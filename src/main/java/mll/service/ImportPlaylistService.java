package mll.service;

import mll.beans.Playlist;
import mll.beans.PlaylistReference;
import mll.dao.AdminDAO;
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
	
		System.out.println("IMPORT PLAYLIST");
		boolean playlistExistsForUser = dao.isExistingPlaylistForUser(destUserId, playlistId);
		if(playlistExistsForUser) {
			return false;
		}

		String playlistName = dao.getPlaylistName(playlistId);

		PlaylistReference newPlaylistreference = new PlaylistReference();
		newPlaylistreference.setPlaylistName(playlistName);
		newPlaylistreference.setUserId(destUserId);
		String userName = new AdminDAO().getUserName(destUserId);
		if(userName == null)
			return false;
		newPlaylistreference.setUserName(userName);
		newPlaylistreference.setCreationDate(new Date());
		newPlaylistreference.setIsShared(false);

		int newId = dao.addPlaylist1(newPlaylistreference);
		System.out.println("NEW ID  " + newId);
		boolean songsAdded = addSongsToPlaylist(playlistId, newId);

		return songsAdded;
	}

	public boolean addSongsToPlaylist(int sourceid, int destid) {
		
		System.out.println(sourceid);
		System.out.println(destid);
		try{
			JSONArray jsonarray = playlistDAO.getAllSongsForPlaylist(sourceid);
			System.out.println("JSON ARRAY");
			System.out.println(jsonarray);
			for(int i =0; i < jsonarray.length(); i++) {
				String object = jsonarray.getJSONObject(i).getString("assetId");
				System.out.println(object.toString());
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