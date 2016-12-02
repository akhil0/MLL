package mll.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONObject;
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
			if(action.equals("add"))  
			{
				boolean isValid = playlistService.addSongPlaylist(userId, playlistId, assetId);
				responseObject.put("isValid", isValid);
				
			}
//			else if(action.equals("delete"))
//			{
//				playlistService.deleteSongPlaylist(userId, playlistId, assetId);
//			}
			/*else if(action.equals("get")) 
			{
				playlistService.getSongsPlaylist(userId, playlistId, assetId);
			}*/
			else {
				responseObject.put("isValid", false);
			}
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