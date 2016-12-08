package mll.service;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;

import mll.beans.Playlist;
import mll.dao.PlaylistDAO;

public class PlaylistService {
	PlaylistDAO dao;

	public PlaylistService() 
	{
		dao = new PlaylistDAO();
	}
	
	
	@SuppressWarnings("unchecked")
	public JSONArray getSongsFromPlaylist(int playlistId) {

		JSONArray songs = new JSONArray();
		try {
			 songs = dao.getAllSongsForPlaylist(playlistId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		JSONObject responseObject = new JSONObject();
//		responseObject.put("songs", jsonArray);
//		responseObject.put("playlistId", playlistId);
		return songs;
	}
	
	public boolean addSongPlaylist(int userId, int playlistId, String assetId) 
    {
        Playlist playlist = new Playlist();
        playlist.setId(0);
        playlist.setPlaylist_id(playlistId);
        if(assetId==null || assetId.equals(""))
            return false;
        else
            assetId = assetId.trim();
        playlist.setSong_id(assetId);
        boolean flag = dao.addSongPlaylist(playlist);
        return flag;
    }
	
	public JSONArray deleteSongPlaylist(int userId, int playlistId, String assetId) throws Exception 
	{
		return 	dao.deleteSongPlaylist(playlistId, assetId);
		 
	}
			
}
