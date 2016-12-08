package mll.servlet;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.junit.Test;
import mll.servlets.PlaylistServlet;

public class PlaylistServletTest {

	@Test
	public void testHandlePlaylistReferenceRequestAdd()
	{
		try
		{
			HttpServletRequest request = mock(HttpServletRequest.class);
			HttpServletResponse response = mock(HttpServletResponse.class);
			HttpSession session = mock(HttpSession.class);
			when(session.getAttribute("userId")).thenReturn("3"); 
			when(request.getSession()).thenReturn(session);
			when(request.getParameter("actionType")).thenReturn("add");
			when(request.getParameter("playlistId")).thenReturn("1");
			when(request.getParameter("assetId")).thenReturn("FCD433C107A14F0D9FDE89A9A4DFF9E6");
			
			new PlaylistServlet().doGet(request, response);
		}
		catch(Exception e)
		{
			
		}
	}
	
	
	@Test
	public void testHandlePlaylistReferenceRequestDelete()
	{
		try
		{
			HttpServletRequest request = mock(HttpServletRequest.class);
			HttpServletResponse response = mock(HttpServletResponse.class);
			HttpSession session = mock(HttpSession.class);
			when(session.getAttribute("userId")).thenReturn("3"); 
			when(request.getSession()).thenReturn(session);
			when(request.getParameter("actionType")).thenReturn("delete");
			when(request.getParameter("playlistId")).thenReturn("1");
			when(request.getParameter("assetId")).thenReturn("FCD433C107A14F0D9FDE89A9A4DFF9E6");
			
			new PlaylistServlet().doGet(request, response);
		}
		catch(Exception e)
		{
			
		}
	}
	
	
	@Test
	public void testHandlePlaylistReferenceRequestGet()
	{
		try
		{
			HttpServletRequest request = mock(HttpServletRequest.class);
			HttpServletResponse response = mock(HttpServletResponse.class);
			HttpSession session = mock(HttpSession.class);
			when(session.getAttribute("userId")).thenReturn("3"); 
			when(request.getSession()).thenReturn(session);
			when(request.getParameter("actionType")).thenReturn("get");
			when(request.getParameter("playlistId")).thenReturn("1");
			when(request.getParameter("assetId")).thenReturn("FCD433C107A14F0D9FDE89A9A4DFF9E6");
			
			new PlaylistServlet().doGet(request, response);
		}
		catch(Exception e)
		{
			
		}
	}
	
	@Test
	public void testHandlePlaylistReferenceRequestNothing()
	{
		try
		{
			HttpServletRequest request = mock(HttpServletRequest.class);
			HttpServletResponse response = mock(HttpServletResponse.class);
			HttpSession session = mock(HttpSession.class);
			when(session.getAttribute("userId")).thenReturn("3"); 
			when(request.getSession()).thenReturn(session);
			when(request.getParameter("actionType")).thenReturn("");
			when(request.getParameter("playlistId")).thenReturn("1");
			when(request.getParameter("assetId")).thenReturn("FCD433C107A14F0D9FDE89A9A4DFF9E6");
			
			new PlaylistServlet().doGet(request, response);
		}
		catch(Exception e)
		{
			
		}
	}
	
	@Test
	public void testHandlePlaylistReferenceRequestAdd2()
	{
		try
		{
			HttpServletRequest request = mock(HttpServletRequest.class);
			HttpServletResponse response = mock(HttpServletResponse.class);
			HttpSession session = mock(HttpSession.class);
			when(session.getAttribute("userId")).thenReturn("3"); 
			when(request.getSession()).thenReturn(session);
			when(request.getParameter("actionType")).thenReturn("addToMyPlaylist");
			when(request.getParameter("playlistId")).thenReturn("1");
			when(request.getParameter("assetId")).thenReturn("FCD433C107A14F0D9FDE89A9A4DFF9E6");
			
			new PlaylistServlet().doGet(request, response);
		}
		catch(Exception e)
		{
			
		}
	}
	
	@Test
	public void testHandlePlaylistReferenceRequestAdd3()
	{
		try
		{
			HttpServletRequest request = mock(HttpServletRequest.class);
			HttpServletResponse response = mock(HttpServletResponse.class);
			HttpSession session = mock(HttpSession.class);
			when(session.getAttribute("userId")).thenReturn("3"); 
			when(request.getSession()).thenReturn(session);
			when(request.getParameter("actionType")).thenReturn("addToMyPlaylist");
			when(request.getParameter("playlistId")).thenReturn("1");
			when(request.getParameter("assetId")).thenReturn("FCD433C107A14F0D9FDE89A9A4DFF9E6");
			
			new PlaylistServlet().doPost(request, response);
		}
		catch(Exception e)
		{
			
		}
	}
}
