package mll.service;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.http.ParseException;
import org.json.JSONException;
import org.json.simple.JSONObject;
import org.junit.Test;


import mll.beans.Metadata;
import mll.beans.SongMetadata;
import mll.utility.Configuration;
import mll.utility.HttpUtility;
import mll.utility.MultipartUtility;
import mll.utility.RazunaUtility;


public class RazunaServiceTest
{	
	  

	@Test
	public void uploadMediaTestFailure() throws Exception{
			RazunaService razunaService = new RazunaService();
			assertEquals(false,razunaService.uploadMedia(null, null) == new ArrayList<String>() );
				
	}
	
	@Test
	public void uploadMediaTestSuccess() throws Exception{
			RazunaService razunaService = new RazunaService();
			ArrayList<String> assetIds=new ArrayList<String>();
			List<Metadata> metadatas = getMetadata();
			ArrayList<String> assetids=razunaService.uploadMedia(metadatas, "40E5FD89FF8945B5A94719E8613217D8");
			
			HashMap<String,String> reqMap=new HashMap<String,String>();
			reqMap.put("method", "remove");
			reqMap.put("api_key", new Configuration().RAZUNA_KEY);
			reqMap.put("assetid",assetids.get(0));
			new HttpUtility().callRazunaAPI(reqMap, "asset.cfc");
			assertEquals(1, assetids.size());
				
	}
	
	@Test
	public void createFolderForUserTestSuccess(){
		RazunaService razunaService = new RazunaService();
		String folderId=razunaService.createFolderForUser("Uploads");
		try {
			razunaService.deleteFolder(folderId);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals(folderId.length(), 32);
	}
	
	@Test
	public void createFolderForUserTestFailure(){
		RazunaService razunaService = new RazunaService();
		assertEquals(razunaService.createFolderForUser(null), null);
	}
	
	@Test
	public void addMetaData1() throws JSONException, IOException{
		RazunaService razunaService = new RazunaService();
		razunaService.addMetaData(getMetadata().get(0), "40E5FD89FF8945B5A94719E8613217D8");
	}
	
	
	
	@Test(expected = NullPointerException.class)
	public void addMetaData3() throws NullPointerException, JSONException{
		RazunaService razunaService = new RazunaService();
		razunaService.addMetaData(null, null);
	}
	
	@SuppressWarnings("unchecked")
	public List<Metadata> getMetadata() throws IOException {	
		ArrayList<Metadata> metadatas=new ArrayList<Metadata>();
		File uploadFile1 = new File("src/main/resources/samplesong.mp3");
		 FileInputStream inputStream = new FileInputStream(uploadFile1);
		 ByteArrayOutputStream buffer = new ByteArrayOutputStream();

		 int nRead;
		 byte[] data = new byte[16384];

		 while ((nRead = inputStream.read(data, 0, data.length)) != -1) {
		   buffer.write(data, 0, nRead);
		 }

		 buffer.flush();

		 byte[] content= buffer.toByteArray();
	       
		Metadata metadata = new Metadata();
		SongMetadata songmeta=new SongMetadata();
		songmeta.setTitle("test");
		songmeta.setArtists("mahanth");
		songmeta.setContent(new byte[100]);
		songmeta.setBeats_per_rate(new Long(10));
		songmeta.setContent(content);
		songmeta.setFileName("samplesong.mp3");
		songmeta.setLyrics("hello world");
		songmeta.setPrimary_genre("blue");
		songmeta.setSecondary_genre("heals");
		songmeta.setPro("sample");
		songmeta.setCopyright_date("12-2-2016");
		songmeta.setCopyright_number("12342");
		songmeta.setPublishing_company("neu");
		metadata.setSong(songmeta);
		metadatas.add(metadata);
		return metadatas;
	}
	
	@Test
	public void testRetrieveSongs()
	{
		RazunaService service=new RazunaService();
		try {
			assertEquals(true,service.RetrieveSongs(null).length()==0);
		} catch (ParseException | JSONException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testRetrieveSongs1()
	{
		RazunaService service=new RazunaService();
		try {
			assertEquals(true,service.RetrieveSongs("4BB7CA2D4E3F40BDA52C829E0F09C693").length()>0);
		} catch (ParseException | JSONException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testRetrieveSongs2()
	{
		RazunaService service=new RazunaService();
		try {
			assertEquals(true,service.RetrieveSongs("123445").length()==0);
		} catch (ParseException | JSONException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testRetrieveSongs3()
	{
		RazunaService service=new RazunaService();
		try {
			assertEquals(true,service.RetrieveSongs("").length()==0);
		} catch (ParseException | JSONException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testSearchKeyword1() throws ParseException, JSONException, IOException{
		RazunaService service=new RazunaService();
		assertNull(service.searchKeyword(null, "rock"));
		
	}
	
	@Test
	public void testSearchKeyword2() throws Exception{
		RazunaService service=new RazunaService();
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
		String response = multipart.finish();
		assertEquals(true,service.searchKeyword("4BB7CA2D4E3F40BDA52C829E0F09C693", "test").length()>0);
		String assetid=RazunaUtility.parseRazunaResponse(response);
//		service.deleteAsset(assetid);
		
	}
	
	@Test
	public void testdeleteassetsuccess() throws Exception
	{
		RazunaService service=new RazunaService();
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
		String response = multipart.finish();
		String assetid=RazunaUtility.parseRazunaResponse(response);
		String res = service.deleteAsset(assetid);
		assertEquals(true, res.contains("successfully") == true );
		
	}
	
	@Test
	public void testdeleteassetsuccess2() throws Exception
	{
		RazunaService service=new RazunaService();
		String assetid = "xdrtytyty";
		String res = service.deleteAsset(assetid);
		assertEquals(true, res.contains("successfully") == true );
		
	}
	
	@Test
	public void testdeleteassetfailure() throws Exception
	{
		RazunaService service=new RazunaService();
		String assetid = null;
		String res = service.deleteAsset(assetid);
		assertEquals(true, res.contains("choose a song to be deleted") == true );
		
	}
}