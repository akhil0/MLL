package mll.service;

import static org.junit.Assert.assertEquals;
import org.junit.Test;


public class PlaylistReferenceServiceTest {
	
	@Test
	public void testAddPlaylist1() 
	{
		try 
		{
			PlaylistReferenceService service = new PlaylistReferenceService();
			assertEquals(true, service.addPlaylistForUser(-1, "Test") == false);
		} 
		catch (Exception e) 
		{
		}
	}
	
	@Test
	public void testGetAllPlaylistsForUserId1()
	{
		try 
		{
			PlaylistReferenceService service = new PlaylistReferenceService();
			assertEquals(true, service.getAllPlaylistsForUser(-1) == null);
		} 
		catch (Exception e) 
		{
		}
	}
}