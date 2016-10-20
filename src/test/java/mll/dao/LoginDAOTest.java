package mll.dao;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import mll.beans.Login;

public class LoginDAOTest 
{
	@Test
	public void testValidateLogin1()
	{
		try 
		{
			LoginDAO dao = new LoginDAO();
			assertEquals(true, dao.validateLogin(null) == null);
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	@Test
	public void testValidateLogin2()
	{
		try 
		{
			LoginDAO dao = new LoginDAO();
			Login login = new Login();
			login.setUser(null);
			assertEquals(true, dao.validateLogin(login) == login);
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	@Test
	public void testValidateLogin3()
	{
		try 
		{
			LoginDAO dao = new LoginDAO();
			Login login = new Login();
			login.getUser().setUserName(null);
			assertEquals(true, dao.validateLogin(login) == login);
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	@Test
	public void testValidateLogin4()
	{
		try 
		{
			LoginDAO dao = new LoginDAO();
			Login login = new Login();
			login.getUser().setPassword(null);
			assertEquals(true, dao.validateLogin(login) == login);
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	@Test
	public void testValidateLogin5()
	{
		try 
		{
			LoginDAO dao = new LoginDAO();
			Login login = new Login();
			login.getUser().setUserName("");
			assertEquals(true, dao.validateLogin(login) == login);
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	@Test
	public void testValidateLogin6()
	{
		try 
		{
			LoginDAO dao = new LoginDAO();
			Login login = new Login();
			login.getUser().setPassword("");
			assertEquals(true, dao.validateLogin(login) == login);
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	@Test
	public void testValidateLogin7()
	{
		try 
		{
			LoginDAO dao = new LoginDAO();
			Login login = new Login();
			login.getUser().setPassword("AnyPassword");
			login.getUser().setUserName("AnyUsername");
			assertEquals(true, dao.validateLogin(login).isValidUser() == false);
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
}
