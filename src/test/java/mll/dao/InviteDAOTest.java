package mll.dao;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import mll.beans.Invite;

public class InviteDAOTest 
{
	
	@Test
	public void testGenerateInvite1() 
	{
		try 
		{
			InviteDAO dao = new InviteDAO();
			assertEquals(true, dao.generateInvite(null) == null);
		} 
		catch (Exception e) 
		{
		}
	}
	
	@Test
	public void testGenerateInvite2() 
	{
		try 
		{
			InviteDAO dao = new InviteDAO();
			assertEquals(true, dao.generateInvite(new Invite()).getIsGenerated() == false);
		} 
		catch (Exception e) 
		{
		}
	}
	
	@Test
	public void testGenerateInvite3() 
	{
		try 
		{
			InviteDAO dao = new InviteDAO();
			assertEquals(true, dao.generateInvite(new Invite()).getToken().getToken() == null);
		} 
		catch (Exception e) 
		{
		}
	}
	
	@Test
	public void testValidateInvite1() 
	{
		try 
		{
			InviteDAO dao = new InviteDAO();
			assertEquals(true, dao.validateInvite(null) == null);
		} 
		catch (Exception e) 
		{
		}
	}
	
	@Test
	public void testValidateInvite2() 
	{
		try 
		{
			InviteDAO dao = new InviteDAO();
			assertEquals(true, dao.validateInvite(new Invite()).getIsValid() == false);
		} 
		catch (Exception e) 
		{
		}
	}
	
	@Test
	public void testValidateInvite3() 
	{
		try 
		{
			InviteDAO dao = new InviteDAO();
			assertEquals(true, dao.validateInvite(new Invite()).getMessage().equals("Invalid Token."));
		} 
		catch (Exception e) 
		{
		}
	}
	
	
	@Test
    public void testCheckEmail1() {

        try 
        {
            InviteDAO dao = new InviteDAO();
            assertEquals(true, dao.checkEmailId("xyz@gmail.com") == true);
        }
        catch(Exception e)
        {            
        }
    }

    public void testCheckEmail2() {
        
        try 
        {
            InviteDAO dao = new InviteDAO();
            assertEquals(true, dao.checkEmailId(null)==true);
        }
        catch(Exception e)
        {
            
        }
    }


     @Test
    public void testCheckEmail3() {
        
        try 
        {
            InviteDAO dao = new InviteDAO();
            assertEquals(true, dao.checkEmailId("")==true);
        }
        catch(Exception e)
        {
            
        }
    }   
        
}
