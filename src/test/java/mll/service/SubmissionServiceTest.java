package mll.service;

import static org.junit.Assert.assertEquals;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.junit.Test;

import mll.beans.Metadata;


public class SubmissionServiceTest 
{
	@Test
	public void testPopulateSong1() throws Exception
	{
		SubmissionService service = new SubmissionService();
		assertEquals(true, service.populateSong(null, null, null, null, null) == null);
	}
	
	@Test
	public void testPopulateSong2() throws Exception
	{
		SubmissionService service = new SubmissionService();
		Metadata metadata = new Metadata();
		assertEquals(true, service.populateSong(metadata, getGeneralInfoJsonObject(), null, null, null) == metadata);
	}
	
	@Test
	public void testPopulateSong3() throws Exception
	{
		SubmissionService service = new SubmissionService();
		Metadata metadata = new Metadata();
		assertEquals(true, service.populateSong(metadata, null, getOwnerInfoJsonObject(), null, null) == metadata);
	}
	
	@Test
	public void testPopulateSong4() throws Exception
	{
		SubmissionService service = new SubmissionService();
		Metadata metadata = new Metadata();
		assertEquals(true, service.populateSong(metadata, getGeneralInfoJsonObject(), getOwnerInfoJsonObject(), "dropboxURL", null).getSong().getTitle().equals("My heart will go on"));
	}
	
	@Test
	public void testPopulateSong5() throws Exception
	{
		SubmissionService service = new SubmissionService();
		Metadata metadata = new Metadata();
		assertEquals(true, service.populateSong(metadata, getGeneralInfoJsonObject(), getOwnerInfoJsonObject(), "dropboxURL", null).getSong().getCopyrightNo().equals("copyright"));
	}
	
	@Test
	public void testPopulateSong6() throws Exception
	{
		SubmissionService service = new SubmissionService();
		Metadata metadata = new Metadata();
		assertEquals(true, service.populateSong(metadata, getGeneralInfoJsonObject(), getOwnerInfoJsonObject(), "dropboxURL", null).getSong().getSourceOfContent().equals("DROPBOX"));
	}
	
	@Test
	public void testPopulateSong7() throws Exception
	{
		SubmissionService service = new SubmissionService();
		Metadata metadata = new Metadata();
		assertEquals(true, service.populateSong(metadata, getGeneralInfoJsonObject(), getOwnerInfoJsonObject(), null, new byte[1024]).getSong().getSourceOfContent().equals("HARDDRIVE"));
	}
	
	@Test
	public void testPopulateSongGenres1() throws Exception
	{
		SubmissionService service = new SubmissionService();
		assertEquals(true, service.populateSongGenres(null, null) == null);
	}
	
	@Test
	public void testPopulateSongGenres2() throws Exception
	{
		SubmissionService service = new SubmissionService();
		Metadata metadata = new Metadata();
		assertEquals(true, service.populateSongGenres(metadata, null) == metadata);
	}
	
	@Test
	public void testPopulateSongGenres3() throws Exception
	{
		SubmissionService service = new SubmissionService();
		Metadata metadata = new Metadata();
		assertEquals(true, service.populateSongGenres(metadata, getGenresJsonObject()).getGenres().size() == 2);
	}
	
	@Test
	public void testPopulateSongGenres4() throws Exception
	{
		SubmissionService service = new SubmissionService();
		Metadata metadata = new Metadata();
		assertEquals("Alternative", service.populateSongGenres(metadata, getGenresJsonObject()).getGenres().get(0).getGenre());
	}
	
	@Test
	public void testPopulateSongGenres5() throws Exception
	{
		SubmissionService service = new SubmissionService();
		Metadata metadata = new Metadata();
		assertEquals("Classical", service.populateSongGenres(metadata, getGenresJsonObject()).getGenres().get(1).getGenre());
	}
	
	@Test
	public void testPopulateSongArtists1() throws Exception
	{
		SubmissionService service = new SubmissionService();
		assertEquals(true, service.populateSongArtists(null, null) == null);
	}
	
	@Test
	public void testPopulateSongArtists2() throws Exception
	{
		SubmissionService service = new SubmissionService();
		Metadata metadata = new Metadata();
		assertEquals(true, service.populateSongArtists(metadata, null) == metadata);
	}
	
	@Test
	public void testPopulateSongArtists3() throws Exception
	{
		SubmissionService service = new SubmissionService();
		Metadata metadata = new Metadata();
		assertEquals(true, service.populateSongArtists(metadata, getArtistJsonObject()).getArtists().size() == 1);
	}
	
	@Test
	public void testPopulateSongArtists4() throws Exception
	{
		SubmissionService service = new SubmissionService();
		Metadata metadata = new Metadata();
		assertEquals(true, service.populateSongArtists(metadata, getArtistJsonObject()).getArtists().get(0).getName().equals("ArtistName"));
	}
	
	@Test
	public void testPopulateSongRecorders1() throws Exception
	{
		SubmissionService service = new SubmissionService();
		assertEquals(true, service.populateSongRecorders(null, null) == null);
	}
	
	@Test
	public void testPopulateSongRecorders2() throws Exception
	{
		SubmissionService service = new SubmissionService();
		Metadata metadata = new Metadata();
		assertEquals(true, service.populateSongRecorders(metadata, null) == metadata);
	}
	
	@Test
	public void testPopulateSongRecorders3() throws Exception
	{
		SubmissionService service = new SubmissionService();
		Metadata metadata = new Metadata();
		assertEquals(true, service.populateSongRecorders(metadata, getRecorderJsonObject()).getOwners().size() == 1);
	}
	
	@Test
	public void testPopulateSongRecorders4() throws Exception
	{
		SubmissionService service = new SubmissionService();
		Metadata metadata = new Metadata();
		assertEquals("Test", service.populateSongRecorders(metadata, getRecorderJsonObject()).getOwners().get(0).getName());
	}
	
	@Test
	public void testPopulateSongRecorders5() throws Exception
	{
		SubmissionService service = new SubmissionService();
		Metadata metadata = new Metadata();
		assertEquals("primary@neu.edu", service.populateSongRecorders(metadata, getRecorderJsonObject()).getOwners().get(0).getPrimaryEmail());
	}
	
	@Test
	public void testPopulateSongRecorders6() throws Exception
	{
		SubmissionService service = new SubmissionService();
		Metadata metadata = new Metadata();
		assertEquals("123-456-7890", service.populateSongRecorders(metadata, getRecorderJsonObject()).getOwners().get(0).getPrimaryPhone());
	}
	
	@Test
	public void testPopulateSongWriters1() throws Exception
	{
		SubmissionService service = new SubmissionService();
		assertEquals(true, service.populateSongWriters(null, null) == null);
	}
	
	@Test
	public void testPopulateSongWriters2() throws Exception
	{
		SubmissionService service = new SubmissionService();
		Metadata metadata = new Metadata();
		assertEquals(true, service.populateSongWriters(metadata, null) == metadata);
	}
	
	@Test
	public void testPopulateSongWriters3() throws Exception
	{
		SubmissionService service = new SubmissionService();
		Metadata metadata = new Metadata();
		assertEquals(true, service.populateSongWriters(metadata, getWriterJsonObject()).getOwners().size() == 1);
	}
	
	@Test
	public void testPopulateSongWriters4() throws Exception
	{
		SubmissionService service = new SubmissionService();
		Metadata metadata = new Metadata();
		assertEquals("Test", service.populateSongWriters(metadata, getWriterJsonObject()).getOwners().get(0).getName());
	}
	
	@Test
	public void testPopulateSongWriters5() throws Exception
	{
		SubmissionService service = new SubmissionService();
		Metadata metadata = new Metadata();
		assertEquals("primary@neu.edu", service.populateSongWriters(metadata, getWriterJsonObject()).getOwners().get(0).getPrimaryEmail());
	}
	
	@Test
	public void testPopulateSongWriters6() throws Exception
	{
		SubmissionService service = new SubmissionService();
		Metadata metadata = new Metadata();
		assertEquals("123-456-7890", service.populateSongWriters(metadata, getWriterJsonObject()).getOwners().get(0).getPrimaryPhone());
	}
	
	@SuppressWarnings("unchecked")
	public JSONObject getGeneralInfoJsonObject()
	{
		JSONObject jo = new JSONObject();
		jo.put("beatRate", 3L);
		jo.put("title", "My heart will go on");
		return jo;
	}
	
	@SuppressWarnings("unchecked")
	public JSONObject getOwnerInfoJsonObject()
	{
		JSONObject jo = new JSONObject();
		jo.put("copyright", "copyright");
		jo.put("pubCompany", "pubCompany");
		jo.put("pbo", "pro");
		return jo;
	}
	
	@SuppressWarnings("unchecked")
	public JSONObject getGenresJsonObject()
	{
		JSONObject jo = new JSONObject();
		jo.put("primaryGenre", "Alternative");
		jo.put("secondaryGenre", "Classical");
		return jo;
	}
	
	@SuppressWarnings("unchecked")
	public JSONObject getArtistJsonObject()
	{
		JSONObject jo = new JSONObject();
		jo.put("name", "ArtistName");

		JSONArray artists = new JSONArray();
		artists.add(jo);
		
		JSONObject input = new JSONObject();
		input.put("artists", artists);
		return input;
	}
	
	@SuppressWarnings("unchecked")
	public JSONObject getRecorderJsonObject()
	{
		JSONObject jo = new JSONObject();
		jo.put("name", "Test");
		jo.put("primaryEmail", "primary@neu.edu");
		jo.put("secondaryEmail", "secondary@neu.edu");
		jo.put("primaryPhone", "123-456-7890");
		jo.put("secondaryPhone", "012-345-6789");
		
		JSONArray recorders = new JSONArray();
		recorders.add(jo);
		
		JSONObject input = new JSONObject();
		input.put("soundOwners", recorders);
		return input;
	}
	
	@SuppressWarnings("unchecked")
	public JSONObject getWriterJsonObject()
	{
		JSONObject jo = new JSONObject();
		jo.put("name", "Test");
		jo.put("primaryEmail", "primary@neu.edu");
		jo.put("secondaryEmail", "secondary@neu.edu");
		jo.put("primaryPhone", "123-456-7890");
		jo.put("secondaryPhone", "012-345-6789");
		
		JSONArray recorders = new JSONArray();
		recorders.add(jo);
		
		JSONObject input = new JSONObject();
		input.put("songwriters", recorders);
		return input;
	}
}

	
