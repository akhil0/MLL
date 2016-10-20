package mll.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import mll.beans.Login;
import mll.service.LoginService;

public class LoginServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	LoginService subService = null;
	
	public void init(ServletConfig config) throws ServletException 
	{
		subService = new LoginService();
	}
	
	@SuppressWarnings("unchecked")
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException  
	{
		Login login = subService.validateLogin(request, response);
		
		//Create a new JSON resonse object 
		JSONObject responseObject = new JSONObject();
		
		/** Check if the error message is null , if not then check the user type.
		 * If user is musician, put all its details in JSON response object and if
		 * the user is admin user, put all its details in JSON response object. Otherwise,
		 * put error message and print on console.
		 */
		
		if (login.isValidUser())
		{
			responseObject.put("isValidUser", true);
			responseObject.put("type", login.getType());
			responseObject.put("userId", login.getUser().getId());
			responseObject.put("browse", login.isCanBrowse());
			responseObject.put("upload", login.isCanUpload());
			responseObject.put("email", login.getUser().getEmailId());
			
			if (login.getType() == Login.musicianType) 
			{
				responseObject.put("name", login.getMusician().getName());
			} 
			else 
			{
				responseObject.put("firstName", login.getAdmin().getFirstName());
				responseObject.put("lastName", login.getAdmin().getLastName());
				responseObject.put("college", login.getAdmin().getCollege());
				responseObject.put("level", login.getAdmin().getLevel());
				responseObject.put("gender", login.getAdmin().getGender());
				responseObject.put("preference", login.getAdmin().getPreference());
				responseObject.put("age", login.getAdmin().getAge());
			}
		}
		else 
		{
			responseObject.put("isValidUser", false);
			responseObject.put("errorMessage", login.getErrMsg());
		}

		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		out.print(responseObject);
		out.flush();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		doGet(request, response);
	}
}
