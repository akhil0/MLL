package mll.utility;

import java.io.StringReader;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import mll.beans.Metadata;
import mll.beans.SongMetadata;

public class RazunaUtility {

	static Configuration conf=new Configuration();

	public static String parseRazunaResponse(String response) throws Exception {

		DocumentBuilder db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
		InputSource is = new InputSource();
		is.setCharacterStream(new StringReader(response));
		String assetId=new String();
		Document doc = db.parse(is);

		NodeList nodes = doc.getElementsByTagName("assetid");
		for(int i=0;i<nodes.getLength();i++)
		{
			if(nodes.item(i).getNodeName().equalsIgnoreCase("assetid"))
			{
				assetId=nodes.item(i).getTextContent();
			}
		}
		return assetId;
	}


	public static String createJsonForCustomFields(SongMetadata smd) throws JSONException {
		JSONArray customFields=new JSONArray();

		if(smd.getTitle()!=null)
		{
			JSONArray title=new JSONArray();
			title.put(conf.RAZUNA_TITLE);
			title.put(smd.getTitle());
			customFields.put(title);
		}

		if(new Long(smd.getBeats_per_rate())!=null)
		{
			JSONArray bits=new JSONArray();
			bits.put(conf.RAZUNA_BITS_PER_RATE);
			bits.put(smd.getBeats_per_rate());
			customFields.put(bits);
		}
		if(smd.getCopyright_date()!=null)
		{
			JSONArray arr=new JSONArray();
			arr.put(conf.RAZUNA_COPY_RIGHT_DATE);
			arr.put(smd.getCopyright_date());
			customFields.put(arr);
		}
		if(smd.getCopyright_number()!=null)
		{
			JSONArray arr=new JSONArray();
			arr.put(conf.RAZUNA_COPY_RIGHT_NUMBER);
			arr.put(smd.getCopyright_number());
			customFields.put(arr);
		}
		if(smd.getLyrics()!=null)
		{
			JSONArray arr=new JSONArray();
			arr.put(conf.RAZUNA_LYRICS);
			arr.put(smd.getLyrics());
			customFields.put(arr);
		}
		if(smd.getPrimary_genre()!=null)
		{
			JSONArray arr=new JSONArray();
			arr.put(conf.RAZUNA_PRIMAY_GENRE);
			arr.put(smd.getPrimary_genre());
			customFields.put(arr);
		}
		if(smd.getSecondary_genre()!=null)
		{
			JSONArray arr=new JSONArray();
			arr.put(conf.RAZUNA_SEC_GENRE);
			arr.put(smd.getSecondary_genre());
			customFields.put(arr);
		}
		if(smd.getPublishing_company()!=null)
		{
			JSONArray arr=new JSONArray();
			arr.put(conf.RAZUNA_PUBLISHING_COMPNAY);
			arr.put(smd.getPublishing_company());
			customFields.put(arr);
		}
		if(smd.getPro()!=null)
		{
			JSONArray arr=new JSONArray();
			arr.put(conf.RAZUNA_PRO);
			arr.put(smd.getPro());
			customFields.put(arr);
		}
		if(smd.getArtists()!=null)
		{
			JSONArray arr=new JSONArray();
			arr.put(conf.RAZUNA_ARTISTS);
			arr.put(smd.getArtists());
			customFields.put(arr);
		}


		//System.out.println(customFields.toString());
		return customFields.toString();


	}
}
