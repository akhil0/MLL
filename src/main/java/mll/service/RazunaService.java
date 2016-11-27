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
			if(songs.get(0)!=null &&songs.getJSONObject(0).has("fileName") && songs.getJSONObject(0).getInt("fileName")==0)
				songs=new JSONArray();
		}
		
		return songs;
	}
	
	
//	public JSONArray retrieveMetaDataOfSongs(JSONArray songsArray) throws JSONException, ParseException, IOException
//	{
//		JSONArray songs=new JSONArray(); 
//		for(int i=0;i<songsArray.length();i++)
//		{
//			JSONObject song=songsArray.getJSONObject(i);
//			JSONObject metaData=new JSONObject();
//			if(song.has("ID"))
//			{
//				String songId=(String) song.get("ID");
//				HashMap<String,String> reqMap=new HashMap<String,String>();
//				reqMap.put("method", "getfieldsofasset");
//				reqMap.put("api_key", new Configuration().RAZUNA_KEY);
//				reqMap.put("asset_id",songId);
//				metaData=httputil.readResponseForCustomFields(httputil.callRazunaAPI(reqMap, config.RAZUNA_CUSTOM_FIELD_METHOD));
//				SongRetrievalDAO songretrieval=new SongRetrievalDAO();
//				metaData.put("OwnerShipInfo", songretrieval.retrieveOwnerInfo(songId));
//				metaData.put("source", song.getString("LOCAL_URL_ORG"));
//				metaData.put("fileName",  song.getString("FILENAME"));
//				metaData.put("dateAdded",song.getString("DATEADD"));
//				metaData.put("songid", songId);
//				songs.put(metaData);
//			}
//			
//		}
//		System.out.println(songs);
//		return songs;
//	}
	

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
				obj.put("divisionOfOwnership", owner.getDivisonOfOwnership());
				obj.put("primayPhone", owner.getPrimaryPhone());
				obj.put("secondaryPhone", owner.getSecondaryPhone());
				obj.put("primayEmail", owner.getPrimaryEmail());
				obj.put("secondaryEmail", owner.getSecondaryEmail());
				OwnerShipInfo.put(owner.getOwnerType(), obj);
				song.put("OwnerShipInfo", OwnerShipInfo);
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
			if(!obj.getString("FOLDER_ID").equalsIgnoreCase("40E5FD89FF8945B5A94719E8613217D8"))
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
	
	public void deleteAsset(String assetId) throws JSONException
	{
		JSONArray array=new JSONArray();
		
		
				HashMap<String,String> reqMap=new HashMap<String,String>();
				reqMap.put("method", "remove");
				reqMap.put("api_key", new Configuration().RAZUNA_KEY);
				reqMap.put("folder_id",assetId);
				httputil.callRazunaAPI(reqMap, config.RAZUNA_ASSET_METHOD);
				
		
	}
	
public static void main(String[] args)
 {
	 RazunaService service=new RazunaService();
	 try {
		 JSONArray songs=service.RetrieveSongs("1234");
		 System.out.println(songs.length());
		
		service.deleteFolders();
	} catch ( Exception  e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
 }
	
}

