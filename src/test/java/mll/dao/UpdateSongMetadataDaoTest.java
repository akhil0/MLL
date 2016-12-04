package mll.dao;

import static org.junit.Assert.*;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONArray;
import org.junit.Test;

public class UpdateSongMetadataDaoTest {

	UpdateSongMetadataDAO updatedao=new UpdateSongMetadataDAO();
	
	@Test
	public void TestUpdateSongWithNull() throws JSONException
	{
		
		assertTrue(updatedao.updateSongMetadata(null, null).equalsIgnoreCase("failure"));
	}
	
	@Test
	public void TestUpdateSongWithEmptyJSON() throws JSONException
	{
		
		assertTrue(updatedao.updateSongMetadata(new JSONArray(), new JSONArray()).equalsIgnoreCase("success"));
	}
	
	
	@Test
	public void TestUpdateSongWithWriterInfo() throws JSONException
	{
		JSONObject obj=new JSONObject();
		obj.put("primayPhone", "8578299424");
		obj.put("Name","sai mahanth");
		obj.put("divisionOfOwnership","full");
		obj.put("id","2");
		JSONArray arry=new JSONArray();
		arry.put(obj);
		
		assertTrue(updatedao.updateSongMetadata(arry, new JSONArray()).equalsIgnoreCase("success"));
	}
	
	@Test
	public void TestUpdateSongWithRecordingInfo() throws JSONException
	{
		JSONObject obj=new JSONObject();
		obj.put("primayPhone", "8578299424");
		obj.put("Name","sai mahanth");
		obj.put("divisionOfOwnership","full");
		obj.put("id","1");
		JSONArray arry=new JSONArray();
		arry.put(obj);
		
		assertTrue(updatedao.updateSongMetadata(arry, new JSONArray()).equalsIgnoreCase("success"));
	}
}
