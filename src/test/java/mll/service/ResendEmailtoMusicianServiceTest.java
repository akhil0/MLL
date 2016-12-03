package mll.service;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class ResendEmailtoMusicianServiceTest {
	
	@Test
	public void testGetTokenForTokenId1() 
	{
		try 
		{
			String token = "abc";
			assertEquals(true, new ResendEmailtoMusicianService().sendEmail(token) == false);
			
		} catch (Exception e) 
		{
		
		}
	}
	
}