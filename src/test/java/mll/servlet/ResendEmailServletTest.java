package mll.servlet;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.junit.Test;

import mll.servlets.ResendEmailServlet;

public class ResendEmailServletTest {

	@Test
	public void testResendServlet()
	{
		try
		{
			HttpServletRequest request = mock(HttpServletRequest.class);
			HttpServletResponse response = mock(HttpServletResponse.class);
			HttpSession session = mock(HttpSession.class);
			when(session.getAttribute("userId")).thenReturn("3"); 
			when(request.getSession()).thenReturn(session);
			when(session.getAttribute("token")).thenReturn("MLLTKN11");
			new ResendEmailServlet().doGet(request, response);
		}
		catch(Exception e)
		{
			
		}
	}
	
	@Test
	public void testResendServlet1()
	{
		try
		{
			HttpServletRequest request = mock(HttpServletRequest.class);
			HttpServletResponse response = mock(HttpServletResponse.class);
			HttpSession session = mock(HttpSession.class);
			when(session.getAttribute("userId")).thenReturn("null"); 
			when(request.getSession()).thenReturn(session);
			when(session.getAttribute("token")).thenReturn("null");
			new ResendEmailServlet().doGet(request, response);
		}
		catch(Exception e)
		{
			
		}
	}
}
