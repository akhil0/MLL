package mll.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import mll.beans.ARuser;
import mll.beans.Musician;
import mll.beans.Token;
import mll.dao.ARHomePageDAO;

public class ARHomePageService {
	
	public static List<String> getRecent(){
		
		return null;
		
	}
	
	public static String getGenre(String eachSong){
		
		return null;	
	}
	
	public static String getUserWhoUploaded(String eachSong){
	
		return null;		
	}
	
	
	/**
	* This method takes Http request input  
	* gets the user id from the request and calls the getMusiciansWithId
	* to get the details about registered and unregistered musician 
	* @author  Dishant Shah
	* @version 1.0
	* @since   2016-11-23 
	*/
	public JSONObject getMusiciansForARUser(HttpServletRequest request, HttpServletResponse response) {
		if(request.getSession().getAttribute("userId").toString()!=null) {
			String userId = request.getSession().getAttribute("userId").toString();
			return getMusiciansWithId(userId);
		}
		else {
			return null;
		}
	}
		
	/**
	* This method takes userId input and call getRegisteredMusicians
	* and getUnRegisteredMusicians method and returns the Json Object
	* from data received from the DAO 
	* @author  Dishant Shah
	* @version 1.0
	* @since   2016-11-23 
	*/
	public JSONObject getMusiciansWithId(String userId){
		JSONObject responseObject = new JSONObject();		
		ARuser aRuser = new ARuser();
		aRuser.setId(Integer.parseInt(userId));
		List<Musician> registeredMusicians = getRegisteredMusicians(aRuser);
		List<Token> unregisteredMusicians = getUnRegisteredMusicians(aRuser);


		JSONArray jsonArrayRegisteredMusicians = new JSONArray();
		JSONArray jsonArrayUnregisteredMusicians = new JSONArray();

		for (int index = 0; index < registeredMusicians.size(); index++) {
			JSONObject registeredMusicianObject = new JSONObject();
			registeredMusicianObject.put("musicianId", registeredMusicians.get(index).getId());
			registeredMusicianObject.put("name", registeredMusicians.get(index).getName());
			registeredMusicianObject.put("folderId", registeredMusicians.get(index).getFolderId());
			registeredMusicianObject.put("age", registeredMusicians.get(index).getAge());
			registeredMusicianObject.put("gender", registeredMusicians.get(index).getGender());
			jsonArrayRegisteredMusicians.add(registeredMusicianObject);
		}


		for (int index = 0; index < unregisteredMusicians.size(); index++) {
			JSONObject unregisteredMusicianObject = new JSONObject();
			unregisteredMusicianObject.put("tokenId", unregisteredMusicians.get(index).getId());
			unregisteredMusicianObject.put("emailId", unregisteredMusicians.get(index).getEmailId());
			// unregisteredMusicianObject.put("issuedDate",
			// unregisteredMusicians.get(index).getIssueDate());
			jsonArrayUnregisteredMusicians.add(unregisteredMusicianObject);
		}
		
		responseObject.put("registeredMusicians", jsonArrayRegisteredMusicians);
		responseObject.put("unregisteredMusicians", jsonArrayUnregisteredMusicians);
		return responseObject;
		
	}
	
	/**
	* This method takes Aruser object as input and call getRegisteredMusicians
	*  method in ARHomePageDao to get the details about 
	*  registered musician 
	* @author  Dishant Shah
	* @version 1.0
	* @since   2016-11-23 
	*/
	
	public static List<Musician> getRegisteredMusicians(ARuser arUser){	
		
		return new ARHomePageDAO().getRegisteredMusicians(arUser.getId());
	}

	/**
	* This method takes Aruser object as input and call 
	* and getUnRegisteredMusicians method in ARHomePageDao 
	* to get the details about unregistered musician 
	* @author  Vishal kotak
	* @version 1.0
	* @since   2016-11-23 
	*/

	public static List<Token> getUnRegisteredMusicians(ARuser arUser){	
	
		return new ARHomePageDAO().getUnRegisteredMusicians(arUser.getId());
		
	}
	
}
