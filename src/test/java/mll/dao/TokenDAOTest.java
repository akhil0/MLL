package mll.dao;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import mll.beans.Token;

public class TokenDAOTest {
	
	@Test
	public void testGetTokenForTokenId1() 
	{
		try 
		{
			assertEquals(true, new TokenDAO().getTokenForTokenId(null) == null);
			
		} catch (Exception e) 
		{
		
		}
	}
	
	@Test
	public void testGetTokenForTokenId2() 
	{
		try 
		{
			assertEquals(true, new TokenDAO().getTokenForTokenId("") == null);
			
		} catch (Exception e) 
		{
		
		}
	}
	
	@Test
	public void testGetTokenForTokenId3() 
	{
		try 
		{
			Token tokenObj = new Token();
			tokenObj.setId(1);
			String token = tokenObj.getMessageBody();
			token = "ABC";
			assertEquals(true, new TokenDAO().getTokenForTokenId(token) == null);
			
		} catch (Exception e) 
		{
		
		}
	}
}