package mll.service;
import static org.junit.Assert.assertEquals;


import org.json.simple.JSONObject;
import org.junit.Test;

public class ProfileServiceTest {

	@Test
	public void testuploadBandDeatilnull() throws Exception
	{
		ProfileService service = new ProfileService();
		assertEquals(true, service.uploadBandDetails(null, null) == null);
	}
	
	@Test
	public void populateSongWritersnull() throws Exception
	{
		ProfileService service = new ProfileService();
		assertEquals(true, service.populateSongWriters(null, null) == null);
	}
	
	@Test
	public void populateSongWriterssuccess() throws Exception
	{
		ProfileService service = new ProfileService();
		JSONObject json = new JSONObject();
		assertEquals(true, service.populateSongWriters(json, null) == null);
	}
	
}
