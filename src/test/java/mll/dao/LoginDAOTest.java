package mll.dao;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import mll.beans.Login;
import mll.beans.User;

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
	
	@Test
	public void testValidateLogin8()
	{
		try 
		{
			LoginDAO dao = new LoginDAO();
			Login login = new Login();
			User user = new User();
			user.setUserName("aruser");
			user.setPassword("dbb2ad553b54536d308f7ade07cefbe5");
			login.setUser(user);
			login.setCanBrowse(true);
			login.setCanUpload(false);
			
			assertEquals("user", (dao.validateLogin(login)).getType());
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	
	
	@Test
	public void testValidateLogin9()
	{
		try 
		{
			LoginDAO dao = new LoginDAO();
			Login login = new Login();
			User user = new User();
			user.setUserName("mahanth");
			user.setPassword("dbb2ad553b54536d308f7ade07cefbe5");
			login.setUser(user);
			login.setCanBrowse(true);
			login.setCanUpload(false);
			
			assertEquals("musician", (dao.validateLogin(login)).getType());
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	@Test
	public void testValidateLogin10()
	{
		try 
		{
			LoginDAO dao = new LoginDAO();
			Login login = new Login();
			User user = new User();
			user.setUserName("akhil0");
			user.setPassword("25d55ad283aa400af464c76d713c07ad");
			login.setUser(user);
			login.setCanBrowse(true);
			login.setCanUpload(false);
			
			assertEquals("user", (dao.validateLogin(login)).getType());
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	@Test
	public void testValidateLogin11()
	{
		try 
		{
			LoginDAO dao = new LoginDAO();
			Login login = new Login();
			User user = new User();
			user.setUserName("akhil0");
			user.setPassword("11");
			login.setUser(user);
			login.setCanBrowse(true);
			login.setCanUpload(false);
			
			assertEquals("Username and/or password doesn't match. Please provide valid credentials.", (dao.validateLogin(login)).getErrMsg());
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	
	
}
