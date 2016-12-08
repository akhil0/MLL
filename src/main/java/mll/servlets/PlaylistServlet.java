package mll.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.simple.JSONObject;

import mll.service.ImportPlaylistService;
import mll.service.PlaylistService;


public class PlaylistServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	PlaylistService playlistService = null;
	
	public void init(ServletConfig config) throws ServletException 
	{
		playlistService = new PlaylistService();
	}
	
	//Method added on 11/29/2016
	@SuppressWarnings("unchecked")
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException  
	{
		
		String action = request.getParameter("actionType");
		JSONObject responseObject = new JSONObject();
		if(action != null || request.getSession().getAttribute("userId") != null)
		{
			int userId = Integer.parseInt(request.getSession().getAttribute("userId").toString());
			String assetId = request.getParameter("assetId");
			int playlistId = Integer.parseInt(request.getParameter("playlistId"));
			PlaylistService playlistService = new PlaylistService();
			ImportPlaylistService imp = new ImportPlaylistService();
			if(action.equals("add"))  
			{
				boolean isValid = playlistService.addSongPlaylist(userId, playlistId, assetId);
				responseObject.put("isValid", isValid);				
			}
			else if(action.equals("delete"))
			{
				JSONArray deleteSongPlaylist = null;
				try {
					deleteSongPlaylist = playlistService.deleteSongPlaylist(userId, playlistId, assetId);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				responseObject.put("songs", deleteSongPlaylist);
				responseObject.put("isDeleted", true);
			}
			else if(action.equals("get")) 
			{
				JSONArray songsFromPlaylist = playlistService.getSongsFromPlaylist(playlistId);				
				responseObject.put("songs", songsFromPlaylist);
			}
			
			else if(action.equals("addToMyPlaylist")) 
			{
				boolean importPlaylist = imp.importPlaylist(userId, playlistId);
				responseObject.put("isValid", importPlaylist);
			}
			else {
				responseObject.put("isValid", false);
			}
		}
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		out.print(responseObject);
		out.flush();
	} 

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		doGet(request, response);
	}
}