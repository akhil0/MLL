package mll.service;

import java.io.BufferedReader;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import mll.beans.Login;
import mll.dao.LoginDAO;
import mll.utility.Encryption;

public class LoginService
{
	LoginDAO dao;

	public LoginService() 
	{
		dao = new LoginDAO();
	}
	
	/**
	* This method takes http request and response objects as input and 
	* first validates the request and if it is valid then it calls DAO to validate
	* the login credentials and return appropriate message to service.
	*
	* @author Vishal Mehta
	* @version 1.0
	* @since   2016-04-06 
	*/
	
	public Login validateLogin(HttpServletRequest request, HttpServletResponse response) 
	{
		// TODO Auto-generated method stub
		Login login = new Login();
		
		try
		{
			// Validate the request and populate the user beans if the request is valid.
			login = populateUser(request);
			
			if(null != login)
			{
				// validate login 
				login = dao.validateLogin(login);
			}
			else
			{
				login = new Login();
				login.setValidUser(false);
				login.setErrMsg("Invalid Request.");
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			login.setValidUser(false);
			login.setErrMsg("Invalid Request.");
		}
		return login;
	}

	/**
	* This method takes http request as input and validates the 
	* request and if it is valid then sets the username and password 
	* for a particular user from the request object.
	*
	* @author  Vishal Mehta
	* @version 1.0
	* @since   2016-04-06 
	*/
	
	public Login populateUser(HttpServletRequest request) throws Exception
	{
		// TODO Auto-generated method stub
		Login login = new Login();

		StringBuffer requestStr = new StringBuffer();
		BufferedReader reader = request.getReader();
		String line = null;
		while ((line = reader.readLine()) != null)
		{
			requestStr.append(line);
		}

		JSONParser parser = new JSONParser();
		JSONObject mainObject = (JSONObject) parser.parse(requestStr.toString());
		
		login = populateUserDetails(mainObject);
		
		// Calling Encryption method to encrypt the entered password
		login.getUser().setPassword(Encryption.encryptPassword(login.getUser().getPassword()));
		
		return login;
	}
	
	/**
	 * This method takes a JSON object as an input and returns
	 * a login object containing user name and password.
	 * @param JSON object, jo
	 * @return
	 */
	public Login populateUserDetails(JSONObject jo)
	{
		Login login = null;
		if (null != jo) 
		{
			login = new Login();
			login.getUser().setUserName((String)jo.get("userName"));
			login.getUser().setPassword((String)jo.get("password"));
		}
		return login;
	}

}