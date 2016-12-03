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
		

		return token;
	}
}