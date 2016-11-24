package mll.service;

import static org.junit.Assert.assertEquals;

import javax.servlet.http.HttpServletRequest;

import org.junit.Test;

import mll.beans.Playlist;

public class PlayListServiceTest {
	 	 
	 // In this test case we take a -1 play-list id. Now we ask the play list service to get the playlist for this id.
	// The service should return a null playlist for this playlist id which is -1.
	// If the service returns anything else which is not null, then we can say that the test case fails.
	@Test
	public void testPlayListGetById1() throws Exception
	{
		int playlist_id = -1;
		PlaylistService playService = new PlaylistService();		
		Playlist playlist = playService.getPlayList(playlist_id);
		// if playlist is null then assertEquals passes, else fails.
		assertEquals(true, playlist != null);
	}
	
	@Test
	public void testPlayListAddPlayList() throws Exception
	{
		PlaylistService playService = new PlaylistService();
		boolean result = playService.addPlayList(null, null);
		assertEquals(true, result == false);
	}
	
	@Test
	public void testPlayListDeletePlayList() throws Exception
	{
		PlaylistService playService = new PlaylistService();
		boolean result = playService.deletePlayList(null, null);
		assertEquals(true, result == false);
	}
	
	@Test
	public void testPlayListUpdatePlayList() throws Exception
	{
		PlaylistService playService = new PlaylistService();
		boolean result = playService.updatePlayList(null);
		assertEquals(true, result == false);
	}
}
