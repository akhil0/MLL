package mll.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.simple.JSONObject;

import mll.service.RetrieveBandInformationService;

public class RetrieveBandInformationServlet {

	RetrieveBandInformationService service=new RetrieveBandInformationService();
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException  
	{
       int id=Integer.parseInt((String)request.getParameter("bandId"));
       JSONArray arry=service.retrieveBand(id);
        
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		out.print(arry);
		out.flush();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		doGet(request, response);
	}
}
