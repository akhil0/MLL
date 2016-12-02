package mll.service;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.junit.Test;

import mll.beans.PlaylistReference;


public class PlaylistReferenceServiceTest {
		
	@Test
	public void testGetAllPlaylistsForUserId1()
	{
		try 
		{
			PlaylistReferenceService service = new PlaylistReferenceService();
			assertEquals(true, service.getAllPlaylistsForUser(-1) == null);
		} 
		catch (Exception e) 
		{
		}
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void testConvertToJson1() 
	{
		try 
		{
			PlaylistReferenceService service = new PlaylistReferenceService();
			List<PlaylistReference> playlists = new ArrayList<PlaylistReference>();
			PlaylistReference playlistReference = new PlaylistReference();
			playlistReference.setId(1);
			playlistReference.setPlaylistName("Test");
			playlistReference.setUserId(1);
			playlists.add(playlistReference);
			JSONArray jsonArray = new JSONArray();
			JSONObject object = new JSONObject();
			object.put("id", 1);
			object.put("playlistName", "Test");
			object.put("userId", 1);
			jsonArray.add(object);
			int count = service.convertToJson(playlists).size();
			assertEquals(true, count==jsonArray.size());
		}
		catch(Exception e)
		{
			
		}
	}
}