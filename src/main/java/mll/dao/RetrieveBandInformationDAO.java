package mll.dao;

import java.util.List;

import org.codehaus.jackson.map.ObjectMapper;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.json.JSONArray;
import org.json.JSONObject;

import mll.beans.AdminUser;
import mll.beans.Owner;
import mll.utility.SessionFactoryUtil;

public class RetrieveBandInformationDAO {

	Session session = null;
	Transaction tx = null;
	String userName = "";
	public JSONArray retrieveSong(int id)
	
	{
		
		JSONArray ownerArray=new JSONArray();
		try {
			
			session = SessionFactoryUtil.getSessionFactory().getCurrentSession();
			tx = session.beginTransaction();
			ObjectMapper mapper=new ObjectMapper();
			
			Query query = session.createQuery("FROM mll.beans.Owner a WHERE a.band_id=:id");
			query.setParameter("id", id);
			
			List<Owner> owners=query.list();
			for(Owner owner : owners)
			{
				ownerArray.put(new JSONObject(mapper.writeValueAsString(owner)));
			}
			
			
			session.disconnect();
		return ownerArray;
	    
		
	}
		catch(Exception e)
		{
			e.printStackTrace();
			session.disconnect();
		}
		return null;
	}
}
