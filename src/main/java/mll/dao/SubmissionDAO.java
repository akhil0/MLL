package mll.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import mll.beans.Artist;
import mll.beans.Genre;
import mll.beans.Metadata;
import mll.beans.Owner;
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
	public List<Metadata> saveMetadata(List<Metadata> metadatas) throws Exception
	{
		Session session = null;
		Transaction tx = null;
		
		try
		{
			// Initialize the session and transaction
			session = SessionFactoryUtil.getSessionFactory().getCurrentSession();
			tx = session.beginTransaction();
			
			for(Metadata metadata : metadatas)
			{
				// Save the song details in song table
				if(null != metadata.getSong())
				{
					metadata.getSong().setId((Integer) session.save(metadata.getSong()));
				}
				if(null != metadata.getSong().getId())
				{
					// Save all artists associated with this song in Artist table
					saveArtists(metadata.getArtists(), metadata.getSong().getId(), session);
					
					// Save all genres associated with this song in Genre table
					saveGenres(metadata.getGenres(), metadata.getSong().getId(), session);
					
					// Save all owners associated with this song in Owner table
					saveOwners(metadata.getOwners(), metadata.getSong().getId(), session);
				}
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
	
	/**
	* This method takes list of artists of the song, id of the song
	* and session object as input and save all the artists in Artist
	* table 
	*
	* @author  Dhaval Patel
	* @version 1.0
	* @since   2016-03-24 
	*/
	public List<Artist> saveArtists(List<Artist> artists, Integer songId, Session session) throws Exception
	{
		if(null != artists)
		{
			for(Artist artist : artists)
			{
				if(null != artist)
				{
					artist.setSongId(songId);
					artist.setId((Integer)session.save(artist));
				}
			}
		}
		return artists;
	}

	/**
	* This method takes list of Genres of the song, id of the song
	* and session object as input and save all the genres in Genre
	* table 
	*
	* @author  Dhaval Patel
	* @version 1.0
	* @since   2016-03-24 
	*/
	public List<Genre> saveGenres(List<Genre> genres, Integer songId, Session session) throws Exception
	{
		if(null != genres)
		{
			for(Genre genre : genres)
			{
				if(null != genre)
				{
					genre.setSongId(songId);
					genre.setId((Integer)session.save(genre));
				}
			}
		}
		return genres;
	}

	/**
	* This method takes list of owners of the song, id of the song
	* and session object as input and save all the owners in Owner
	* table 
	*
	* @author  Dhaval Patel
	* @version 1.0
	* @since   2016-03-24 
	*/
	public List<Owner> saveOwners(List<Owner> owners, Integer songId, Session session) throws Exception
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
