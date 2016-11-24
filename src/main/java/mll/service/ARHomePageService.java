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
	
	public JSONObject getMusiciansForARUser(HttpServletRequest request, HttpServletResponse response) {
		String userId = request.getParameter("userId");
		return getMusiciansWithId(userId);
		
	}
		
	public JSONObject getMusiciansWithId(String userId){
		JSONObject responseObject = new JSONObject();		
		
		return responseObject;
		
	}
	public static List<Musician> getRegisteredMusicians(ARuser arUser){	
		
		return new ARHomePageDAO().getRegisteredMusicians(arUser.getId());
	}


	public static List<Token> getUnRegisteredMusicians(ARuser arUser){	
	
		return new ARHomePageDAO().getUnRegisteredMusicians(arUser.getId());
		
	}
	
}
