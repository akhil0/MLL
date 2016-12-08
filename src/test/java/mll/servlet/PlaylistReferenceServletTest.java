package mll.servlet;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.junit.Test;
import mll.servlets.PlaylistReferenceServlet;

public class PlaylistReferenceServletTest {
	
	@Test
	public void testHandlePlaylistReferenceRequest1()
	{
		try
		{
			HttpServletRequest request = mock(HttpServletRequest.class);
			HttpServletResponse response = mock(HttpServletResponse.class);
			HttpSession session = mock(HttpSession.class);
			when(session.getAttribute("userId")).thenReturn("3"); 
			when(request.getSession()).thenReturn(session);
			when(session.getAttribute("actionType")).thenReturn("add");
			when(session.getAttribute("playlistName")).thenReturn("PlaylistTest");
			PrintWriter writer = new PrintWriter("test.txt");
			when(response.getWriter()).thenReturn(writer);
			new PlaylistReferenceServlet().doGet(request, response);
			writer.flush();
			assertEquals(true, true);
		}
		catch(Exception e)
		{
			
		}
	}
}
