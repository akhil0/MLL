package mll.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.json.JSONArray;
import org.json.JSONObject;

import mll.beans.Playlist;
import mll.service.RazunaService;
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
	
	@SuppressWarnings("unchecked")
	public JSONArray getAllSongsForPlaylist(int playlistId) throws Exception {
		
		if(playlistId <= 0) {
			return null;
		}
		
		// we add songs from db to this variable and return this
		Session session = null;
		Transaction tx = null;
		List<Playlist> songs = null;
		try
		{
			// Initialize the session and transaction
			session = SessionFactoryUtil.getSessionFactory().getCurrentSession();
			tx = session.beginTransaction();
			
			// query to get all song ids for id
			Query query = session.createQuery("FROM mll.beans.Playlist pl where pl.playlist_id=:playlistId");

	        query.setParameter("playlistId", playlistId);
	        
	        songs =  query.list();
	        
			// Commit the transaction if all the data is successfully saved
			tx.commit();
		}
		catch(Exception e)
		{
			if(null != tx)
			{
				tx.rollback();
			}
			e.printStackTrace();
			throw e;
		}
		
		RazunaService service = new RazunaService();
		JSONArray retrieveSongsUsingAssetId = new JSONArray();
		for(int i = 0; i  < songs.size(); i++){
			
			JSONObject receivedObject = service.retrieveSongsUsingAssetId(songs.get(i).getSong_id());
			retrieveSongsUsingAssetId.put(receivedObject.put("assetId", songs.get(i).getSong_id()));			
			
		}		
		return retrieveSongsUsingAssetId;
	}
	
	public boolean addSongPlaylist(Playlist playlist) 
	{
		if(playlist == null || playlist.getSong_id()==null || playlist.getPlaylist_id()<=0)
			return false;
		
		Session session = null;
		Transaction tx = null;
		
		try
		{
			session = SessionFactoryUtil.getSessionFactory().getCurrentSession();
			tx = session.beginTransaction();
			
			session.save(playlist);
	        session.getTransaction().commit();	       
			if (!tx.wasCommitted()) {
	        	tx.commit();
	        }
	    }
		catch(HibernateException e) 
		{
			if( null != tx)
			{
				tx.rollback();
			}
			return false;
		  // Error message for integrity constraint violation
		}
		catch(Exception e)
		{
			if( null != tx)
			{
				tx.rollback();
			}
			return false;
		  //
		}
		
		return true;
	}
	
	
	public static void main(String[] args) throws Exception {
//		List<Playlist> allSongsForPlaylist = new PlaylistDAO().getAllSongsForPlaylist(129);
//		System.out.println(allSongsForPlaylist);
//		for(int i = 0; i < allSongsForPlaylist.size(); i++){
//			System.out.println("MAIN SONG ID    " + allSongsForPlaylist.get(i).getSong_id());
//			System.out.println(allSongsForPlaylist.get(i).getPlaylist_id());
//		}
		
	}
}