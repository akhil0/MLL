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
	
	// This method returns all playlists for a given user id
	public List<Playlist> getAllPlaylistsForUserId(HttpServletRequest request, HttpServletResponse response) {
		List<Playlist> playlists = null;
		
		return playlists;
	}
	
	public List<Playlist> getPlaylistsForPlayListId(HttpServletRequest request, HttpServletResponse response) {
		List<Playlist> playlists = new ArrayList<Playlist>();		
		return playlists;
	}
	
	public Playlist getPlayList(int playlist_id) {		
		return new Playlist();
	}
	
	// Add a playlist
	public boolean addPlayList(HttpServletRequest request, HttpServletResponse response) {
		return false;
	}
	
	// Delete a playlist
	public boolean deletePlayList(HttpServletRequest request, HttpServletResponse response) {

		return false;
	}
	
	public boolean updatePlayList(Playlist playlist) {
		return false;
	}	

	@SuppressWarnings("unchecked")
	public JSONArray getSongsFromPlaylist(int playlistId) {

		System.out.println("PLAY LIST ID  " + playlistId);
		JSONArray songs = new JSONArray();
		try {
			 songs = dao.getAllSongsForPlaylist(playlistId);
		} catch (Exception e) {
			e.printStackTrace();
		}
//		JSONArray jsonArray = convertToJSON(songs);
		
		JSONObject responseObject = new JSONObject();
//		responseObject.put("songs", jsonArray);
//		responseObject.put("playlistId", playlistId);
		return songs;
	}
	
	public boolean addSongPlaylist(int userId, int playlistId, String assetId) 
    {
		System.out.println("ADD SONGS ");
        Playlist playlist = new Playlist();
        playlist.setId(0);
        playlist.setPlaylist_id(playlistId);
        if(assetId==null || assetId.equals(""))
            return false;
        else
            assetId = assetId.trim();
        playlist.setSong_id(assetId);
        boolean flag = dao.addSongPlaylist(playlist);
        System.out.println("Song " + assetId + " Playlist " + playlistId + "  " + flag);
        return flag;
    }
	
	
//	@SuppressWarnings("unchecked")
//	public JSONArray convertToJSON(List<Playlist> songs) {
//		JSONArray jsonArray = new JSONArray();
//		for(int i = 0; i < songs.size(); i++) {
//			JSONObject object = new JSONObject();
//			System.out.println(songs.get(i).getPlaylist_id() + "  " + songs.get(i).getSong_id());
//			object.put("assetId", songs.get(i).getSong_id());
//			jsonArray.add(object);
//		}
//		return jsonArray;
//	}
	
	
	public static void main(String[] args) {
		
	}
}
