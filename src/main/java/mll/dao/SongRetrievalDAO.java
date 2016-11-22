package mll.dao;

import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.json.JSONObject;
import mll.beans.Owner;
import mll.utility.SessionFactoryUtil;

public class SongRetrievalDAO {

	public JSONObject retrieveOwnerInfo(String songId)
	{
		JSONObject ownerInfo=null;
		Session session = null;
		Transaction tx = null;
		if(songId!=null)
		{
			ownerInfo=new JSONObject();
			try
			{
				session=SessionFactoryUtil.getSessionFactory().getCurrentSession();
				tx = session.beginTransaction();
				Query query = session.createQuery("from Owner o where o.songId=:songId");
				query.setString("songId",songId);
				List<Owner> owners=query.list();
				if(owners!=null && owners.size()>0)
				{
					for(Owner owner:owners)
					{
						JSONObject obj=new JSONObject();
						obj.put("Name", owner.getName());
						obj.put("divisionOfOwnership", owner.getDivisonOfOwnership());
						obj.put("primayPhone", owner.getPrimaryPhone());
						obj.put("secondaryPhone", owner.getSecondaryPhone());
						obj.put("primayEmail", owner.getPrimaryEmail());
						obj.put("secondaryEmail", owner.getSecondaryEmail());
						ownerInfo.put(owner.getOwnerType(), obj);
					}
					
				}
				session.close();
				
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		return ownerInfo;
	}
}