package mll.dao;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import mll.utility.SessionFactoryUtil;

public class UpdateSongMetadataDAO {
 
	Session session = null;
	Transaction tx = null;
	public String updateSongMetadata(JSONArray writerinfo, JSONArray recordinginfo) throws JSONException
	{
		if(writerinfo!=null && recordinginfo!=null)
		{
		
			try
			{
				session = SessionFactoryUtil.getSessionFactory().getCurrentSession();
				tx = session.beginTransaction();
		
		
				if(writerinfo!=null && writerinfo.length() > 0)
				{
					for(int i=0;i<writerinfo.length(); i++)
					{
						JSONObject obj= writerinfo.getJSONObject(0);
						update(obj,session,tx); 
					}
				}
		
				if(recordinginfo!=null && recordinginfo.length() > 0)
				{
					for(int i=0;i<recordinginfo.length(); i++)
					{
						JSONObject obj= recordinginfo.getJSONObject(0);
						update(obj,session,tx); 
					}
				}
			
				if(tx!=null)
					tx.commit();
			}
			catch(Exception e)
			{
				e.printStackTrace();
				if(null != tx)
				{
					tx.rollback();
				
				}
				return "failure";
			}
			return "success";
		}
		else
			return "failure";
	}
	
	public int update(JSONObject obj, Session session, Transaction tx ) throws JSONException, HibernateException
	{
		String divison_of_ownership=new String();
		String primary_phone_no=new String();
		String secondary_phone_no=new String();
		String primary_email_id = new String();
		String secondary_email_id = new String();
		 
		 int id = Integer.parseInt(obj.getString("id"));
		 String name=obj.getString("Name");
		 if(obj.has("divisionOfOwnership"))
			  divison_of_ownership= obj.getString("divisionOfOwnership");
		 if(obj.has("primayPhone"))	 
		  primary_phone_no=obj.getString("primayPhone");
		 if(obj.has("secondaryPhone"))
		  secondary_phone_no=obj.getString("secondaryPhone");
		 if(obj.has("primayEmail"))
		  primary_email_id=obj.getString("primayEmail");
		 if(obj.has("secondaryEmail"))
		  secondary_email_id = obj.getString("secondaryEmail");
		 
		 Query query=session.createQuery("update Owner owner set owner.name= :name, owner.divisonOfOwnership= :divisonOfOwnership,"
		 		+ "owner.primaryPhone= :primaryPhone,owner.secondaryPhone= :secondaryPhone,owner.primaryEmail= :primaryEmail,"
		 		+ "owner.secondaryEmail= :secondaryEmail where owner.id= :id");
		 	
		 query.setParameter("name", name);
		 query.setParameter("divisonOfOwnership", divison_of_ownership);
		 query.setParameter("primaryPhone", primary_phone_no);
		 query.setParameter("secondaryPhone", secondary_phone_no);
		 query.setParameter("primaryEmail", primary_email_id);
		 query.setParameter("secondaryEmail", secondary_email_id);
		 query.setParameter("id", id);
		 
		 return query.executeUpdate();
	}
}