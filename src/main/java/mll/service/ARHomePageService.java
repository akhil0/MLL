package mll.service;

import java.util.ArrayList;
import java.util.List;

import mll.beans.ARuser;
import mll.beans.Musician;
import mll.beans.Token;

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
	
	
	public List<Musician> getRegisteredMusicians(ARuser arUser){	
		
		return new ArrayList<Musician>();
	}


	public static List<Token> getUnRegisteredMusicians(ARuser arUser){	
	
		return new ArrayList<Token>();
		
	}
	

}
