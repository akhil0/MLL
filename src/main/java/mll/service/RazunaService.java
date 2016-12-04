package mll.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;


import mll.beans.Metadata;
import mll.beans.Owner;
import mll.dao.SongRetrievalDAO;
import mll.utility.Configuration;
import mll.utility.HttpUtility;
import mll.utility.MultipartUtility;

import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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
		
			try {
				return httputil.readResponseForFolderCreation(httputil.callRazunaAPI(reqMap, config.Razuna_CREATE_FOLDER_METHOD));
			} catch (ParseException | JSONException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
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
	
	
	public String updateMetaData(JSONObject md,String assetId) throws JSONException, ParseException, IOException
	{
		HashMap<String,String> reqMap=new HashMap<String,String>();
		reqMap.put("method", "setfieldvalue");
		reqMap.put("api_key", new Configuration().RAZUNA_KEY);
		reqMap.put("assetid",assetId);
		reqMap.put("field_values", RazunaUtility.createJsonForCustomFieldsUpdate(md));
		return httputil.readResponseForAssetUpdate(httputil.callRazunaAPI(reqMap, config.RAZUNA_CUSTOM_FIELD_METHOD));	
	}
	
	public JSONArray RetrieveSongs(String folderid) throws ParseException, JSONException, IOException
	{
		HashMap<String,JSONObject> songsdata=new HashMap<String,JSONObject>();
		JSONArray songs=new JSONArray();
		if(folderid!=null)
		{
			
			HashMap<String,String> reqMap=new HashMap<String,String>();
			reqMap.put("method", "getassets");
			reqMap.put("api_key", new Configuration().RAZUNA_KEY);
			reqMap.put("folderid",folderid);
			
			songsdata=httputil.readResponseOfSongsRetrieval(httputil.callRazunaAPI(reqMap, config.Razuna_CREATE_FOLDER_METHOD));
			System.out.println("RetrieveSongs "+songsdata);
			songs= retrieveMetaDataOfSongs(songsdata);
			if(songs.get(0)!=null && songs.length()==1 && !songs.getJSONObject(0).has("source"))
				songs=new JSONArray();
		}
		
		return songs;
	}
	
	

	

	public JSONArray retrieveMetaDataOfSongs(HashMap<String,JSONObject> songsdata) throws JSONException, ParseException, IOException
	{
		JSONArray songsArray=new JSONArray();
		System.out.println(songsdata.size() + " in retrievemetadata");
		if(songsdata!=null)
		{
			SongRetrievalDAO songretrieval=new SongRetrievalDAO();
			String keys[]=songsdata.keySet().toArray(new String[songsdata.size()]);
			List<Owner> ownerInfo=songretrieval.retrieveOwnerInfo(songsdata.keySet());
			songsdata=traverseOwnerInfo(ownerInfo,songsdata);
			
			String ids=Arrays.toString(keys);
			ids=ids.replaceAll("\\[", "").replaceAll("\\]", "");
			ids=ids.replaceAll(" ","");
			songsArray= readCustomFieldsofSongs(ids,songsdata);
			
		}
		return songsArray;
			
	}
	
	public HashMap<String,JSONObject> traverseOwnerInfo(List<Owner> owners, HashMap<String,JSONObject> songsdata) throws JSONException
	{

		System.out.println(songsdata.size() +" before");
		if(owners!=null && owners.size()>0)
		{
			for(Owner owner:owners)
			{
				JSONObject OwnerShipInfo=new JSONObject();
			
				String songid=owner.getSongId();
				JSONObject song=songsdata.get(songid);
				if(song.has("OwnerShipInfo"))
				{
				  OwnerShipInfo=song.getJSONObject("OwnerShipInfo");
				}
				JSONObject obj=new JSONObject();
				obj.put("Name", owner.getName());
				obj.put("Id",owner.getId());
				obj.put("divisionOfOwnership", owner.getDivisonOfOwnership());
				obj.put("primayPhone", owner.getPrimaryPhone());
				obj.put("secondaryPhone", owner.getSecondaryPhone());
				obj.put("primayEmail", owner.getPrimaryEmail());
				obj.put("secondaryEmail", owner.getSecondaryEmail());
				if(owner.getOwnerType().equalsIgnoreCase("RECORDING"))
				{
					JSONArray recordingarray=new JSONArray();
					if(OwnerShipInfo.has("RECORDING"))
					{
						recordingarray=OwnerShipInfo.getJSONArray("RECORDING");
						recordingarray.put(obj);
						OwnerShipInfo.put("RECORDING", recordingarray);
					}  
					else
					{
						recordingarray.put(obj);
						OwnerShipInfo.put("RECORDING", recordingarray);
					}
					
				}
				if(owner.getOwnerType().equalsIgnoreCase("WRITER"))
				{
					JSONArray recordingarray=new JSONArray();
					if(OwnerShipInfo.has("WRITER"))
					{
						recordingarray=OwnerShipInfo.getJSONArray("WRITER");
						recordingarray.put(obj);
						OwnerShipInfo.put("WRITER", recordingarray);
					}  
					else
					{
						recordingarray.put(obj);
						OwnerShipInfo.put("WRITER", recordingarray);
					}
					
				}
			
				song.put("OwnerShipInfo", OwnerShipInfo);
				System.out.println("OwnerShipInfo "+OwnerShipInfo);
				songsdata.put(songid, song);
			}
			
		}
		System.out.println(songsdata.size() +" after");
		return songsdata;
	}
	
	
	public JSONArray readCustomFieldsofSongs(String ids,HashMap<String,JSONObject> songsdata) throws ParseException, JSONException, IOException
	{
		HashMap<String,String> reqMap=new HashMap<String,String>();
		reqMap.put("method", "getfieldsofasset");
		reqMap.put("api_key", new Configuration().RAZUNA_KEY);
		reqMap.put("asset_id",ids);
		return httputil.readResponseForCustomFields(httputil.callRazunaAPI(reqMap, config.RAZUNA_CUSTOM_FIELD_METHOD),songsdata);
	}
	
	
	
	
	public void deleteFolders() throws JSONException
	{
		JSONArray array=new JSONArray();
		HashMap<String,String> reqMap=new HashMap<String,String>();
		reqMap.put("method", "getfolders");
		reqMap.put("api_key", new Configuration().RAZUNA_KEY);
		
		
		try {
			array=httputil.readResponseIntoJSONArray(httputil.callRazunaAPI(reqMap, config.Razuna_CREATE_FOLDER_METHOD));
		} catch (ParseException | JSONException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for(int i=0;i<array.length();i++)
		{
			JSONObject obj=array.getJSONObject(i);
			if(!obj.getString("FOLDER_ID").equalsIgnoreCase("2F1998DC3A534074A096509E79AE6339") && !obj.getString("FOLDER_ID").equalsIgnoreCase("4BB7CA2D4E3F40BDA52C829E0F09C693"))
			{
				HashMap<String,String> map=new HashMap<String,String>();
				reqMap.put("method", "removefolder");
				reqMap.put("api_key", new Configuration().RAZUNA_KEY);
				reqMap.put("folder_id", obj.getString("FOLDER_ID"));
				httputil.callRazunaAPI(reqMap, config.Razuna_CREATE_FOLDER_METHOD);
				
			}
		}
	}
	
	
	
	public void deleteFolder(String folderId) throws JSONException
	{
		JSONArray array=new JSONArray();
		
		
				HashMap<String,String> reqMap=new HashMap<String,String>();
				reqMap.put("method", "removefolder");
				reqMap.put("api_key", new Configuration().RAZUNA_KEY);
				reqMap.put("folder_id",folderId);
				httputil.callRazunaAPI(reqMap, config.Razuna_CREATE_FOLDER_METHOD);
				
		
	}
	
	public String deleteAsset(String assetId) throws JSONException, ParseException, IOException
	{
		JSONArray array=new JSONArray();
		
		if(assetId != null){
			HashMap<String,String> reqMap=new HashMap<String,String>();
			reqMap.put("method", "remove");
			reqMap.put("api_key", new Configuration().RAZUNA_KEY);
			reqMap.put("assetid",assetId);
			HttpResponse response = httputil.callRazunaAPI(reqMap, config.RAZUNA_ASSET_METHOD);
			String res = httputil.readResponseForAssetDeletion(response);
			return res;
		}
		return "Please choose a song to be deleted";		
		
	}
	public JSONArray searchKeyword(String folder_id, String searchWord) throws ParseException, JSONException, IOException{
		JSONArray songsarray=new JSONArray();
		HashMap<String,JSONObject> songsdata=new HashMap<String,JSONObject>();
		if(folder_id!=null && searchWord != null)
		{
			
			HashMap<String,String> reqMap=new HashMap<String,String>();
			reqMap.put("method", "searchassets");
			reqMap.put("api_key", new Configuration().RAZUNA_KEY);
			reqMap.put("folderid",folder_id);
			reqMap.put("searchfor", searchWord);
			
			songsdata=httputil.readResponseOfSearchedSongs(httputil.callRazunaAPI(reqMap, config.RAZUNA_SEARCH_METHOD));
			System.out.println(songsdata.size() + " Size of searched songs ");
			songsarray = retrieveMetaDataOfSongs(songsdata);
			return songsarray;
		}
		return null;
	}
	
public static void main(String[] args)
{
	 RazunaService service=new RazunaService();
	 try {
	 //JSONArray songs=service.RetrieveSongs("4BB7CA2D4E3F40BDA52C829E0F09C693");
	 //System.out.println(songs.length());
		// service.updateMetaData(new JSONObject(), "FCD433C107A14F0D9FDE89A9A4DFF9E6");
		 service.deleteFolders();
		
	} catch ( Exception  e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
 }
	
}

