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
	
	//
	@Test
	public void testDeletePlaylist1()
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
	
	@Test
	public void testAddSongPlaylist1()
	{
		Playlist playlist = null;
		PlaylistDAO dao = new PlaylistDAO();
		try 
		{
			boolean addResult = dao.addSongPlaylist(playlist); 
			assertEquals(false, addResult);
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	@Test
	public void testAddSongPlaylist2()
	{
		Playlist playlist = new Playlist();
		playlist.setPlaylist_id(0);
		PlaylistDAO dao = new PlaylistDAO();
		try 
		{
			boolean addResult = dao.addSongPlaylist(playlist); 
			assertEquals(false, addResult);
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	@Test
	public void testAddSongPlaylist3()
	{
		Playlist playlist = new Playlist();
		playlist.setSong_id(null);
		PlaylistDAO dao = new PlaylistDAO();
		try 
		{
			boolean addResult = dao.addSongPlaylist(playlist); 
			assertEquals(false, addResult);
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	@Test
	public void testGetAllSongsForPlaylist()
	{
		try
		{
			assertEquals(true, new PlaylistDAO().getAllSongsForPlaylist(1)!=null);
		}
		catch(Exception e)
		{

		}
	}

	@Test
	public void testAddSongPlaylist4()
	{
		try
		{			
			assertEquals(false, new PlaylistDAO().addSongPlaylist(null));
		}
		catch(Exception e)
		{

		}
	}

	
	
}
