package mll.service;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ImportPlaylistServiceTest {

	@Test
	public void testGetAllPlaylistsForUserId() 
	{
		try 
		{
			assertEquals(true, new ImportPlaylistService().importPlaylist(13, 2) == true);
			
		} catch (Exception e) 
		{
		
		}
	}

	
}
