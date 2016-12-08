package mll.service;

import static org.junit.Assert.assertEquals;

import org.json.simple.JSONObject;
import org.junit.Test;

import mll.beans.ARuser;

public class ARHomePageTest {
	

	
	@Test
	public void testGetRegisteredMusician1(){ 
		{
			try 
			{
				ARuser arUser = new ARuser();
				arUser.setId(-3);
				assertEquals(true, new ARHomePageService().getRegisteredMusicians(arUser) != null);
			} 
			catch (Exception e) 
			{
			}
		}
	}
	
	@Test
	public void testGetRegisteredMusician2(){ 
		{
			try 
			{
				ARuser arUser = new ARuser();
				arUser.setId(1);
				assertEquals(true, new ARHomePageService().getRegisteredMusicians(arUser) != null);
			} 
			catch (Exception e) 
			{
			}
		}
	}
	
	@Test
	public void testGetUnRegisteredMusician1(){ 
		{
			try 
			{
				ARuser arUser = new ARuser();
				arUser.setId(1);
				assertEquals(true, new ARHomePageService().getRegisteredMusicians(arUser) != null);
			} 
			catch (Exception e) 
			{
			}
		}
	}
	
	@Test
	public void testGetUnRegisteredMusician2(){ 
		{
			try 
			{
				ARuser arUser = new ARuser();
				arUser.setId(-1);
				assertEquals(true, new ARHomePageService().getRegisteredMusicians(arUser) != null);
			} 
			catch (Exception e) 
			{
			}
		}
	}
	
	@Test
	public void testGetMusiciansWithId1(){ 
		{
			try 
			{
				assertEquals(true, new ARHomePageService().getMusiciansWithId("3") != null);
			} 
			catch (Exception e) 
			{
			}
		}
	}	

	@Test
	public void testGetMusiciansWithId2(){ 
		{
			try 
			{				
				JSONObject json = new JSONObject();
				assertEquals(true, new ARHomePageService().getMusiciansWithId("qwe").equals(json));
			} 
			catch (Exception e) 
			{
			}
		}
	}
	

	
}
