package mll.dao;

import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import mll.beans.Token;
import mll.utility.SessionFactoryUtil;

public class TokenDAO {	
	@SuppressWarnings("unchecked")
	public Token getTokenForTokenId(String tokenId) throws Exception 
	{
		if(tokenId == null || tokenId.length() == 0) {
			return null;
		}
		
		Token token = null;
		Session session = null;
		Transaction tx = null;
		
		try
		{
			session = SessionFactoryUtil.getSessionFactory().getCurrentSession();
			tx = session.beginTransaction();
			
			Query query = session.createQuery("From Token t where t.token=:usertoken");
			query.setString("usertoken", tokenId);
			
			List<Token> tokens = query.list();
			if(tokens != null && tokens.size() > 0) {
				token = tokens.get(0);
			}
			tx.commit();
		}
		catch(Exception e)
		{
			if( null != tx)
			{
				tx.rollback();
			}
			e.printStackTrace();
			throw e;
		}

		return token;
	}
}