package mll.service;

import java.util.ArrayList;
import java.util.List;

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
	
	
	public static List<Musician> getRegisteredMusicians(ARuser arUser){	
		
		return new ARHomePageDAO().getRegisteredMusicians(arUser.getId());
	}


	public static List<Token> getUnRegisteredMusicians(ARuser arUser){	
	
		return new ARHomePageDAO().getUnRegisteredMusicians(arUser.getId());
		
	}
	
	public static void main(String[] args) {
		
		ARuser user = new ARuser();
		user.setId(1);
		System.out.println(getRegisteredMusicians(user).size());
		System.out.println(getUnRegisteredMusicians(user).size());		
			
	}

}
