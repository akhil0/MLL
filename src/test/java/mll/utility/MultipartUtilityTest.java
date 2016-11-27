package mll.utility;

import static org.junit.Assert.assertEquals;
import java.util.List;
import org.junit.Test;

import mll.service.RazunaService;
import mll.utility.MultipartUtility;


public class MultipartUtilityTest 
{
	String charset = "UTF-8";
	String fileName = "test";
	String contentString = "test";
	byte[] content = contentString.getBytes();
	RazunaService service=new RazunaService();
	Configuration config = new Configuration();
	
	@Test
	public void testfinish1() throws Exception
	{
		//positive path
		MultipartUtility multipart = new MultipartUtility(config.RAZUNA_URL, charset);
		multipart.addFormField("fa", "c.apiupload");
		multipart.addFormField("api_key", config.RAZUNA_KEY);
		multipart.addFormField("destfolderid", "60B5709518AE40359B63EF998C4751F0");
		
		multipart.addFilePart(fileName, content);
		String response = multipart.finish();
		String assetid=RazunaUtility.parseRazunaResponse(response);
		service.deleteAsset(assetid);
		assertEquals(true, response.contains("<message>success</message>") == true );
		
	}
	
	@Test
	public void testfinish2() throws Exception
	{
		//without c.apiupload
		MultipartUtility multipart = new MultipartUtility(config.RAZUNA_URL, charset);
	
		multipart.addFormField("api_key", config.RAZUNA_KEY);
		multipart.addFormField("destfolderid", "CC5DFF287F274153963FF173C505A956");
		
		multipart.addFilePart(fileName, content);
		String response = multipart.finish();
		System.out.println(response);
		assertEquals(true, response.contains("<message>success</message>") == false );
		
	}
	
	@Test
	public void testfinish3() throws Exception
	{
		// without api key
		MultipartUtility multipart = new MultipartUtility(config.RAZUNA_URL, charset);
		multipart.addFormField("fa", "c.apiupload");
		
		multipart.addFormField("destfolderid", "CC5DFF287F274153963FF173C505A956");
		
		multipart.addFilePart(fileName, content);
		String response = multipart.finish();
		assertEquals(true, response.contains("<message>success</message>") == false );
		
	}
	
	@Test
	public void testfinish4() throws Exception
	{
		// without destination folder
		MultipartUtility multipart = new MultipartUtility(config.RAZUNA_URL, charset);
		multipart.addFormField("fa", "c.apiupload");
		multipart.addFormField("api_key", config.RAZUNA_KEY);
		
		multipart.addFilePart(fileName, content);
		String response = multipart.finish();
		assertEquals(true, response.contains("<message>success</message>") == false );
		
	}
	
	@Test
	public void testfinish5() throws Exception
	{
		// without filepart
		MultipartUtility multipart = new MultipartUtility(config.RAZUNA_URL, charset);
		multipart.addFormField("fa", "c.apiupload");
		multipart.addFormField("api_key", config.RAZUNA_KEY);
		multipart.addFormField("destfolderid", "CC5DFF287F274153963FF173C505A956");
		
		String response = multipart.finish();
		assertEquals(true, response.contains("SUCCESS") == false );
		
	}
}