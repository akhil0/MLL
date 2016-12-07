package mll.service;

import static org.junit.Assert.assertEquals;

import javax.servlet.http.HttpServletRequest;

import org.junit.Test;

import mll.beans.Playlist;

public class PlayListServiceTest {
	 	 
	
	@Test
	public void testAddSongPlaylist1() throws Exception
	{
		PlaylistService playService = new PlaylistService();
		boolean result = playService.addSongPlaylist(1, 1, null);
		assertEquals(true, result == false);
	}
	
	@Test
	public void testAddSongPlaylist2() throws Exception
	{
		PlaylistService playService = new PlaylistService();
		boolean result = playService.addSongPlaylist(1, 1, "");
		assertEquals(true, result == false);
	}
	
	@Test
	public void testGetSongsFromPlaylist() 
	{
		try 
		{
			assertEquals(true, new PlaylistService().getSongsFromPlaylist(1) != null);
		}
		catch(Exception e)
		{
			
		}
	}
	
	@Test
	public void testAddSongPlaylist()
	{
		try
		{
			assertEquals(true, new PlaylistService().addSongPlaylist(100000, 100001, "abc"));
		}
		catch(Exception e)
		{

		}
	}
	
	@Test
	public void testDeleteSongPlaylist1()
	{
		try
		{
			assertEquals(true, new PlaylistService().deleteSongPlaylist(100000, 100001, "abc") != null);
		}
		catch(Exception e)
		{

		}
	}
	
}
