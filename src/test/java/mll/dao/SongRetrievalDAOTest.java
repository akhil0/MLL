package mll.dao;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;

import org.hibernate.mapping.PersistentClass;
import java.util.*;
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
		Set set=new HashSet( Arrays.asList("2B8AEAD8749C427FB9FD7CBCF94730F6"));
		
		assertEquals(true, songretrieval.retrieveOwnerInfo(set).size() > 0);
	}
	
	@Test
	public void testretrieveOwnerInfo3()
	{
		songretrieval=new SongRetrievalDAO();
		Set set=new HashSet( Arrays.asList("1234"));
		assertEquals(true, songretrieval.retrieveOwnerInfo(set).size() ==0);
	}
}
