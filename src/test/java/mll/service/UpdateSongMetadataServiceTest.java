package mll.service;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.http.ParseException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;

import mll.beans.Metadata;
import mll.utility.Configuration;
import mll.utility.HttpUtility;

public class UpdateSongMetadataServiceTest {

	UpdateSongMetadataService updateService=new UpdateSongMetadataService();
	
	@Test
	public void updateMetadataServiceTest() throws JSONException
	{
		assertTrue(updateService.updateSongMetadata(null).equalsIgnoreCase("Data cannot be updated, Try again"));
	}
	
	@Test
	public void testUpdateSongWithNullJSONObject() throws ParseException, JSONException, IOException
	{
		assertTrue(updateService.updateSong(null).equalsIgnoreCase("Data cannot be updated, Try again"));
	}
	
	@Test
	public void testUpdateSongWithJSONObject() throws ParseException, JSONException, IOException
	{
		RazunaService razunaservice=new RazunaService();
		RazunaServiceTest servicetest=new RazunaServiceTest();
		ArrayList<String> assetIds=new ArrayList<String>();
		List<Metadata> metadatas = servicetest.getMetadata();
		ArrayList<String> assetids=razunaservice.uploadMedia(metadatas, "40E5FD89FF8945B5A94719E8613217D8");
		assertEquals(1, assetids.size());
		JSONObject dataobj=new JSONObject();
		dataobj.put("primayPhone", "8578299424");
		dataobj.put("Name","sai mahanth");
		dataobj.put("divisionOfOwnership","full");
		dataobj.put("Id","2");
		JSONArray arry=new JSONArray();
		arry.put(dataobj);
		JSONObject obj=new JSONObject();
		obj.put("assetId", assetids.get(0));
		obj.put("OwnerShipInfo",new JSONObject().put("RECORDING", arry));
		obj.put("beats_per_minute",20);
		obj.put("Title", "Sample");
		String msg=updateService.updateSong(obj);
		HashMap<String,String> reqMap=new HashMap<String,String>();
		reqMap.put("method", "remove");
		reqMap.put("api_key", new Configuration().RAZUNA_KEY);
		reqMap.put("assetid",assetids.get(0));
		new HttpUtility().callRazunaAPI(reqMap, "asset.cfc");
		assertTrue(msg.equalsIgnoreCase("Data Updated Successfully"));
	}
}

