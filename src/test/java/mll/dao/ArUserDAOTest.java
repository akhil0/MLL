package mll.dao;

import static org.junit.Assert.*;
import org.junit.Test;

public class ArUserDAOTest {

	@Test
	public void testGetUserName()
	{
		try
		{
			assertEquals(true, new ArUserDAO().getUserName(1000000)=="");
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
			assertEquals(true, new ArUserDAO().getUserName(1)!=null);
		}
		catch(Exception e)
		{
			
		}
	}
}
