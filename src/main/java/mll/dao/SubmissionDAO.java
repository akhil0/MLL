package mll.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import mll.beans.Artist;
import mll.beans.Folder;
import mll.beans.Genre;
import mll.beans.Metadata;
import mll.beans.Musician;
import mll.beans.Owner;
import mll.beans.Song;
import mll.utility.SessionFactoryUtil;

public class SubmissionDAO 
{
	/**
	* This method takes list of Metadata objects as an input and save 
	* all the metadata information into database configured in hibernate 
	* configuration file.
	*
	* @author  Dhaval Patel
	* @version 1.0
	* @since   2016-03-24 
	*/
	public List<Metadata> saveMetadata(List<Metadata> metadatas,ArrayList<String> AssetIds,String folder_id) throws Exception
	{
		Session session = null;
		Transaction tx = null;
		
		try
		{
			// Initialize the session and transaction
			session = SessionFactoryUtil.getSessionFactory().getCurrentSession();
			
		
			
			tx = session.beginTransaction();
			int i=0;
			for(Metadata metadata : metadatas)
			{
				String songid=AssetIds.get(i);
				// Save the song details in song table
				
				if(null !=songid)
				{
					// Save all artists associated with this song in Artist table
				
					// Save all owners associated with this song in Owner table
					saveSong(metadata.getSongMetadata().getUserId(),songid,session);
					saveFolder(folder_id,songid,session);
					saveOwners(metadata.getOwners(), songid, session);
					
					
					
				}
				i++;
			}
			
			// Commit the transaction if all the data successfully saved
			tx.commit();
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
		return metadatas;
	}
	
	
	
	
	private Folder saveFolder(String folder_id, String songid,Session session) {
		
		Folder folder=new Folder();
		if(folder_id!=null && songid!=null)
		{
			folder.setAsset_id(songid);
			folder.setFolder_id(folder_id);
			folder.setType("song");
			session.save(folder);
		}
		return folder;
	}




	private Song saveSong(long userId, String songid,Session session) {
		
		Song song=new Song();
		if(songid!=null)
		{
			song.setMusician_id(userId);
			song.setAsset_id(songid);
			session.save(song);
		}
		return song;
	}

//	/**
//	* This method takes list of artists of the song, id of the song
//	* and session object as input and save all the artists in Artist
//	* table 
//	*
//	* @author  Dhaval Patel
//	* @version 1.0
//	* @since   2016-03-24 
//	*/
//	public List<Artist> saveArtists(List<Artist> artists, Integer songId, Session session) throws Exception
//	{
//		if(null != artists)
//		{
//			for(Artist artist : artists)
//			{
//				if(null != artist)
//				{
//					artist.setSongId(songId);
//					artist.setId((Integer)session.save(artist));
//				}
//			}
//		}
//		return artists;
//	}
//
//	/**
//	* This method takes list of Genres of the song, id of the song
//	* and session object as input and save all the genres in Genre
//	* table 
//	*
//	* @author  Dhaval Patel
//	* @version 1.0
//	* @since   2016-03-24 
//	*/
//	public List<Genre> saveGenres(List<Genre> genres, Integer songId, Session session) throws Exception
//	{
//		if(null != genres)
//		{
//			for(Genre genre : genres)
//			{
//				if(null != genre)
//				{
//					genre.setSongId(songId);
//					genre.setId((Integer)session.save(genre));
//				}
//			}
//		}
//		return genres;
//	}

	/**
	* This method takes list of owners of the song, id of the song
	* and session object as input and save all the owners in Owner
	* table 
	*
	* @author  Dhaval Patel
	* @version 1.0
	* @since   2016-03-24 
	*/
	public List<Owner> saveOwners(List<Owner> owners, String songId, Session session) throws Exception
	{
		if(null != owners)
		{
			for(Owner owner : owners)
			{
				if(null != owner)
				{
					owner.setSongId(songId);
					owner.setId((Integer)session.save(owner));
				}
			}
		}
		return owners;
	}
	
}
