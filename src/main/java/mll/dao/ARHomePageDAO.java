package mll.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import mll.beans.Musician;
import mll.beans.Token;
import mll.utility.SessionFactoryUtil;

public class ARHomePageDAO {

	public List<Musician> getRegisteredMusicians(int id) {
		Session session = null;

		Transaction tx = null;

		List<Musician> musicians = null;
		try {
			session = SessionFactoryUtil.getSessionFactory().getCurrentSession();
			tx = session.beginTransaction();

			Query q = session.createQuery("FROM mll.beans.Musician m where m.added_by=:id");
			q.setParameter("id", id);
			musicians = q.list();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return musicians;

	}

	public List<Token> getUnRegisteredMusicians(int id) {
		List<Token> listOfMusicians = new ArrayList<Token>();
		
		Session session = null;

		Transaction tx = null;

		try{
			session = SessionFactoryUtil.getSessionFactory().getCurrentSession();
			tx = session.beginTransaction();
			
			Query q = session.createQuery("FROM mll.beans.Token t where t.userId=:id AND isUsed=:isRegistered");
			q.setParameter("id", id);
			q.setParameter("isRegistered", false);
			listOfMusicians = q.list();
		}catch(Exception e){
			e.printStackTrace();
		}

		return listOfMusicians;
	}
}
