package mll.dao;

import static org.junit.Assert.assertEquals;

import org.junit.Test;


public class SongRetrievalDAOTest {

	SongRetrievalDAO songretrieval;
	@Test
	public void testretrieveOwnerInfo1()
	{
		songretrieval=new SongRetrievalDAO();
		assertEquals(true, songretrieval.retrieveOwnerInfo(null) == null);
	}
	
	@Test
	public void testretrieveOwnerInfo2()
	{
		songretrieval=new SongRetrievalDAO();
		assertEquals(true, songretrieval.retrieveOwnerInfo("2B8AEAD8749C427FB9FD7CBCF94730F6").length() > 0);
	}
	
	@Test
	public void testretrieveOwnerInfo3()
	{
		songretrieval=new SongRetrievalDAO();
		assertEquals(true, songretrieval.retrieveOwnerInfo("12345").length() ==0);
	}
}
