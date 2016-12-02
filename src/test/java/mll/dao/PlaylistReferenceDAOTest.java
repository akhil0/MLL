package mll.dao;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import mll.dao.PlaylistReferenceDAO;

public class PlaylistReferenceDAOTest {

	@Test
	public void testAddPlaylist1() 
	{
		try 
		{
			PlaylistReferenceDAO dao = new PlaylistReferenceDAO();
			assertEquals(true, dao.addPlaylist(null) == false);
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
			PlaylistReferenceDAO dao = new PlaylistReferenceDAO();
			assertEquals(true, dao.getAllPlaylistsForUserId(-1) == null);
		} 
		catch (Exception e) 
		{
		}
	}
}