package mll.dao;

import java.util.List;
import mll.beans.Musician;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.Query;
import mll.utility.SessionFactoryUtil;

public class ARHomePageDAO {

	
	public List<Musician> getMusicians(int id){
		
		Session session = null;
		
		Transaction tx = null;
		
		List<Musician> musicians = null;
		try{
			session = SessionFactoryUtil.getSessionFactory().getCurrentSession();
			tx = session.beginTransaction();
			if(id == -1){
				return null;
			}
			
			Query q = session.createQuery("FROM mll.beans.Musician m where m.added_by=:id");
			q.setParameter("id", id);
			musicians = q.list();
			
			return musicians;
			
		}catch(Exception e){
			e.printStackTrace();
			System.exit(0);
		}
		return musicians;
	}

}
