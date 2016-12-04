package mll.utility;

import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;



import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;

public class HttpUtility {
	

	public HttpResponse callRazunaAPI(HashMap<String,String> reqParams,String methodName) 
	{
		HttpPost httpost = null;
		Configuration conf=new Configuration();
   

		try {

			DefaultHttpClient httpClient = new DefaultHttpClient();
			httpost = new HttpPost(conf.Razuna_API_URL + methodName);
			
			
				   
			List<NameValuePair> params = createParams(reqParams);
			
			httpost.setEntity(new UrlEncodedFormEntity(params));
			//httpost.setHeader("Content-Type", "application/x-www-form-urlencoded");
			HttpResponse response = httpClient.execute(httpost);
			
				return response;
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}
	
	public String readResponseForFolderCreation(HttpResponse response) throws ParseException, JSONException, IOException {
	
		if(response!=null && response.getStatusLine().getStatusCode()==200)
		{
		JSONObject json = new JSONObject(EntityUtils.toString(response.getEntity()));

		if(json.has("folder_id"))
		return json.getString("folder_id");
		else
			return "failure";
		}
		else
			return "failure";
	}

	public JSONArray readResponseIntoJSONArray(HttpResponse response) throws ParseException, JSONException, IOException
	{
				
		
		JSONArray responseArray=new JSONArray();
		JSONArray columnarray=new JSONArray();
		if(response!=null)
		{
			
			JSONObject json = new JSONObject(EntityUtils.toString(response.getEntity()));
			if(json.has("COLUMNS"))
			{
				
				 columnarray=json.getJSONArray("COLUMNS");
			}
			if(json.has("DATA"))
			{
				JSONArray datarry=json.getJSONArray("DATA");
				for(int i=0;i<datarry.length();i++)
				{
					JSONObject songobj=new JSONObject();
					JSONArray songarry=datarry.getJSONArray(i);
					for(int j=0;j<songarry.length();j++)
					{
						songobj.put(columnarray.getString(j), songarry.get(j));
					}
					responseArray.put(songobj);
				}
			}
	
			return responseArray;
			
		}
		
		return null;
	}
	
	
	public HashMap readResponseOfSongsRetrieval(HttpResponse response) throws ParseException, JSONException, IOException
	{
				
		
		JSONArray responseArray=new JSONArray();
		JSONArray columnarray=new JSONArray();
		HashMap<String,JSONObject> songsdata=new HashMap<String,JSONObject>();
		String ids=new String();
		if(response!=null)
		{
			
			JSONObject json = new JSONObject(EntityUtils.toString(response.getEntity()));
			
			if(json.has("DATA"))
			{
				JSONArray datarry=json.getJSONArray("DATA");
				for(int i=0;i<datarry.length();i++)
				{
					JSONObject songobj=new JSONObject();
					JSONArray songarry=datarry.getJSONArray(i);
					String id=new String();
					for(int j=0;j<songarry.length();j++)
					{
						if(j==0 && songarry.get(j)!=null)
						{
							id=songarry.getString(j);
						}
						if(j==0)
							songobj.put("assetId", songarry.get(j));
						if(j==1)
							songobj.put("fileName", songarry.get(j));
						if(j==19)
							songobj.put("source", songarry.get(j));
						if(j==16)
							songobj.put("dateAdded", songarry.get(j));
					}
					songsdata.put(id, songobj);
				}
			}
			
	
			return songsdata;
			
		}
		
		return null;
	}
	
	public HashMap readResponseOfSearchedSongs(HttpResponse response) throws ParseException, JSONException, IOException
	{
				
		
		JSONArray responseArray=new JSONArray();
		JSONArray columnarray=new JSONArray();
		HashMap<String,JSONObject> songsdata=new HashMap<String,JSONObject>();
		String ids=new String();
		if(response!=null)
		{
			
			JSONObject json = new JSONObject(EntityUtils.toString(response.getEntity()));
			
			if(json.has("DATA"))
			{
				JSONArray datarry=json.getJSONArray("DATA");
				for(int i=0;i<datarry.length();i++)
				{
					JSONObject songobj=new JSONObject();
					JSONArray songarry=datarry.getJSONArray(i);
					String id=new String();
					for(int j=0;j<songarry.length();j++)
					{
						if(j==0 && songarry.get(j)!=null)
						{
							id=songarry.getString(j);
						}
						if(j==1)
							songobj.put("fileName", songarry.get(j));
						if(j==22)
							songobj.put("source", songarry.get(j));
					}
					songsdata.put(id, songobj);
					
				}
			}
			
	
			return songsdata;
			
		}
		
		return null;
	}
	
	public JSONArray readResponseForCustomFields(HttpResponse response,HashMap<String,JSONObject> songsdata) throws ParseException, JSONException, IOException
	{
				
		JSONObject metadata=new JSONObject();
		JSONArray columnarray=new JSONArray();
		JSONArray songsarray=new JSONArray();
		System.out.println(songsdata.size());
		if(response!=null)
		{
			
			JSONObject json = new JSONObject(EntityUtils.toString(response.getEntity()));
			System.out.println(json);
			if(json.has("COLUMNS"))
			{
				
				 columnarray=json.getJSONArray("COLUMNS");
			}
			if(json.has("DATA"))
			{
				JSONArray datarry=json.getJSONArray("DATA");
				
				for(int i=0;i<datarry.length();i++)
				{
					
					JSONArray metaarray=datarry.getJSONArray(i);
					String id=metaarray.getString(4);
					JSONObject song=songsdata.get(id);
					if(metaarray.get(2) != null){
						
						song.put(metaarray.getString(1).trim(), metaarray.get(2));
						
					}
					
					songsdata.put(id, song);
				}
			}
			
			Iterator keys=songsdata.keySet().iterator();
			while(keys.hasNext())
			{
				String key=keys.next().toString();
				songsarray.put(songsdata.get(key));
			}
			
			
			System.out.println(songsarray);
			return songsarray;
			
		}
		
		return null;
	}
	
	
	
	public List<NameValuePair> createParams(HashMap<String,String> reqParams)
	{
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		Iterator ir=reqParams.keySet().iterator();
		while(ir.hasNext())
		{
			String paramkey=ir.next().toString();
			params.add(new BasicNameValuePair(paramkey,reqParams.get(paramkey).toString()));
		}
		 
		return params;
	}
	
	public String readResponseForAssetDeletion(HttpResponse response) throws ParseException, JSONException, IOException {
		
		String message = "failure";
		if(response!=null && response.getStatusLine().getStatusCode()==200)
		{
			JSONObject json = new JSONObject(EntityUtils.toString(response.getEntity()));
			if(json.has("responsecode")){
				if(json.getInt("responsecode") == 0){
					message = json.getString("message");
				}
			}
		}
		return message;
	}
	
	
	public HashMap readResponseOfEachSong(HttpResponse response) throws ParseException, JSONException, IOException
	{

		HashMap<String,JSONObject> songsdata=new HashMap<String,JSONObject>();
		if(response!=null)
		{			
			JSONObject json = new JSONObject(EntityUtils.toString(response.getEntity()));
			System.out.println(json);
			if(json.has("DATA"))
			{
				JSONArray datarry=json.getJSONArray("DATA");
				for(int i=0;i<datarry.length();i++)
				{
					JSONObject songobj=new JSONObject();
					JSONArray songarry=datarry.getJSONArray(i);
					String id=new String();
					for(int j=0;j<songarry.length();j++)
					{
						if(j==0 && songarry.get(j)!=null)
						{
							id=songarry.getString(j);
						}
						if(j==2)
							songobj.put("fileName", songarry.get(j));
						if(j==21)
							songobj.put("source", songarry.get(j));
						if(j==16)
							songobj.put("dateAdded", songarry.get(j));
					}
					songsdata.put(id, songobj);
					
				}
			}
			System.out.println("HashMap");
			System.out.println(songsdata);
	
			return songsdata;
			
		}
		
		return null;
	}

	
public String readResponseForAssetUpdate(HttpResponse response) throws ParseException, JSONException, IOException {
		
		String message = "failure";
		if(response!=null && response.getStatusLine().getStatusCode()==200)
		{
			JSONObject json = new JSONObject(EntityUtils.toString(response.getEntity()));
			if(json.has("responsecode")){
				if(json.getInt("responsecode") == 0){
					message = json.getString("message");
				}
			}
		}
		return message;
	}

}
