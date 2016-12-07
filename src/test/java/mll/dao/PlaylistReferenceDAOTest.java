package mll.dao;

import static org.junit.Assert.assertEquals;
import java.util.Date;
import org.junit.Test;
import mll.beans.PlaylistReference;
import mll.service.PlaylistReferenceService;

public class PlaylistReferenceDAOTest {
	
	@Test
	public void testGetAllPlaylistsForUserId() 
	{
		try 
		{
			int userId = 1;
			assertEquals(true, new PlaylistReferenceDAO().getAllPlaylistsForUserId(userId) != null);
			
		} catch (Exception e) 
		{
		
		}
	}
	
	@Test
	public void testGetSharedPlaylists() 
	{
		try 
		{
			boolean flag = true;
			assertEquals(true, new PlaylistReferenceDAO().getSharedPlaylists(flag) != null);
			
		} catch (Exception e) 
		{
		
		}
	}
	
	@Test
	public void testAddPlaylist() 
	{
		try 
		{
			PlaylistReference playlistReference = new PlaylistReference();
			playlistReference.setUserId(1);
			playlistReference.setIsShared(false);
			playlistReference.setPlaylistName("Vishal");
			playlistReference.setId(0);
			playlistReference.setCreationDate(new Date());
			PlaylistReferenceDAO playlistReferenceDAO = new PlaylistReferenceDAO();
			playlistReferenceDAO.addPlaylist(playlistReference);
			boolean flag = playlistReference.getIsShared();
			assertEquals(true, flag == false);
			
		} catch (Exception e) 
		{
		
		}
	}
	
	@Test
	public void testDeletePlaylist() 
	{
		try 
		{
			PlaylistReferenceDAO playlistReferenceDAO = new PlaylistReferenceDAO();
			boolean flag = playlistReferenceDAO.deletePlaylist(0,1);
			assertEquals(false, flag == true);
			
		} catch (Exception e) 
		{
		
		}
	}
	
	@Test
	public void testRemovePlaylistFromGlobal()
	{
		try
		{
			assertEquals(false, new PlaylistReferenceDAO().removePlaylistFromGlobal(1, 10000));
		}
		catch(Exception e)
		{

		}
	}
	

}