package mll.dao;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import mll.beans.Playlist;

public class PlaylistDAOTest 
{
	
	@Test
	public void testAddSongPlaylist2()
	{
		Playlist playlist = new Playlist();
		playlist.setSong_id("Abcdef");;
		playlist.setPlaylist_id(10003);
		PlaylistDAO dao = new PlaylistDAO();
		try 
		{
			boolean addResult = dao.addSongPlaylist(playlist); 
			assertEquals(true, addResult);
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	
	@Test
	public void testDeleteSongPlaylist()
	{
		try
		{
			assertEquals(true, new PlaylistDAO().deleteSongPlaylist(10003, "Abcdef")!=null);
		}
		catch(Exception e)
		{
			
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
	public void testGetAllSongsForPlaylist1()
	{
		try
		{
			assertEquals(true, new PlaylistDAO().getAllSongsForPlaylist(1)==null);
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

	@Test
	public void testAddSongPlaylist5()
	{
		try
		{	
			Playlist playlist = new Playlist();
			playlist.setSong_id(null);
			playlist.setPlaylist_id(-1);
			assertEquals(true, new PlaylistDAO().addSongPlaylist(playlist) == false);
		}
		catch(Exception e)
		{

		}
	}
	
	@Test
	public void testAddSongPlaylist6()
	{
		try
		{			
			Playlist playlist = new Playlist();
			playlist.setPlaylist_id(-1);
			playlist.setSong_id("abc");
			assertEquals(true, new PlaylistDAO().addSongPlaylist(playlist)==false);
		}
		catch(Exception e)
		{

		}
	}
	
}
