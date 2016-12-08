package mll.service;

import org.json.JSONArray;

import mll.dao.RetrieveBandInformationDAO;

public class RetrieveBandInformationService {

	public JSONArray retrieveBand(int id)
	{
		 RetrieveBandInformationDAO dao= new RetrieveBandInformationDAO();
		 JSONArray arry=dao.retrieveSong(id);
		 if(arry!=null)
			 return arry;
		 else
			 return new JSONArray();
	}
	
	
	public static void main(String args[])
	{
		RetrieveBandInformationService ser=new RetrieveBandInformationService();
		System.out.println(ser.retrieveBand(4));
	}
}
