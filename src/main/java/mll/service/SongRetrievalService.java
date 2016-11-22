package mll.service;

import org.json.JSONArray;

public class SongRetrievalService {
	
	RazunaService razunaservice;
	public void retrieveSongsOfMusician(String folderid)
	{
		razunaservice=new RazunaService();
		try
		{
			JSONArray responseArray=razunaservice.RetrieveSongs(folderid);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}

}
