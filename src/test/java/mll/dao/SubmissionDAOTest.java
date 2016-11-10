package mll.dao;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.json.simple.JSONObject;
import org.junit.Test;

import mll.beans.Artist;
import mll.beans.Genre;
import mll.beans.Metadata;
import mll.beans.Owner;
import mll.beans.SongMetadata;
import mll.utility.SessionFactoryUtil;

public class SubmissionDAOTest 
{
	@Test
	public void testSaveMetadata1() 
	{
		try 
		{
			SubmissionDAO dao = new SubmissionDAO();
			assertEquals(true, dao.saveMetadata(null,null,null) == null);
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
			List<Metadata> metadatas = getMetadata();
			ArrayList<String> AssetIds = new ArrayList<String>();
			AssetIds.add("DEDWDU36283333");
			AssetIds.add("DFJGKGY4564564");
			String folder_id= "QTFTW678686GIK";
			assertEquals(true, dao.saveMetadata(metadatas, AssetIds,folder_id) == metadatas);
		} 
		catch (Exception e) 
		{

		}
	}
	
//	@Test
//	public void testSaveArtists1() 
//	{
//		try 
//		{
//			SubmissionDAO dao = new SubmissionDAO();
//			assertEquals(true, dao.saveArtists(null, null, null) == null);
//		} 
//		catch (Exception e) 
//		{
//
//		}
//	}
//	
//	@Test
//	public void testSaveArtists2() 
//	{
//		try 
//		{
//			SubmissionDAO dao = new SubmissionDAO();
//			List<Artist> artists = new ArrayList<Artist>();
//			assertEquals(true, dao.saveArtists(artists, null, null) == artists);
//		} 
//		catch (Exception e) 
//		{
//
//		}
//	}
//	
//	@Test
//	public void testSaveGenres1() 
//	{
//		try 
//		{
//			SubmissionDAO dao = new SubmissionDAO();
//			assertEquals(true, dao.saveGenres(null, null, null) == null);
//		} 
//		catch (Exception e) 
//		{
//
//		}
//	}
//	
//	@Test
//	public void testSaveGenres2() 
//	{
//		try 
//		{
//			SubmissionDAO dao = new SubmissionDAO();
//			List<Genre> genres = new ArrayList<Genre>();
//			Genre genre = new Genre();
//			genre.setId(1);
//			genre.setGenre("Rock");
//			genre.setSongId(1);
//			genres.add(genre);
//			assertEquals(true, dao.saveGenres(genres, null, null) == genres);
//		} 
//		catch (Exception e) 
//		{
//
//		}
//	}
	
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
			Owner owner = new Owner();
			owner.setId(1);
			owner.setSongId("DEDGWY42828");
			owner.setName("Name");
			owner.setPrimaryEmail("owner@gmail.com");
			owners.add(owner);
			assertEquals(true, dao.saveOwners(owners, null, null) == owners);
		} 
		catch (Exception e) 
		{

		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Metadata> getMetadata() {	
		Metadata metadata = new Metadata();
		Owner owner = new Owner();
		owner.setId(1);
		owner.setSongId("DEDGWY42828");
		owner.setName("Name");
		owner.setPrimaryEmail("owner@gmail.com");
		SongMetadata song = new SongMetadata();
	    song.setBeats_per_rate(120L);
	    song.setTitle("title");
	    song.setCopyright_number("copyright");    
	    song.setPublishing_company("pubCompany"); 
	    song.setPro("pbo");
	    song.setPrimary_genre("primaryGenre");
	    song.setSecondary_genre("secondaryGenre");
	    song.setFileName("Filename");
	    song.setContentURL(null);
		song.setSourceOfContent("HARDDRIVE");
		song.setContent("path/song".getBytes());
		List<Metadata> metadatas = new ArrayList<Metadata>();
		JSONObject jo = new JSONObject();
		jo.put("generalInformation", "copyright");
		jo.put("ownershipInformation", "pubCompany");
		jo.put("soundInformation", "pro");
		metadata.setMetadataJson(jo);
		metadata.setSong(song);
		
		metadatas.add(metadata);
		return metadatas;
	}
	
}
