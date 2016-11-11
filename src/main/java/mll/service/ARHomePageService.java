package mll.service;

import java.util.List;

import mll.beans.Musician;

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
	
	
	public  List<Musician> getMusicians(int id){
		
		 return new ARHomePageService().getMusicians(id);		
	}
	

}
