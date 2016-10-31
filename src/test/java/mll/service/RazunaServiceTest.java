package mll.service;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONObject;
import org.junit.Assert;
import org.junit.Test;

import mll.beans.Metadata;

public class RazunaServiceTest
{	
	  

	@Test(expected=NullPointerException.class)
	public void uploadMediaTestFailure() throws Exception{
			RazunaService razunaService = new RazunaService();
			assertEquals(true, razunaService.uploadMedia(null) == false);
				
	}
	
	@Test(expected=NullPointerException.class)
	public void uploadMediaTestSuccess() throws Exception{
			RazunaService razunaService = new RazunaService();
			assertEquals(true, razunaService.uploadMedia(getMetadata()) == true);
				
	}
	
	@SuppressWarnings("unchecked")
	public List<Metadata> getMetadata() {	
		Metadata metadata = new Metadata();
		List<Metadata> metadatas = new ArrayList<Metadata>();
		JSONObject jo = new JSONObject();
		jo.put("generalInformation", "copyright");
		jo.put("ownershipInformation", "pubCompany");
		jo.put("soundInformation", "pro");
		metadata.setMetadataJson(jo);
		
		metadatas.add(metadata);
		return metadatas;
	}
}