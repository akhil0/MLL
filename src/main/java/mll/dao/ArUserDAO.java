package mll.dao;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import mll.beans.ARuser;
import mll.utility.SessionFactoryUtil;

public class ArUserDAO {

	public String getUserName(int userId)
	{
		Session session = null;
		Transaction tx = null;
		String userName = "";
		
		try {
			session = SessionFactoryUtil.getSessionFactory().getCurrentSession();
			tx = session.beginTransaction();
			
			Query query = session.createQuery("FROM mll.beans.ARuser a WHERE a.id=:userId");
			query.setParameter("userId", userId);
			
			ARuser arUser = (ARuser) query.uniqueResult();
			
			if(arUser == null)
				return userName;
			
			userName = arUser.getFirstName();
			
			tx.commit();			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return userName;	
	}
}
