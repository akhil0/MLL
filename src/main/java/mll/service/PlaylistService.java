package mll.service;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
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
}
