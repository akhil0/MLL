package mll.service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import mll.beans.PlaylistReference;
import mll.dao.AdminDAO;
import mll.dao.PlaylistReferenceDAO;

public class PlaylistReferenceService {

	/*
	 * This method takes in the request object containing the type of request i.e. add/delete/get
	 * and constructs the response object with the updated playlist.
	 * Author: Vishal Sanjiv Kotak
	 * Date: 11/26/2016
	 */
	@SuppressWarnings({ "unchecked", "null", "unused" })
	public JSONObject handlePlaylistReferenceRequest(HttpServletRequest request, HttpServletResponse response) {
		
		JSONObject responseObject = new JSONObject();
		System.out.println(request.getParameter("actionType"));
		if(request.getSession().getAttribute("userId") != null) {
			int userId = Integer.parseInt(request.getSession().getAttribute("userId").toString());
			if(request.getParameter("actionType").equals("add")) {
				String playlistName = request.getParameter("playlistName");
				boolean isSuccess = addPlaylistForUser(userId, playlistName);
				if(isSuccess) {
					List<PlaylistReference> playlists = getAllPlaylistsForUser(userId);
					JSONArray playlistReferences = convertToJson(playlists);
					responseObject.put("playlists", playlistReferences);
					responseObject.put("isValid", true);
				}
				else {
					List<PlaylistReference> playlists = getAllPlaylistsForUser(userId);
					JSONArray playlistReferences = convertToJson(playlists);
					responseObject.put("playlists", playlistReferences);
					responseObject.put("isValid", false);
				}
			}
			else if(request.getParameter("actionType").equals("shared")) {
				List<PlaylistReference> playlists = getSharedPlaylists();
				JSONArray playlistReferences = convertToJson(playlists);
				responseObject.put("playlists", playlistReferences);
				responseObject.put("isValid", true);
			}
			else if(request.getParameter("actionType").equals("get")) {
				System.out.println("IN GET");				
				List<PlaylistReference> playlists = getAllPlaylistsForUser(userId);
				JSONArray playlistReferences = convertToJson(playlists);
				responseObject.put("playlists", playlistReferences);
				responseObject.put("isValid", true);
			}
			else if(request.getParameter("actionType").equals("delete")){
				int playlistId = Integer.parseInt(request.getParameter("playlistId"));
				boolean flag = deletePlaylistForUser(playlistId, userId);
				List<PlaylistReference> playlists = getAllPlaylistsForUser(userId);
				JSONArray playlistReferences = convertToJson(playlists);
				responseObject.put("playlists", playlistReferences);
				responseObject.put("isValid", true);
			}
			else if(request.getParameter("actionType").equals("addToShare")) {
				int playlistId = Integer.parseInt(request.getParameter("playlistId"));
				responseObject = setPlaylistToGlobal(userId, playlistId);
			}
			else if(request.getParameter("actionType").equals("unShare")) 
			{
				System.out.println("UNSHARE ");
				int playlistId = Integer.parseInt(request.getParameter("playlistId"));
				responseObject = removeFromShare(userId, playlistId);
			}

		}
		else {
			responseObject.put("isValid", false);
			responseObject.put("playlists", null);
		}
		return responseObject;
	}
	
	
	public boolean deletePlaylistForUser(int playlistId, int userId) {
		
		boolean flag = new PlaylistReferenceDAO().deletePlaylist(playlistId, userId);
		return flag;
		
	}
	
	public List<PlaylistReference> getSharedPlaylists() {

		boolean shared = true;
		return new PlaylistReferenceDAO().getSharedPlaylists(shared);
	}
	
	/*
	 * This method takes in the user identifier and the playlist name and adds a 
	 * new playlist for that user.
	 * Author: Vishal Sanjiv Kotak
	 * Date: 11/26/2016
	 */
	
	
	public boolean addPlaylistForUser(int userId, String playlistName) {
		PlaylistReferenceDAO playlistReferenceDAO = new PlaylistReferenceDAO();
		PlaylistReference playlistReference = new PlaylistReference();
		playlistReference.setId(0);
		playlistReference.setPlaylistName(playlistName);
		playlistReference.setUserId(userId);
		String userName = new AdminDAO().getUserName(userId);
		if(userName == null)
			return false;
		playlistReference.setUserName(userName);
		playlistReference.setIsShared(false);
		playlistReference.setCreationDate(new Date());
		boolean flag = playlistReferenceDAO.addPlaylist(playlistReference);
		return flag;
	}

	
	/*
	 * This method takes in the user identifier and returns the list of all 
	 * playlists belonging to that user
	 * Author: Vishal Sanjiv Kotak
	 * Date: 11/26/2016
	 */
	
	
	
	public List<PlaylistReference> getAllPlaylistsForUser(int userId) {

		return new PlaylistReferenceDAO().getAllPlaylistsForUserId(userId);
	}
	
	/*
	 * This method takes in the playlists and returns a JSON Array
	 * for that playlists
	 * Author: Dishant Shah
	 * Date: 11/26/2016
	 * 
	 */
	
	
	@SuppressWarnings("unchecked")
	public JSONArray convertToJson(List<PlaylistReference> playlistReference) {
		JSONArray jsonArrayPlaylist = new JSONArray();
		for(int i = 0; i< playlistReference.size(); i++){
			JSONObject object = new JSONObject();
			object.put("id", playlistReference.get(i).getId());
			object.put("userId", playlistReference.get(i).getUserId());
			object.put("playlistName", playlistReference.get(i).getPlaylistName());
			object.put("isShared", playlistReference.get(i).getIsShared());
			DateFormat format = new SimpleDateFormat("dd-mm-yyyy");
			object.put("creationDate", (playlistReference.get(i).getCreationDate().getMonth() + 1) + "-" + 
						playlistReference.get(i).getCreationDate().getDate() + "-" + (playlistReference.get(i).getCreationDate().getYear() + 1900));
			object.put("userName", playlistReference.get(i).getUserName());
			jsonArrayPlaylist.add(object);			
		}
		return jsonArrayPlaylist;
	}
	
	
	@SuppressWarnings("unchecked")
	public JSONObject setPlaylistToGlobal(int userId, int playlistId) {

		boolean isPlaylistUpdated = new PlaylistReferenceDAO().setPlaylistToGlobal(userId, playlistId);
		List<PlaylistReference> playlists = getAllPlaylistsForUser(userId);
		JSONArray playlistReferences = convertToJson(playlists);
		JSONObject responseObject = new JSONObject();
		responseObject.put("playlists", playlistReferences);
		responseObject.put("isValid", isPlaylistUpdated);
		return responseObject;
	}
		
	public JSONObject removeFromShare(int userId, int playlistId) {
		JSONObject responseObject = new JSONObject();
		boolean success = new PlaylistReferenceDAO().removePlaylistFromGlobal(userId,playlistId);
		responseObject.put("success", success);
		return responseObject;
	}
	
	
}