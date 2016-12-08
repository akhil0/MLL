package mll.dao;

import static org.junit.Assert.*;
import org.junit.Test;

public class AdminDAOTest {

	@Test
	public void testGetUserName()
	{
		try
		{
			assertEquals(true, new AdminDAO().getUserName(1000000)=="");
		}
		catch(Exception e)
		{
			
		}
	}
	
	@Test
	public void testGetUserName2()
	{
		try
		{
			assertEquals(true, new AdminDAO().getUserName(1)!=null);
		}
		catch(Exception e)
		{
			
		}
	}
}
