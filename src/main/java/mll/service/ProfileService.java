package mll.service;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;


import mll.beans.Owner;
import mll.utility.Configuration;

import mll.dao.ProfileDAO;

public class ProfileService {
	ProfileDAO dao;
	Configuration conf = new Configuration();
	
	public ProfileService(){
		dao = new ProfileDAO();
	}
	
	@SuppressWarnings("unchecked")
	public JSONObject uploadBandDetails(HttpServletRequest request, HttpServletResponse response)
	{	if (request.equals(null)){
		return null;
	}
		JSONObject responseObject = new JSONObject();
		JSONObject OwnerandBand = new JSONObject();
		HttpSession session=request.getSession();
		Integer musician_id=(Integer) session.getAttribute("musician_id");
		System.out.println("Musician "+ musician_id);
		try
		{
			
			OwnerandBand = populateMetadataBeansFromRequest(request);
			if(null != OwnerandBand && !OwnerandBand.isEmpty())
			{
				dao.saveMetadata(OwnerandBand,musician_id);
				responseObject.put("isUploaded", true);
				responseObject.put("message", "Band Information successfully uploaded");
				
			}else{
				responseObject.put("isUploaded", false);
				responseObject.put("message", "Request does not contain valid data. Please upload with proper metadata information.");
			}
		}catch(Exception e){
			e.printStackTrace();
			responseObject.put("isUploaded", false);
			responseObject.put("message", "Error while saving the data. Please upload with proper band information.");
		}
		return responseObject;
	}
	
	public JSONObject populateMetadataBeansFromRequest(HttpServletRequest request) throws Exception
	{
//		List<Metadata> metadatas = new ArrayList<Metadata>();
//		Metadata metadata =  new Metadata();
		JSONObject OwnerandBand = new JSONObject();
		
		StringBuffer requestStr = new StringBuffer();
	   	BufferedReader reader = request.getReader();
    	String line = null;
    	while ((line = reader.readLine()) != null)
    	{
    		requestStr.append(line);
    	}
    	
    	JSONParser parser = new JSONParser();
    	JSONObject mainObject = (JSONObject) parser.parse(requestStr.toString());
	    JSONObject ownershipInformation =  (JSONObject) mainObject.get("ownershipInformation");
	    System.out.println("ownershipInformation "+ownershipInformation);
	    
	    // Populate Song Writers Information	 
	    populateSongWriters(OwnerandBand, ownershipInformation);
	 
		return OwnerandBand;
	}
	
	@SuppressWarnings("unchecked")
	public JSONObject populateSongWriters(JSONObject OwnerandBand, JSONObject ownershipInformation) throws Exception
	{
		
		if(null == ownershipInformation)
		{
			return OwnerandBand;
		}
		
		JSONArray songwriters = (JSONArray) ownershipInformation.get("songwriters");
		OwnerandBand.put("bandName", ownershipInformation.get("bandName"));
		List<Owner> owners = new ArrayList<Owner>();
	    for(int i=0; i<songwriters.size(); i++)
	    {
	    	JSONObject writer =  (JSONObject) songwriters.get(i);
	    	Owner owner = new Owner();
	    	
	    	owner.setDivisonOfOwnership("Half");
			owner.setName((String)writer.get("name"));
			owner.setOwnerType("WRITER");
			owner.setPrimaryEmail((String)writer.get("primaryEmail"));
			owner.setSecondaryEmail((String)writer.get("secondaryEmail"));
			owner.setPrimaryPhone((String)writer.get("primaryPhone"));
			owner.setSecondaryPhone((String)writer.get("secondaryPhone"));
			owner.setContribution((String)writer.get("contribution"));
			owner.setOwner_percent((Long)writer.get("ownershipPercent"));
			owner.setRole((String)writer.get("MusicianRole"));
	    	
			owners.add(owner);
	    }
	    OwnerandBand.put("owners", owners);
	    
	    return OwnerandBand;
	}
}
