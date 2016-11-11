package mll.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import mll.beans.Metadata;
import mll.utility.Configuration;
import mll.utility.HttpUtility;
import mll.utility.MultipartUtility;
import org.json.JSONException;
import mll.utility.RazunaUtility;


public class RazunaService
{
	HttpUtility httputil=new HttpUtility();
	Configuration config = new Configuration();
	public ArrayList<String> uploadMedia(List<Metadata> metadata,String folder_id)
	{
		
		ArrayList<String> assetIds=null;
		try
		{
			String charset = "UTF-8";
			
			byte[] content = null;
			String fileName = null;
			assetIds=new ArrayList<String>();
			if(metadata!=null)
			{
				for(Metadata md : metadata){
					content = md.getSongMetadata().getContent();
					System.out.println("byte lenght is " + content.length);
					fileName = md.getSongMetadata().getFileName();
					MultipartUtility multipart = new MultipartUtility(config.RAZUNA_URL, charset);

					multipart.addHeaderField("User-Agent", "Shivani");
					multipart.addHeaderField("Test-Header", "Header-Value");

					multipart.addFormField("fa", "c.apiupload");
					multipart.addFormField("api_key", config.RAZUNA_KEY);
					multipart.addFormField("metadata", Integer.toString(1));
					multipart.addFormField("meta_lang_id_r", Integer.toString(1));
					multipart.addFormField("destfolderid", folder_id);
				
					multipart.addFilePart(fileName, content);
					String response = multipart.finish();
					String assetId=RazunaUtility.parseRazunaResponse(response);
					addMetaData(md,assetId);
					assetIds.add(assetId);
				}
				
				
				/*
				System.out.println("SERVER REPLIED:");
				
				for (String line : response) {
					System.out.println(line);
				}*/
			}
		}
			
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return assetIds;
	}
	
	public String createFolderForUser(String folderName)
	{
		if(folderName!=null)
		{
			String folderId=new String();
			HashMap<String,String> reqMap=new HashMap<String,String>();
			reqMap.put("method", "setfolder");
			reqMap.put("api_key", new Configuration().RAZUNA_KEY);
			reqMap.put("folder_name", folderName);
		
			return httputil.callRazunaAPI(reqMap, config.Razuna_CREATE_FOLDER_METHOD);
		}
		else
			return null;
	}

	public void addMetaData(Metadata md,String assetId) throws JSONException
	{
			HashMap<String,String> reqMap=new HashMap<String,String>();
			reqMap.put("method", "setfieldvalue");
			reqMap.put("api_key", new Configuration().RAZUNA_KEY);
			reqMap.put("assetid",assetId);
			reqMap.put("field_values", RazunaUtility.createJsonForCustomFields(md.getSongMetadata()));
			httputil.callRazunaAPI(reqMap, config.RAZUNA_CUSTOM_FIELD_METHOD);
			
			
	
	}
	

}

