package mll.utility;
import static org.junit.Assert.*;

import java.io.IOException;
import java.util.HashMap;

import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.json.JSONException;
import org.junit.Test;

import mll.service.RazunaService;

public class HttpUtilityTest {
	
	HttpUtility util=new HttpUtility();
	@Test
	public void testCallRazunaAPIFail()
	{
		
			
			HashMap<String,String> map=new HashMap<String,String>();
			map.put("method", "folderid");
			map.put("api_key", "1234");
			map.put("folder_name", "final");
			HttpResponse response=util.callRazunaAPI(map,new Configuration().Razuna_CREATE_FOLDER_METHOD);
			;
			try {
				assertTrue(util.readResponseForFolderCreation(response).equalsIgnoreCase("failure"));
			} catch (ParseException | JSONException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	}
	
	@Test
	public void testeadResponseForFolderCreationFailure()
	{
		
			
			try {
				assertTrue(util.readResponseForFolderCreation(null).equalsIgnoreCase("failure"));
			} catch (ParseException | JSONException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	}
	
	
	@Test
	public void testCallRazunaAPIFail1()
	{
		
			HttpUtility util=new HttpUtility();
			HashMap<String,String> map=new HashMap<String,String>();
			map.put("method", "folderid");
			map.put("api_key", "1234");
			map.put("folder_name", "final");
			HttpResponse response=util.callRazunaAPI(map,new Configuration().Razuna_CREATE_FOLDER_METHOD);
		
				assertFalse(response.getStatusLine().getStatusCode()!=200);
			
		
	}
	
	@Test
	public void testCallRazunaAPISuccess()
	{
		
			HashMap<String,String> map=new HashMap<String,String>();
			map.put("method", "folderid");
			map.put("api_key", "1234");
			map.put("folder_name", "final");
			HttpResponse response=util.callRazunaAPI(map,new Configuration().Razuna_CREATE_FOLDER_METHOD);
		
				assertTrue(response.getStatusLine().getStatusCode()==200);
			
		
	}
	
	@Test
	public void testReadResponseIntoJSONArrayFail() throws ParseException, JSONException, IOException
	{
		assertNull(util.readResponseIntoJSONArray(null));
		
	}
	
	@Test
	public void testReadResponseOfSearchedSongsFail() throws ParseException, JSONException, IOException
	{
		HashMap<String,String> reqMap=new HashMap<String,String>();
		reqMap.put("method", "searchassets");
		reqMap.put("api_key", new Configuration().RAZUNA_KEY);
		reqMap.put("folderid","");
		reqMap.put("searchfor", "song");
		
		HttpResponse response = util.callRazunaAPI(reqMap, new Configuration().RAZUNA_SEARCH_METHOD);
		assertTrue(util.readResponseOfSearchedSongs(response).size()==0);
	}
	
	@Test
	public void testReadResponseOfSearchedSongsSuccess() throws Exception
	{
		String fileName = "test";
		String charset = "UTF-8";
		String contentString = "test";
		byte[] content = contentString.getBytes();
		//positive path
		MultipartUtility multipart = new MultipartUtility(new Configuration().RAZUNA_URL, charset);
		multipart.addFormField("fa", "c.apiupload");
		multipart.addFormField("api_key", new Configuration().RAZUNA_KEY);
		multipart.addFormField("destfolderid", "4BB7CA2D4E3F40BDA52C829E0F09C693");
		
		multipart.addFilePart(fileName, content);
		String res = multipart.finish();
		
		HashMap<String,String> reqMap=new HashMap<String,String>();
		reqMap.put("method", "searchassets");
		reqMap.put("api_key", new Configuration().RAZUNA_KEY);
		reqMap.put("folderid","4BB7CA2D4E3F40BDA52C829E0F09C693");
		reqMap.put("searchfor", "test");
		
		HttpResponse response = util.callRazunaAPI(reqMap, new Configuration().RAZUNA_SEARCH_METHOD);
		assertTrue(util.readResponseOfSearchedSongs(response).size()>0);
		String assetid=RazunaUtility.parseRazunaResponse(res);
		new RazunaService().deleteAsset(assetid);
	}
	

}
