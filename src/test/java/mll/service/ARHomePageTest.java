package mll.service;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ARHomePageTest {
	

	@Test
	public void testGetRecent() throws Exception
	{
		ARHomePage ds = new ARHomePage();
		assertEquals(true, ds.getRecent() == null);
	}

	@Test
	public void testGetRecent1() throws Exception
	{
		ARHomePage ds = new ARHomePage();
		assertEquals(true, ds.getRecent().size() < 5);
	}

	@Test
	public void testGetRecent2() throws Exception
	{
		ARHomePage ds = new ARHomePage();
		assertEquals(true, ds.getRecent().size() == 5);
	}
	
	@Test
	public void testGetRecent3() throws Exception
	{
		ARHomePage ds = new ARHomePage();
		assertEquals(false, ds.getRecent().size() > 5);
	}	
	
	
	@Test
	public void testGenre1() throws Exception
	{
		ARHomePage ds = new ARHomePage();
		assertEquals(false, ds.getGenre("StringWithAudioFileAndMetaData") == null);
	}
	
	
	@Test
	public void testGenre2() throws Exception
	{
		ARHomePage ds = new ARHomePage();
		assertEquals(true, ds.getGenre("StringWithAudioFileAndMetaData") == "Rock");
	}

	
	@Test
	public void testgetUserWhoUploaded1() throws Exception
	{
		ARHomePage ds = new ARHomePage();
		assertEquals(true, ds.getUserWhoUploaded("StringWithAudioFileAndMetaData") == "5381947sdds82");
	}
	
	@Test
	public void testgetUserWhoUploaded2() throws Exception
	{
		ARHomePage ds = new ARHomePage();
		assertEquals(false, ds.getUserWhoUploaded("StringWithAudioFileAndMetaData") == "null");
	}

	
}
