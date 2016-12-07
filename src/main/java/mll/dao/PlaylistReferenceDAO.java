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
	
	
	public int addPlaylist1(PlaylistReference playlistReference) {
		Session session = null;
		Transaction tx = null;
        int id = 0;
		
		if(playlistReference==null)
			return 0;
		
		
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
	 
	        try{
				session = SessionFactoryUtil.getSessionFactory().getCurrentSession();
				tx = session.beginTransaction();
			
				Query q = session.createQuery("From mll.beans.PlaylistReference p ORDER BY p.id DESC LIMIT 1");
				q.setMaxResults(1);
				PlaylistReference pr = (PlaylistReference) q.uniqueResult();
				tx.commit();
				id =  pr.getId();
	        }catch(Exception e){
				if( null != tx)
				{
					// Rollback the transaction if any error comes during the process
					 tx.rollback();
				}
				throw e;
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
		return id;
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
        		p.setCreationDate(list.get(i).getCreationDate());
        		
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
	
	public boolean deletePlaylist(int playlistId, int userId) {		
		System.out.println("PlaylistId " + playlistId + " userId " + userId );
		
		Session session = null;
		Transaction tx = null;
		
		
		if(playlistId <= 0)
			return false;
		
		try
		{
			// Initialize the session and transaction
			session = SessionFactoryUtil.getSessionFactory().getCurrentSession();
			tx = session.beginTransaction();			
			Query query = session.createQuery("FROM mll.beans.PlaylistReference pr where pr.userId=:userId AND pr.id=:playlistId");
			query.setParameter("userId", userId);
			query.setParameter("playlistId", playlistId);
			
			PlaylistReference pr = (PlaylistReference) query.uniqueResult();
			
			session.delete(pr);
			
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
		
public List<PlaylistReference> getSharedPlaylists(boolean flag) {
		
		// we add playlists from db to this variable and return this
		List<PlaylistReference>  playlists = new ArrayList<PlaylistReference>();
		Session session = null;
		Transaction tx = null;
		
		try
		{
			// Initialize the session and transaction
			session = SessionFactoryUtil.getSessionFactory().getCurrentSession();
			tx = session.beginTransaction();
			Query query = session.createQuery("FROM mll.beans.PlaylistReference pr where pr.isShared=:isShared");
			query.setParameter("isShared", flag);
			List<PlaylistReference> list = (ArrayList<PlaylistReference>) query.list();
	       
        	for(int i = 0;i<list.size();i++) {
        		PlaylistReference p = new PlaylistReference();
        		p.setId(list.get(i).getId());
        		p.setUserId(list.get(i).getUserId());
        		p.setPlaylistName(list.get(i).getPlaylistName());
        		p.setCreationDate(list.get(i).getCreationDate());
        		//(list.get(i).getUser().getUserName());
        		System.out.print(list.get(i).getUserId());
        		System.out.print(" -> " +  list.get(i).getId());
        		System.out.println(" -> " + list.get(i).getPlaylistName());
        		System.out.println(" -> " + list.get(i).getCreationDate());
        		System.out.println(" -> " + list.get(i).getIsShared());
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

	public boolean setPlaylistToGlobal(int userId, int playlistId) {
		
		Session session = null;
		Transaction tx = null;
		
		try {
			session = SessionFactoryUtil.getSessionFactory().getCurrentSession();
			tx = session.beginTransaction();
			System.out.println(playlistId);
			Query query = session.createQuery("FROM mll.beans.PlaylistReference pr WHERE pr.userId=:userId AND pr.id=:playlistId");
			query.setParameter("userId", userId);
			query.setParameter("playlistId", playlistId);
			
			PlaylistReference pr = (PlaylistReference) query.uniqueResult();
			
			if(pr == null)
				return false;
			
			pr.setIsShared(true);
			
			session.update(pr);
			
			tx.commit();
					
		}
		catch(Exception e) {
			
			if (tx!=null) {
				tx.rollback();
			}
	        e.printStackTrace();
		}
		return true;
	}
	
	
	public boolean isExistingPlaylistForUser(int userId, int playlistId) {
		boolean doesExist = false;
		Session session = null;
		Transaction tx = null;

		try
		{
			// Initialize the session and transaction
			session = SessionFactoryUtil.getSessionFactory().getCurrentSession();
			tx = session.beginTransaction();

			Query query = session.createQuery("FROM mll.beans.PlaylistReference pr WHERE pr.userId=:userId AND pr.id=:playlistId");
			query.setParameter("userId", userId);
			query.setParameter("playlistId", playlistId);

			List<PlaylistReference> list = (ArrayList<PlaylistReference>) query.list();

			if(list.size() > 0) {
				doesExist = true;
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

		return doesExist;
	}
	
	
	public String getPlaylistName(int playlistId) {

		if(playlistId < 1) {
			return null;
		}

		Session session = null;
		Transaction tx = null;
		PlaylistReference reference = null;
		
		try {
			session = SessionFactoryUtil.getSessionFactory().getCurrentSession();
			tx = session.beginTransaction();
			Query query = session.createQuery("FROM mll.beans.PlaylistReference pr WHERE pr.id=:playlistId");
			query.setParameter("playlistId", playlistId);

			reference = (PlaylistReference) query.list().get(0);
			
			if(reference == null)
				return null;
			
			tx.commit();
		}
		catch(Exception e) {
			
			if (tx!=null) {
				tx.rollback();
			}
	        e.printStackTrace();
		}

		return reference.getPlaylistName();
	}
	
	public boolean removePlaylistFromGlobal(int userId, int playlistId) {
		return false;
	}
}