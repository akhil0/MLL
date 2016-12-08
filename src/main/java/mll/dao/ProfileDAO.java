package mll.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.json.simple.JSONObject;

import mll.beans.Band;
import mll.beans.Metadata;
import mll.beans.Owner;
import mll.utility.SessionFactoryUtil;

public class ProfileDAO {
	
	@SuppressWarnings("unchecked")
	public void saveMetadata(JSONObject OwnerandBand,Integer musician_id) throws Exception
	{
		Session session = null;
		Transaction tx = null;
		
		try
		{
			// Initialize the session and transaction
			session = SessionFactoryUtil.getSessionFactory().getCurrentSession();
			tx = session.beginTransaction();
			// Save all owners associated with this song in Owner table
			int band_id = saveBand((String) OwnerandBand.get("bandName"),musician_id,session);
			saveOwners((List<Owner>) OwnerandBand.get("owners"), band_id, session);
			
			// Commit the transaction if all the data successfully saved
			tx.commit();
		}
		catch(Exception e)
		{
			if( null != tx)
			{
				// Rollback the transaction if any error comes during the process
				 tx.rollback();
			}
			throw e;
		}
	}

	private int saveBand(String bandName, Integer musician_id, Session session) {
		// TODO Auto-generated method stub
		Band band = new Band();
		int band_id = 0;
		if(null != bandName)
		{	
			band.setmusician_id(musician_id);
			band.setName(bandName);
			band_id = (int) session.save(band);
		}
		return band_id;
	}
	
	public void saveOwners(List<Owner> owners, Integer band_id, Session session) throws Exception
	{
		if(null != owners)
		{
			for(Owner owner : owners)
			{
				if(null != owner)
				{
					owner.setSongId(" ");
					owner.setBand_id(band_id);
					owner.setId((Integer)session.save(owner));
				}
			}
		}
	}
	}
	
