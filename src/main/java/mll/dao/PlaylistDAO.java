package mll.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import mll.beans.Playlist;
import mll.utility.SessionFactoryUtil;

public class PlaylistDAO {	
	
	public List<Playlist> getAllPlaylistsForUserId(int userId) {
		
		List<Playlist> playlist = new ArrayList<Playlist>();
		return playlist;
	}
	
	public List<Playlist> getPlaylistsForPlayListId(int playlistId) {
		
		List<Playlist> playlists = new ArrayList<Playlist>();
		return playlists;
	}
	
	public boolean addPlaylist(Playlist playlist) {
		
		return false;
	}

	public boolean deletePlaylist(Playlist playlist) {
		
		return false;
	}
}