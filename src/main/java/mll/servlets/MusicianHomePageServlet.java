package mll.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.http.ParseException;
import org.json.JSONArray;
import org.json.JSONException;

import mll.service.LoginService;
import mll.service.RazunaService;
import mll.service.SongRetrievalService;

public class MusicianHomePageServlet extends HttpServlet{
	SongRetrievalService retrievalservice;
	RazunaService razunaservice;
	
	public void init(ServletConfig config) throws ServletException 
	{
		retrievalservice=new SongRetrievalService();
		razunaservice=new RazunaService();
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		doGet(request, response);
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
	    HttpSession session=request.getSession();
	    String folderid=new String();
	    JSONArray songs=new JSONArray();
	    if(session.getAttribute("folder_id")!=null)
	    {
	    	folderid=(String) session.getAttribute("folder_id");
	    }
	    try {
			songs=razunaservice.RetrieveSongs(folderid);
		} catch (ParseException | JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	    response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		out.print(songs);
		out.flush();

	}

	
}
