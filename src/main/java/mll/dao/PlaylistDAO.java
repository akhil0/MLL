package mll.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
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
	        
			System.out.println("AYAYAYAYAYA");
	    }
		catch(HibernateException e) 
		{
			System.out.println("Inside Exception");
			if( null != tx)
			{
				tx.rollback();
			}
			return false;
		  // Error message for integrity constraint violation
		}
		catch(Exception e)
		{
			System.out.println("Inside Exception 1");
			if( null != tx)
			{
				tx.rollback();
			}
			return false;
		  //
		}
		
		return true;
	}
}