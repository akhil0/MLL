package mll.utility;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class SessionFactoryUtilTest 
{
	@Test
	// Test weather session factory creates a new hibernate session or not.
	public void testSessionFactory() 
	{
		assertEquals(true, SessionFactoryUtil.getSessionFactory() != null);
	}
}