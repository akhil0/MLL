package mll.utility;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class EncryptionTest
{

	@Test
	public void testEncryptPassword1() 
	{
		try 
		{
			assertEquals(true, Encryption.encryptPassword(null) == null);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	@Test
	public void testEncryptPassword2() 
	{
		try 
		{
			assertEquals(true, Encryption.encryptPassword("") == null);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	@Test
	public void testEncryptPassword3() 
	{
		try 
		{
			assertEquals(true, Encryption.encryptPassword("Password@123").equals(Encryption.encryptPassword("Password@123")));
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
}
