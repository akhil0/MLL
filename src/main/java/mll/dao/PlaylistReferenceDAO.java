package mll.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import mll.beans.PlaylistReference;
import mll.utility.SessionFactoryUtil;


public class PlaylistReferenceDAO {

	
	/*
	 * Tis method takes in a playlist main object and adds it to the database
	 * Author: Meha Verma
	 * Date: 11/26/2016
	 */
	
	public boolean addPlaylist(PlaylistReference playlistReference) {
		Session session = null;
		Transaction tx = null;
		
		
		if(playlistReference==null)
			return false;
		
		
		try
		{
			// Initialize the session and transaction
			session = SessionFactoryUtil.getSessionFactory().getCurrentSession();
			tx = session.beginTransaction();
			
			//Save the playlist in database
	        session.save(playlistReference);
	 
	        //Commit the transaction
	        session.getTransaction().commit();
	        
			// Commit the transaction if all the data successfully saved
	        if (!tx.wasCommitted()) {
	        	tx.commit();
	        }
	        
	    }
		catch(Exception e)
		{
			if( null != tx)
			{
				// Rollback the transaction if any error comes during the process
				 tx.rollback();
			}
			throw e;
		}
		return true;
	}
	
	

	/*
	 * Tis method takes in userId and gets all the playlists for the user
	 * Author: Meha Verma
	 * Date: 11/26/2016
	 */
	
	public List<PlaylistReference> getAllPlaylistsForUserId(int userId) {
		
		if(userId<1)
			return null;
		
		// we add playlists from db to this variable and return this
		List<PlaylistReference>  playlists = new ArrayList<PlaylistReference>();
		Session session = null;
		Transaction tx = null;
		
		try
		{
			// Initialize the session and transaction
			session = SessionFactoryUtil.getSessionFactory().getCurrentSession();
			tx = session.beginTransaction();
			
			Query query = session.createQuery("FROM mll.beans.PlaylistReference pr where pr.userId=:userId");
			 query.setParameter("userId", userId);
	        List<PlaylistReference> list = (ArrayList<PlaylistReference>) query.list();
	       
        	for(int i = 0;i<list.size();i++) {
        		PlaylistReference p = new PlaylistReference();
        		p.setId(list.get(i).getId());
        		p.setUserId(list.get(i).getUserId());
        		p.setPlaylistName(list.get(i).getPlaylistName());
        		
        		System.out.print(list.get(i).getUserId());
        		System.out.print(" -> " +  list.get(i).getId());
        		System.out.println(" -> " + list.get(i).getPlaylistName());
        		playlists.add(p);        		
        	}
            
			// Commit the transaction if all the data is successfully saved
			tx.commit();
		}
		catch(Exception e)
		{
			if( null != tx)
			{
				// Rollback the transaction if any error comes during the process
				tx.rollback();
			}
			e.printStackTrace();
			throw e;
		}
		
		return playlists;
	}
	
	public boolean deletePlaylist(PlaylistReference playlistReference) {
		return false;	
	}
	
}