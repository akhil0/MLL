package mll.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONObject;

import mll.service.PlaylistReferenceService;


public class PlaylistReferenceServlet extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
	PlaylistReferenceService playlistReferenceService = null;
	
	public void init(ServletConfig config) throws ServletException 
	{
		playlistReferenceService = new PlaylistReferenceService();
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException  
	{
		JSONObject responseObject = playlistReferenceService.handlePlaylistReferenceRequest(request, response);
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