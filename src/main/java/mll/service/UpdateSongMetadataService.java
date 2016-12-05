package mll.service;

import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import mll.dao.UpdateSongMetadataDAO;

public class UpdateSongMetadataService {

	UpdateSongMetadataDAO updatedao=new UpdateSongMetadataDAO();
	RazunaService razunaservice=new RazunaService();
	public String updateSongMetadata(HttpServletRequest request) throws JSONException
	{
		
	
		String response=new String();
		JSONParser parser = new JSONParser();
		StringBuffer requestStr = new StringBuffer();
	   	BufferedReader reader;
	   	if(request!=null)
	   	{
	   		try {
	   			reader = request.getReader();
		
	   			String line = null;
	   			while ((line = reader.readLine()) != null)
	   			{
	   				requestStr.append(line);
	   			}
   
	   			System.out.println("Edit object is "+requestStr.toString());
	   			JSONObject songobj = new JSONObject(requestStr.toString());
	   			
	   			return updateSong(songobj);

	   		}
	   		catch (IOException e) {
	   			// TODO Auto-generated catch block
	   			e.printStackTrace();
	   			response="Data cannot be updated, Try again";
	   		} 
	   	}
	   	else
	   		response="Data cannot be updated, Try again";
		
		return response;
		
	}
	
	public String updateSong(JSONObject songobj) throws JSONException, org.apache.http.ParseException, IOException
	{
		System.out.println("inside the function updateSong()");
		String response=null;
		if(songobj != null)
		{
			System.out.println("inside  if block of updateSong");
			String songid=new String();
			JSONObject ownershipinfo=new JSONObject();
			JSONArray recordinginfo=new JSONArray();
			JSONArray  writerinfo=new JSONArray();
			if(songobj.get("assetId") !=null)
				songid= songobj.getString("assetId");
			if(songobj.has("OwnerShipInfo") && songobj.get("OwnerShipInfo")!=null)
			{
				JSONObject ownerinfo=songobj.getJSONObject("OwnerShipInfo");
				if(ownerinfo.has("RECORDING") && ownerinfo.get("RECORDING")!=null && ownerinfo.getJSONArray("RECORDING").length() >0)
				{
					recordinginfo=ownerinfo.getJSONArray("RECORDING");
				}
			
				if(ownerinfo.has("WRITER") && ownerinfo.get("WRITER")!=null && ownerinfo.getJSONArray("WRITER").length() >0)
				{
					writerinfo=ownerinfo.getJSONArray("WRITER");
				}
			}
			
				String dbmsg=updatedao.updateSongMetadata(writerinfo, recordinginfo);
				String razunasmsg=razunaservice.updateMetaData(songobj, songid);
				if(!dbmsg.equalsIgnoreCase("failure") || !razunasmsg.equalsIgnoreCase("failure"))
					response ="Data Updated Successfully";
				else
					response= "Data cannot be updated, Try again";
			
		
		}  
		else
			response="Data cannot be updated, Try again";
		
		return response;
	}
}
