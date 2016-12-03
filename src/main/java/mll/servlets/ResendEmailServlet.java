package mll.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONObject;
import mll.service.ResendEmailtoMusicianService;

public class ResendEmailServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	ResendEmailtoMusicianService subService = null;
	
	public void init(ServletConfig config) throws ServletException 
	{
		subService = new ResendEmailtoMusicianService();
	}
	
	@SuppressWarnings("unchecked")
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException  
	{
		JSONObject responseObject = new JSONObject();
		boolean flag = false;
		if(request.getSession().getAttribute("userId") !=  null || request.getParameter("token") != null) {
			String token = request.getParameter("token");
			flag = subService.sendEmail(token);
		}
		responseObject.put("isValid", flag);
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