package mll.service;

import java.io.BufferedReader;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import mll.beans.Artist;
import mll.beans.Genre;
import mll.beans.Metadata;
import mll.beans.Owner;
import mll.beans.Song;
import mll.dao.SubmissionDAO;
import mll.utility.Configuration;

public class SubmissionService
{
	SubmissionDAO dao;
	Configuration conf = new Configuration();
	
	public SubmissionService() 
	{
		dao = new SubmissionDAO();
	}
	
	/**
	* This method takes http request and response objects as input and 
	* first validates the request and if it is valid then save the media
	* files and related media information.
	*
	* @author  Dhaval Patel
	* @version 1.0
	* @since   2016-03-24 
	*/
	@SuppressWarnings("unchecked")
	public JSONObject uploadMedia(HttpServletRequest request, HttpServletResponse response)
	{
		JSONObject responseObject = new JSONObject();
		
		try
		{
			// Validate the request and populate the metadata beans for all songs if request is valid
			List<Metadata> metadatas = null;
			if(ServletFileUpload.isMultipartContent(request))
			{
				metadatas = populateMetadataBeansFromMultipartRequest(request);
			}
			else
			{
				metadatas = populateMetadataBeansFromRequest(request);
			}
			
			if(null != metadatas && !metadatas.isEmpty())
			{
				// Save all media files with metadata
				metadatas = dao.saveMetadata(metadatas);
				
				//
				if(conf.IS_NUXEO_ENABLED)
				{
					NuxeoService nuxeoService = new NuxeoService();
					nuxeoService.uploadMedia(metadatas);
				}
				
				responseObject.put("isUploaded", true);
				responseObject.put("message", "Media files uploaded successfully.");
			}
			else
			{
				responseObject.put("isUploaded", false);
				responseObject.put("message", "Request does not contain valid data. Please upload with proper metadata information.");
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			responseObject.put("isUploaded", false);
			responseObject.put("message", "Error while saving the data. Please upload with proper metadata information.");
		}
		
		return responseObject;
	}
	
	/**
	* This method takes http request as input and validates the 
	* request and if it is valid then creates the metadata list 
	* from the request object.
	*
	* @author  Dhaval Patel
	* @version 1.0
	* @since   2016-03-24 
	*/
	public List<Metadata> populateMetadataBeansFromRequest(HttpServletRequest request) throws Exception
	{
		List<Metadata> metadatas = new ArrayList<Metadata>();
		Metadata metadata =  new Metadata();
		
		StringBuffer requestStr = new StringBuffer();
	   	BufferedReader reader = request.getReader();
    	String line = null;
    	while ((line = reader.readLine()) != null)
    	{
    		requestStr.append(line);
    	}
    	
    	JSONParser parser = new JSONParser();
    	JSONObject mainObject = (JSONObject) parser.parse(requestStr.toString());
	    JSONObject generalInformation =  (JSONObject) mainObject.get("generalInformation");
	    JSONObject ownershipInformation =  (JSONObject) mainObject.get("ownershipInformation");
	    JSONObject soundInformation =  (JSONObject) mainObject.get("soundInformation");
	    
	    // Populate Song Information	    
	    populateSong(metadata, generalInformation, ownershipInformation, (String) mainObject.get("file"), null);
	    
	    // Populate Song Artists Information	 
	    populateSongArtists(metadata, generalInformation);
	    
	    // Populate Song Genres Information	 
	    populateSongGenres(metadata, generalInformation);
	    
	    // Populate Song Writers Information	 
	    populateSongWriters(metadata, ownershipInformation);
	    
	    // Populate Song Recorders Information	 
	    populateSongRecorders(metadata, soundInformation);
	    
	    // set Json Object For Nuxeo
	    metadata.setMetadataJson(mainObject);
	    
		metadatas.add(metadata);
		return metadatas;
	}
	
	/**
	* This method takes http multipart request as input and validates  
	* the request and if it is valid then creates the metadata list 
	* from the request object.
	*
	* @author  Dhaval Patel
	* @version 1.0
	* @since   2016-03-24 
	*/
	@SuppressWarnings("unchecked")
	public List<Metadata> populateMetadataBeansFromMultipartRequest(HttpServletRequest request) throws Exception
	{
		List<Metadata> metadatas = new ArrayList<Metadata>();
		Metadata metadata =  new Metadata();

		JSONParser parser = new JSONParser();
	    JSONObject generalInformation =  null;
	    JSONObject ownershipInformation =  null;
	    JSONObject soundInformation =  null;
	    
	    byte[] hardDriveContent = null;
	    
		List<FileItem> multiparts = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);
		for (FileItem item : multiparts) 
		{
			if (!item.isFormField()) 
	        {
				InputStream filecontent = item.getInputStream();
				int available = filecontent.available();
				hardDriveContent = new byte[available];
				int readBytes = filecontent.read(hardDriveContent,0,available);
	     	
				if(readBytes != available) 
	     	   	{
					return null;
	     	   	}
	     	   	filecontent.close();
	        } 
	        else 
	        {
	        	if(item.getFieldName().equalsIgnoreCase("generalInformation"))
	        	{
	        		generalInformation = (JSONObject) parser.parse(item.getString());
	        	}
	        	else if(item.getFieldName().equalsIgnoreCase("ownershipInformation"))
	        	{
	        		ownershipInformation = (JSONObject) parser.parse(item.getString());
	        	}
	        	else if(item.getFieldName().equalsIgnoreCase("soundInformation"))
	        	{
	        		soundInformation = (JSONObject) parser.parse(item.getString());
	        	}
	        }
	    }
		
	    // Populate Song Information	    
	    populateSong(metadata, generalInformation, ownershipInformation, null, hardDriveContent);
	    
	    // Populate Song Artists Information	 
	    populateSongArtists(metadata, generalInformation);
	    
	    // Populate Song Genres Information	 
	    populateSongGenres(metadata, generalInformation);
	    
	    // Populate Song Writers Information	 
	    populateSongWriters(metadata, ownershipInformation);
	    
	    // Populate Song Recorders Information	 
	    populateSongRecorders(metadata, soundInformation);
	    
	    // createJsonObject For Nuxeo
	    JSONObject mainObject = new JSONObject();
	    mainObject.put("generalInformation", generalInformation);
	    mainObject.put("ownershipInformation", ownershipInformation);
	    mainObject.put("soundInformation", soundInformation);
	    metadata.setMetadataJson(mainObject);
	    
		metadatas.add(metadata);
		return metadatas;
	}
	
	
	/**
	* This method takes metadata object and json objects as input 
	* and fetches the song information from those objects.
	*
	* @author  Dhaval Patel
	* @version 1.0
	* @since   2016-03-25 
	*/
	public Metadata populateSong(Metadata metadata,  JSONObject generalInformation, JSONObject ownershipInformation, String dropboxURL, byte[] hardDriveContent) throws Exception
	{
		if(null == metadata)
		{
			return null;
		}
		
		if(null == generalInformation || null == ownershipInformation)
		{
			return metadata;
		}
		
		Song song = new Song();
	    song.setBeatsPerMin((Long)generalInformation.get("beatRate"));
	    song.setTitle((String) generalInformation.get("title"));
	    song.setCopyrightNo((String) ownershipInformation.get("copyright"));    
	    song.setPublishingCompany((String) ownershipInformation.get("pubCompany")); 
	    song.setPro((String) ownershipInformation.get("pbo"));
	    
	    if(null != generalInformation.get("userId"))
	    {
	    	song.setUserId(((Long)generalInformation.get("userId")).intValue());
	    }
	    else
	    {
	    	//TODO
	    	song.setUserId(2);
	    }
	    
	    if(null != dropboxURL && !"".equals(dropboxURL))
	    {
	    	song.setContentURL(dropboxURL);
			song.setSourceOfContent("DROPBOX");
			
			// Get File Content from Drop Box
			DropboxService ds = new DropboxService();
			song.setContent(ds.getContentFromDropbox(dropboxURL));
	    }
	    else
	    {
	    	song.setContentURL(null);
			song.setSourceOfContent("HARDDRIVE");
			song.setContent(hardDriveContent);
	    }
	    
	    metadata.setSong(song);
	    
	    return metadata;
	}
	
	/**
	* This method takes metadata object and json object as input 
	* and fetches the song artist information from json array.
	*
	* @author  Dhaval Patel
	* @version 1.0
	* @since   2016-03-25 
	*/
	public Metadata populateSongArtists(Metadata metadata, JSONObject generalInformation) throws Exception
	{
		if(null == metadata)
		{
			return null;
		}
		
		if(null == generalInformation)
		{
			return metadata;
		}
		
		JSONArray artists= (JSONArray) generalInformation.get("artists");
	    for(int i=0; i<artists.size(); i++)
	    {
	    	Artist artist = new Artist();
	    	artist.setName((String) ((JSONObject)artists.get(i)).get("name"));
	    	metadata.getArtists().add(artist);
	    }
	    
	    return metadata;
	}
	
	/**
	* This method takes metadata object and json object as input 
	* and fetches the song genres information from json array.
	*
	* @author  Dhaval Patel
	* @version 1.0
	* @since   2016-03-25 
	*/
	public Metadata populateSongGenres(Metadata metadata, JSONObject generalInformation) throws Exception
	{
		if(null == metadata)
		{
			return null;
		}
		
		if(null == generalInformation)
		{
			return metadata;
		}
		
		Genre primaryGenre = new Genre();
		primaryGenre.setGenre((String) generalInformation.get("primaryGenre"));
    	metadata.getGenres().add(primaryGenre);
    	
		Genre secondaryGenre = new Genre();
		secondaryGenre.setGenre((String) generalInformation.get("secondaryGenre"));
    	metadata.getGenres().add(secondaryGenre);
		
		/*
		If Genre are send as an array from UI side - Previous Code
		JSONArray genres = (JSONArray) generalInformation.get("genres");
	    for(int i=0; i<genres.size(); i++)
	    {
	    	Genre genre = new Genre();
	    	genre.setGenre((String) genres.get(i));
	    	metadata.getGenres().add(genre);
	    }
	    */
	    
	    return metadata;
	}
	
	/**
	* This method takes metadata object and json object as input 
	* and fetches the sound writers information from json array.
	*
	* @author  Dhaval Patel
	* @version 1.0
	* @since   2016-03-25 
	*/
	public Metadata populateSongWriters(Metadata metadata, JSONObject ownershipInformation) throws Exception
	{
		if(null == metadata)
		{
			return null;
		}
		
		if(null == ownershipInformation)
		{
			return metadata;
		}
		
		JSONArray songwriters = (JSONArray) ownershipInformation.get("songwriters");
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
	    	
	    	metadata.getOwners().add(owner);
	    }
	    
	    return metadata;
	}
	
	/**
	* This method takes metadata object and json object as input 
	* and fetches the sound recorders information from json array.
	*
	* @author  Dhaval Patel
	* @version 1.0
	* @since   2016-03-25 
	*/
	public Metadata populateSongRecorders(Metadata metadata, JSONObject soundInformation) throws Exception
	{
		if(null == metadata)
		{
			return null;
		}
		
		if(null == soundInformation)
		{
			return metadata;
		}
		
		JSONArray recorders = (JSONArray) soundInformation.get("soundOwners");
	    for(int i=0; i<recorders.size(); i++)
	    {
	    	JSONObject recorder =  (JSONObject) recorders.get(i);
	    	Owner owner = new Owner();
	    	
	    	owner.setDivisonOfOwnership("Half");
			owner.setName((String)recorder.get("name"));
			owner.setOwnerType("RECORDING");
			owner.setPrimaryEmail((String)recorder.get("primaryEmail"));
			owner.setSecondaryEmail((String)recorder.get("secondaryEmail"));
			owner.setPrimaryPhone((String)recorder.get("primaryPhone"));
			owner.setSecondaryPhone((String)recorder.get("secondaryPhone"));
	    	
	    	metadata.getOwners().add(owner);
	    }
	    
	    return metadata;
	}
}