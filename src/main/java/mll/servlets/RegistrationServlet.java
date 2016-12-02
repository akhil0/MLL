package mll.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;
import org.json.simple.JSONObject;

import mll.service.RegistrationService;

public class RegistrationServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	RegistrationService subService = null;
	
	public void init(ServletConfig config) throws ServletException 
	{
		subService = new RegistrationService();
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException  
	{
        JSONObject responseObject = subService.register(request, response);
        System.out.println(responseObject);
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		HttpSession session=request.getSession();
		System.out.println("FOR SESSION    " + responseObject.get("userId"));
		session.setAttribute("userId", responseObject.get("userId"));
		out.print(responseObject);
		out.flush();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		doGet(request, response);
	}
}
