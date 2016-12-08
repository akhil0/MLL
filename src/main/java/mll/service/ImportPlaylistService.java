package mll.service;

import mll.beans.Playlist;
import mll.beans.PlaylistReference;
import mll.dao.AdminDAO;
import mll.dao.ArUserDAO;
import mll.dao.PlaylistDAO;
import mll.dao.PlaylistReferenceDAO;
import java.util.Date;
import org.json.JSONArray;


public class ImportPlaylistService 
{
	PlaylistReferenceDAO dao;
	PlaylistDAO playlistDAO;

	public ImportPlaylistService() 
	{
		dao = new PlaylistReferenceDAO();
		playlistDAO = new PlaylistDAO();
	}
	

	public boolean importPlaylist(int destUserId, int playlistId) {
	
		boolean playlistExistsForUser = dao.isExistingPlaylistForUser(destUserId, playlistId);
		if(playlistExistsForUser) {
			return false;
		}

		String playlistName = dao.getPlaylistName(playlistId);

		PlaylistReference newPlaylistreference = new PlaylistReference();
		newPlaylistreference.setPlaylistName(playlistName);
		newPlaylistreference.setUserId(destUserId);
		String userName = new ArUserDAO().getUserName(destUserId);
		if(userName == null)
			return false;
		newPlaylistreference.setUserName(userName);
		newPlaylistreference.setCreationDate(new Date());
		newPlaylistreference.setIsShared(false);

		int newId = dao.addPlaylist1(newPlaylistreference);
		boolean songsAdded = addSongsToPlaylist(playlistId, newId);

		return songsAdded;
	}

	public boolean addSongsToPlaylist(int sourceid, int destid) {
		
		try{
			JSONArray jsonarray = playlistDAO.getAllSongsForPlaylist(sourceid);	
			for(int i =0; i < jsonarray.length(); i++) {
				String object = jsonarray.getJSONObject(i).getString("assetId");
				Playlist p = new Playlist();
				p.setPlaylist_id(destid);
				p.setSong_id(object);

				boolean success = playlistDAO.addSongPlaylist(p);
				if(success == false) {
					return false;
				}
			}

		}catch(Exception e){
			e.printStackTrace();
		}
		return true;
	}
}