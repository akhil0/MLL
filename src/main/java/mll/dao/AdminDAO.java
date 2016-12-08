package mll.dao;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import mll.beans.AdminUser;
import mll.utility.SessionFactoryUtil;

public class AdminDAO {

	public String getUserName(int userId)
	{
		Session session = null;
		Transaction tx = null;
		String userName = "";
		
		try {
			session = SessionFactoryUtil.getSessionFactory().getCurrentSession();
			tx = session.beginTransaction();
			
			Query query = session.createQuery("FROM mll.beans.AdminUser a WHERE a.id=:userId");
			query.setParameter("userId", userId);
			
			AdminUser adminUser = (AdminUser) query.uniqueResult();
			
			if(adminUser == null)
				return userName;
			
			userName = adminUser.getFirstName();
			
			tx.commit();
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return userName;	
	}
}
