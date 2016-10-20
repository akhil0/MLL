package mll.dao;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import mll.beans.Artist;
import mll.beans.Genre;
import mll.beans.Metadata;
import mll.beans.Owner;

public class SubmissionDAOTest 
{
	@Test
	public void testSaveMetadata1() 
	{
		try 
		{
			SubmissionDAO dao = new SubmissionDAO();
			assertEquals(true, dao.saveMetadata(null) == null);
		} 
		catch (Exception e) 
		{

		}
	}
	
	@Test
	public void testSaveMetadata2() 
	{
		try 
		{
			SubmissionDAO dao = new SubmissionDAO();
			List<Metadata> metadatas = new ArrayList<Metadata>();
			assertEquals(true, dao.saveMetadata(metadatas) == metadatas);
		} 
		catch (Exception e) 
		{

		}
	}
	
	@Test
	public void testSaveArtists1() 
	{
		try 
		{
			SubmissionDAO dao = new SubmissionDAO();
			assertEquals(true, dao.saveArtists(null, null, null) == null);
		} 
		catch (Exception e) 
		{

		}
	}
	
	@Test
	public void testSaveArtists2() 
	{
		try 
		{
			SubmissionDAO dao = new SubmissionDAO();
			List<Artist> artists = new ArrayList<Artist>();
			assertEquals(true, dao.saveArtists(artists, null, null) == artists);
		} 
		catch (Exception e) 
		{

		}
	}
	
	@Test
	public void testSaveGenres1() 
	{
		try 
		{
			SubmissionDAO dao = new SubmissionDAO();
			assertEquals(true, dao.saveGenres(null, null, null) == null);
		} 
		catch (Exception e) 
		{

		}
	}
	
	@Test
	public void testSaveGenres2() 
	{
		try 
		{
			SubmissionDAO dao = new SubmissionDAO();
			List<Genre> genres = new ArrayList<Genre>();
			assertEquals(true, dao.saveGenres(genres, null, null) == genres);
		} 
		catch (Exception e) 
		{

		}
	}
	
	@Test
	public void testSaveOwners1() 
	{
		try 
		{
			SubmissionDAO dao = new SubmissionDAO();
			assertEquals(true, dao.saveOwners(null, null, null) == null);
		} 
		catch (Exception e) 
		{

		}
	}
	
	@Test
	public void testSaveOwners2() 
	{
		try 
		{
			SubmissionDAO dao = new SubmissionDAO();
			List<Owner> owners = new ArrayList<Owner>();
			assertEquals(true, dao.saveOwners(owners, null, null) == owners);
		} 
		catch (Exception e) 
		{

		}
	}
	
}
