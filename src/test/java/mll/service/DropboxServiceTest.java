package mll.service;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class DropboxServiceTest 
{
	@Test
	public void testContentFromDropbox1() throws Exception
	{
		DropboxService ds = new DropboxService();
		assertEquals(true, ds.getContentFromDropbox(null) == null);
	}
	
	@Test
	public void testContentFromDropbox2() throws Exception
	{
		DropboxService ds = new DropboxService();
		assertEquals(true, ds.getContentFromDropbox("") == null);
	}
	
	@Test
	public void testContentFromDropbox3() throws Exception
	{
		DropboxService ds = new DropboxService();
		assertEquals(true, ds.getContentFromDropbox("wrongurl") == null);
	}
}
