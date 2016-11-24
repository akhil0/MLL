package mll.dao;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import mll.beans.Playlist;

public class PlaylistDAOTest 
{
	@Test
	public void testAddPlaylist()
	{
		Playlist playlist = null;
		PlaylistDAO dao = new PlaylistDAO();
		try 
		{
			boolean addResult = dao.addPlaylist(playlist); 
			assertEquals(false, addResult);
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	@Test
	public void testGetAllPlaylistForUserId()
	{
		PlaylistDAO dao = new PlaylistDAO();
		try 
		{
			List<Playlist> result = dao.getAllPlaylistsForUserId(-1); 
			assertEquals(true, result != null);
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	
	@Test
	public void testGetAllPlaylistForPlaylistId()
	{
		PlaylistDAO dao = new PlaylistDAO();
		try 
		{
			List<Playlist> result = dao.getPlaylistsForPlayListId(-1); 
			assertEquals(true, result != null);
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	@Test
	public void testDeletePlaylist()
	{
		Playlist playlist = null;
		PlaylistDAO dao = new PlaylistDAO();
		try 
		{
			boolean addResult = dao.deletePlaylist(playlist); 
			assertEquals(false, addResult);
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
}
