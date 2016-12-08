package mll.service;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.junit.Test;
import mll.beans.PlaylistReference;
import static org.mockito.Mockito.*;

public class PlaylistReferenceServiceTest {
		
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
	
	@SuppressWarnings("unchecked")
	@Test
	public void testConvertToJson1() 
	{
		try 
		{
			PlaylistReferenceService service = new PlaylistReferenceService();
			List<PlaylistReference> playlists = new ArrayList<PlaylistReference>();
			PlaylistReference playlistReference = new PlaylistReference();
			playlistReference.setId(1);
			playlistReference.setPlaylistName("Test");
			playlistReference.setUserId(1);
			playlistReference.setCreationDate(new Date());
			playlistReference.setIsShared(false);
			playlists.add(playlistReference);
			JSONArray jsonArray = new JSONArray();
			JSONObject object = new JSONObject();
			object.put("id", 1);
			object.put("playlistName", "Test");
			object.put("userId", 1);
			jsonArray.add(object);
			int count = service.convertToJson(playlists).size();
			assertEquals(true, count==jsonArray.size());
		}
		catch(Exception e)
		{
			
		}
	}
	
	
	@Test
	public void testSetPlaylistToGlobal1() 
	{
		try 
		{
			int userId = 0;
			int playlistId = 0;
			assertEquals(true, new PlaylistReferenceService().setPlaylistToGlobal(userId, playlistId) != null);
			
		} catch (Exception e) 
		{
		
		}
	}
	
	@Test
	public void testSetPlaylistToGlobal2() 
	{
		try 
		{
			int userId = 0;
			int playlistId = 10;
			assertEquals(true, new PlaylistReferenceService().setPlaylistToGlobal(userId, playlistId) != null);
			
		} catch (Exception e) 
		{
		
		}
	}
	
	@Test
	public void testAddPlaylistForUser2() 
	{
		try 
		{
			int userId = 1;
			String playlistName = null;
			assertEquals(true, new PlaylistReferenceService().addPlaylistForUser(userId, playlistName) != true);
			
		} catch (Exception e) 
		{
		
		}
	}
	
	@Test
	public void testGetSharedPlaylist1()
	{
		try 
		{
			assertEquals(true, new PlaylistReferenceService().getSharedPlaylists() != null);		
		}
		catch(Exception e)
		{
			
		}
	}
	
	@Test 
	public void testDeletePlaylistForUser1()
	{
		try 
		{
			
			int playlistId = 100000;
			int userId = 100000;			
			assertEquals(false, new PlaylistReferenceService().deletePlaylistForUser(playlistId, userId));
		}
		catch(Exception e)
		{
			
		}
	}
	
	@Test 
	public void testDeletePlaylistForUser2()
	{
		try 
		{
			int playlistId = -1;
			int userId = -1;
			assertEquals(false, new PlaylistReferenceService().deletePlaylistForUser(playlistId, userId));
		}
		catch(Exception e)
		{
			
		}
	}
	
	@Test
	public void testRemoveFromShare()
	{
		try
		{
			assertEquals(true, new PlaylistReferenceService().removeFromShare(1, 10000)!=null);
		}
		catch(Exception e)
		{

		}

	}
	
	@Test
	public void testHandlePlaylistReferenceRequest2()
	{
		try
		{
			HttpServletRequest request = mock(HttpServletRequest.class);
			HttpServletResponse response = mock(HttpServletResponse.class);
			HttpSession session = mock(HttpSession.class);
			when(session.getAttribute("userId")).thenReturn("3"); 
			when(request.getSession()).thenReturn(session);
			when(request.getParameter("actionType")).thenReturn("add");
			when(request.getParameter("playlistName")).thenReturn("mlltest1");
			
			JSONObject jsonObject = new PlaylistReferenceService().handlePlaylistReferenceRequest(request, response);
			assertEquals(true, jsonObject!=null);
		}
		catch(Exception e)
		{
			
		}
	}
	
	@Test
	public void testHandlePlaylistReferenceRequest3()
	{
		try
		{
			HttpServletRequest request = mock(HttpServletRequest.class);
			HttpServletResponse response = mock(HttpServletResponse.class);
			HttpSession session = mock(HttpSession.class);
			when(session.getAttribute("userId")).thenReturn("3"); 
			when(request.getSession()).thenReturn(session);
			when(request.getParameter("actionType")).thenReturn("shared");
			JSONObject jsonObject = new PlaylistReferenceService().handlePlaylistReferenceRequest(request, response);
			assertEquals(true, jsonObject!=null);
		}
		catch(Exception e)
		{
			
		}
	}
	
	@Test
	public void testHandlePlaylistReferenceRequest4()
	{
		try
		{
			HttpServletRequest request = mock(HttpServletRequest.class);
			HttpServletResponse response = mock(HttpServletResponse.class);
			HttpSession session = mock(HttpSession.class);
			when(session.getAttribute("userId")).thenReturn("3"); 
			when(request.getSession()).thenReturn(session);
			when(request.getParameter("actionType")).thenReturn("get");
			JSONObject jsonObject = new PlaylistReferenceService().handlePlaylistReferenceRequest(request, response);
			assertEquals(true, jsonObject!=null);
		}
		catch(Exception e)
		{
			
		}
	}
	
	@Test
	public void testHandlePlaylistReferenceRequest5()
	{
		try
		{
			HttpServletRequest request = mock(HttpServletRequest.class);
			HttpServletResponse response = mock(HttpServletResponse.class);
			HttpSession session = mock(HttpSession.class);
			when(session.getAttribute("userId")).thenReturn("3"); 
			when(request.getSession()).thenReturn(session);
			when(request.getParameter("playlistId")).thenReturn("100000");
			when(request.getParameter("actionType")).thenReturn("delete");
			JSONObject jsonObject = new PlaylistReferenceService().handlePlaylistReferenceRequest(request, response);
			assertEquals(true, jsonObject!=null);
		}
		catch(Exception e)
		{
			
		}
	}
	
	@Test
	public void testHandlePlaylistReferenceRequest6()
	{
		try
		{
			HttpServletRequest request = mock(HttpServletRequest.class);
			HttpServletResponse response = mock(HttpServletResponse.class);
			HttpSession session = mock(HttpSession.class);
			when(session.getAttribute("userId")).thenReturn("3"); 
			when(request.getSession()).thenReturn(session);
			when(request.getParameter("actionType")).thenReturn("addToShare");
			JSONObject jsonObject = new PlaylistReferenceService().handlePlaylistReferenceRequest(request, response);
			assertEquals(true, jsonObject!=null);
		}
		catch(Exception e)
		{
			
		}
	}
	
	@Test
	public void testHandlePlaylistReferenceRequest7()
	{
		try
		{
			HttpServletRequest request = mock(HttpServletRequest.class);
			HttpServletResponse response = mock(HttpServletResponse.class);
			HttpSession session = mock(HttpSession.class);
			when(session.getAttribute("userId")).thenReturn("3"); 
			when(request.getSession()).thenReturn(session);
			when(request.getParameter("actionType")).thenReturn("unShare");
			when(request.getParameter("playlistId")).thenReturn("10000");
			JSONObject jsonObject = new PlaylistReferenceService().handlePlaylistReferenceRequest(request, response);
			assertEquals(true, jsonObject!=null);
		}
		catch(Exception e)
		{
			
		}
	}
	
}