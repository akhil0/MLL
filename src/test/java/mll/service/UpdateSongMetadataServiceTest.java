package mll.service;

import static org.junit.Assert.*;

import java.io.IOException;

import org.apache.http.ParseException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;

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
		RazunaService service=new RazunaService();
		
		JSONObject dataobj=new JSONObject();
		dataobj.put("primayPhone", "8578299424");
		dataobj.put("Name","sai mahanth");
		dataobj.put("divisionOfOwnership","full");
		dataobj.put("id","2");
		JSONArray arry=new JSONArray();
		arry.put(dataobj);
		JSONObject obj=new JSONObject();
		obj.put("Assetid", "FCD433C107A14F0D9FDE89A9A4DFF9E6");
		obj.put("OwnerShipInfo",new JSONObject().put("RECORDING", arry));
		obj.put("beats_per_minute",20);
		obj.put("Title", "Sample");
		assertTrue(updateService.updateSong(obj).equalsIgnoreCase("Data Updated Successfully"));
	}
}

