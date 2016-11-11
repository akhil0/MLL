package mll.dao;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ARDAOTest {

	@Test
	public void testGetMusician1(){ 
	{
		try 
		{
			assertEquals(true, new ARHomePageDAO().getMusicians(-1) == null);
		} 
		catch (Exception e) 
		{
		}
	}
}
	
	@Test
	public void testGetMusician2(){ 
	{
		try 
		{
			assertEquals(true, new ARHomePageDAO().getMusicians(1).size() != 0);
		} 
		catch (Exception e) 
		{
		}
	}
}
	
	@Test
	public void testGetMusicians3(){ 
	{
		try 
		{
			assertEquals(true, new ARHomePageDAO().getMusicians(2).size() == 0);
		} 
		catch (Exception e) 
		{
			
		}
	}	
}

	
	@Test
	public void testGetMusicians4(){ 
	{
		try 
		{
			assertEquals(true, new ARHomePageDAO().getMusicians(100).size() == 0);
		} 
		catch (Exception e) 
		{
			
		}
	}
	
	

}
}

	

