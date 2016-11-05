package mll.utility;

import static org.junit.Assert.assertEquals;
import java.util.List;
import org.junit.Test;
import mll.service.MultipartUtility;


public class MultipartUtilityTest 
{
	String charset = "UTF-8";
	String requestURL = "http://localhost:8080/razuna/raz1/dam/index.cfm";
	String fileName = "test";
	String contentString = "test";
	byte[] content = contentString.getBytes();
	
	@Test
	public void testfinish1() throws Exception
	{
		//positive path
		MultipartUtility multipart = new MultipartUtility(requestURL, charset);
		multipart.addFormField("fa", "c.apiupload");
		multipart.addFormField("api_key", "D8766A71A7844D14B71391EB7F047E26");
		multipart.addFormField("destfolderid", "CC5DFF287F274153963FF173C505A956");
		
		multipart.addFilePart(fileName, content);
		List<String> response = multipart.finish();
		assertEquals(true, response.contains("<message>success</message>") == true );
		
	}
	
	@Test
	public void testfinish2() throws Exception
	{
		//without c.apiupload
		MultipartUtility multipart = new MultipartUtility(requestURL, charset);
	
		multipart.addFormField("api_key", "D8766A71A7844D14B71391EB7F047E26");
		multipart.addFormField("destfolderid", "CC5DFF287F274153963FF173C505A956");
		
		multipart.addFilePart(fileName, content);
		List<String> response = multipart.finish();
		System.out.println(response);
		assertEquals(true, response.contains("<message>success</message>") == false );
		
	}
	
	@Test
	public void testfinish3() throws Exception
	{
		// without api key
		MultipartUtility multipart = new MultipartUtility(requestURL, charset);
		multipart.addFormField("fa", "c.apiupload");
		
		multipart.addFormField("destfolderid", "CC5DFF287F274153963FF173C505A956");
		
		multipart.addFilePart(fileName, content);
		List<String> response = multipart.finish();
		assertEquals(true, response.contains("<message>success</message>") == false );
		
	}
	
	@Test
	public void testfinish4() throws Exception
	{
		// without destination folder
		MultipartUtility multipart = new MultipartUtility(requestURL, charset);
		multipart.addFormField("fa", "c.apiupload");
		multipart.addFormField("api_key", "D8766A71A7844D14B71391EB7F047E26");
		
		multipart.addFilePart(fileName, content);
		List<String> response = multipart.finish();
		assertEquals(true, response.contains("<message>success</message>") == false );
		
	}
	
	@Test
	public void testfinish5() throws Exception
	{
		// without filepart
		MultipartUtility multipart = new MultipartUtility(requestURL, charset);
		multipart.addFormField("fa", "c.apiupload");
		multipart.addFormField("api_key", "D8766A71A7844D14B71391EB7F047E26");
		multipart.addFormField("destfolderid", "CC5DFF287F274153963FF173C505A956");
		
		List<String> response = multipart.finish();
		assertEquals(true, response.contains("SUCCESS") == false );
		
	}
}