package mll.service;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.json.JSONException;
import org.json.simple.JSONObject;
import org.junit.Test;


import mll.beans.Metadata;
import mll.beans.SongMetadata;
import mll.utility.Configuration;
import mll.utility.HttpUtility;


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
			for(Metadata m:metadatas){
//				String response = new MultipartUtility(new Configuration().RAZUNA_URL, "UTF-8").finish();
//				String assetId=RazunaUtility.parseRazunaResponse(response);
//				System.out.println(assetId);
//				if(assetId != null){
//				assetIds.add(assetId);
//				}
			}
			ArrayList<String> assetids=razunaService.uploadMedia(metadatas, "8181F7DD7FFF432C9DE8F13F662ED754");
			
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
		assertEquals(razunaService.createFolderForUser("Uploads").length(), 32);
	}
	
	@Test
	public void createFolderForUserTestFailure(){
		RazunaService razunaService = new RazunaService();
		assertEquals(razunaService.createFolderForUser(null), null);
	}
	
	@Test
	public void addMetaData1() throws JSONException, IOException{
		RazunaService razunaService = new RazunaService();
		razunaService.addMetaData(getMetadata().get(0), "8181F7DD7FFF432C9DE8F13F662ED754");
	}
	
	@Test
	public void addMetaData2() throws JSONException, IOException{
		RazunaService razunaService = new RazunaService();
		razunaService.addMetaData(getMetadata().get(0), "3E4A99288E294FD1B4537E1588743C45");
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
}