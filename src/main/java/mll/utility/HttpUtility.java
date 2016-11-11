package mll.utility;

import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
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
	

	public String callRazunaAPI(HashMap<String,String> reqParams,String methodName) 
	{
		HttpPost httpost = null;
		Configuration conf=new Configuration();
   

		try {

			DefaultHttpClient httpClient = new DefaultHttpClient();
			httpost = new HttpPost(conf.Razuna_API_URL + methodName);
			httpost.setHeader("Accept",
		             "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
			
				   
			List<NameValuePair> params = createParams(reqParams);
			
			httpost.setEntity(new UrlEncodedFormEntity(params));
			httpost.setHeader("Content-Type", "application/x-www-form-urlencoded");
			HttpResponse response = httpClient.execute(httpost);
			

			if (response.getStatusLine().getStatusCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : "
				   + response.getStatusLine().getStatusCode());
			}
			else if(response.getStatusLine().getStatusCode()==200)
			{
				return readResponse(response);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}
	
	private String readResponse(HttpResponse response) throws ParseException, JSONException, IOException {
		
		JSONObject json = new JSONObject(EntityUtils.toString(response.getEntity()));

		if(json.has("folder_id"))
		return json.getString("folder_id");
		else
			return "failure";
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


}
