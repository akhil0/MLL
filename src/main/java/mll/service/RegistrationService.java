package mll.service;

import java.io.BufferedReader;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import mll.beans.AdminUser;
import mll.beans.Musician;
import mll.beans.Token;
import mll.beans.User;
import mll.beans.UserDetails;
import mll.dao.RegistrationDAO;
import mll.utility.Encryption;

public class RegistrationService {
	RegistrationDAO dao;

	public RegistrationService() {
		dao = new RegistrationDAO();
	}

	@SuppressWarnings("unchecked")
	public JSONObject register(HttpServletRequest request, HttpServletResponse response) 
	{
		JSONObject responseObject = new JSONObject();

		try {
			UserDetails userdetails = populateUserDetailBeanFromRequest(request);

			if (null != userdetails) {
				responseObject = dao.registerUser(userdetails);
			} else {
				responseObject.put("isRegistered", false);
				responseObject.put("errorMessage", "Error while registration. Please submit with proper user details.");
			}
		} catch (Exception e) {
			e.printStackTrace();
			responseObject.put("isRegistered", false);
			responseObject.put("errorMessage", "Error while registration. Please submit with proper user details.");
		}

		return responseObject;
	}

	/**
	 * This method takes http request as input and validates the request and if
	 * it is valid then creates the metadata list from the request object.
	 *
	 * @author Dhaval Patel
	 * @version 1.0
	 * @since 2016-03-24
	 */
	public UserDetails populateUserDetailBeanFromRequest(HttpServletRequest request) throws Exception 
	{
		StringBuffer requestStr = new StringBuffer();
		BufferedReader reader = request.getReader();
		String line = null;
		while ((line = reader.readLine()) != null) {
			requestStr.append(line);
		}

		JSONParser parser = new JSONParser();
		JSONObject mainObject = (JSONObject) parser.parse(requestStr.toString());

		UserDetails userdetails = new UserDetails();
		
		userdetails.setToken(populateToken(mainObject));
		userdetails.setType((String) mainObject.get("type"));

		userdetails.setUsers(populateUser(mainObject));

		// Calling Encryption method to encrypt the password & Setting the encrypted password 
		userdetails.getUsers().setPassword(Encryption.encryptPassword(userdetails.getUsers().getPassword()));
		
		if (userdetails.getType().equalsIgnoreCase("user")) 
		{
			userdetails.setAdminUser(populateAdminUser(mainObject));

		} else
		{
			userdetails.setMusician(populateMusician(mainObject));
		}

		return userdetails;
	}

	public User populateUser(JSONObject jo) 
	{
		User u = null;
		if (null != jo) 
		{
			u = new User();
			u.setUserName((String) jo.get("userName"));
			u.setPassword((String) jo.get("password"));
			u.setEmailId((String) jo.get("emailId"));
		}
		return u;
	}

	public AdminUser populateAdminUser(JSONObject jo)
	{
		AdminUser au = null;
		if (null != jo) 
		{
			au = new AdminUser();
			au.setFirstName((String) jo.get("firstName"));
			au.setLastName((String) jo.get("lastName"));
			au.setCollege((String) jo.get("college"));
			au.setLevel((String) jo.get("level"));
			au.setGender((String) jo.get("gender"));
			
			if(null != jo.get("major") && !"".equals(jo.get("major")))
			{
				au.setMajor((String) jo.get("major"));
			}
			else
			{
				au.setMajor(" ");
			}
			
			if(null != jo.get("minor") && !"".equals(jo.get("minor")))
			{
				au.setMinor((String) jo.get("minor"));
			}
			else
			{
				au.setMinor(" ");
			}
			
			au.setPreference((String) jo.get("preference"));
			au.setAge(((Long) jo.get("age")).intValue());
		}
		return au;
	}

	public Musician populateMusician(JSONObject jo) 
	{
		Musician m = null;
		if (null != jo) 
		{
			m = new Musician();
			m.setName(" ");
		}
		return m;
	}
	
	
	public Token populateToken(JSONObject jo) 
	{
		Token t = null;
		if (null != jo) 
		{
			t = new Token();
			t.setToken((String) jo.get("token"));
			t.setInviteType((String) jo.get("type"));
		}
		return t;
	}	
}