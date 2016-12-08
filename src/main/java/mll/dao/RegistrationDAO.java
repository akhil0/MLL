package mll.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.json.simple.JSONObject;

import mll.beans.Invite;
import mll.beans.UserDetails;
import mll.utility.SessionFactoryUtil;

public class RegistrationDAO 
{

	@SuppressWarnings("unchecked")
	public JSONObject registerUser(UserDetails userdetails) throws Exception
	{
		Session session = null;
		Transaction tx = null;
		
		JSONObject responseObject = new JSONObject();
		
		try
		{
			if(null != userdetails && null != userdetails.getToken() && null != userdetails.getToken().getToken() && null != userdetails.getToken().getInviteType() && null != userdetails.getUsers())
			{
				// Initialize the session and transaction
				session = SessionFactoryUtil.getSessionFactory().getCurrentSession();
				tx = session.beginTransaction();
				
				Invite invite = new Invite();
				invite.setToken(userdetails.getToken());
				
				InviteDAO inviteDao = new InviteDAO();
				invite = inviteDao.validateInvite(invite);
				
				if(invite.getIsValid())
				{		invite.getToken().setIsUsed(true);
						session.update(invite.getToken());
						
						Integer userId = (Integer)session.save(userdetails.getUsers());
						
						if(null != userId)
						{

							responseObject.put("isRegistered", true);
							responseObject.put("userId", userId);
							responseObject.put("type", userdetails.getType());

							
							if(userdetails.getType().equalsIgnoreCase("user"))
							{
								userdetails.getAruser().setId(userId);
								session.save(userdetails.getAruser());
						        responseObject.put("browse", true);
								responseObject.put("upload", false);
								
							}
							else if(userdetails.getType().equalsIgnoreCase("musician"))
							{
								userdetails.getMusician().setId(userId);
								userdetails.getMusician().setAdded_by(invite.getToken().getUserId());
								session.save(userdetails.getMusician());
						        responseObject.put("browse", false);
								responseObject.put("upload", true);
								responseObject.put("type", userdetails.getType());
							}
						}
						else
						{
							responseObject.put("isRegistered", false);
							responseObject.put("errorMessage", "Not able to save user details.");
						}
					
				}
				else 
				{
					responseObject.put("isRegistered", false);
					responseObject.put("errorMessage", "Token is already used.");
				}
					
				tx.commit();
			}
		}
		catch(Exception e)
		{
			if( null != tx)
			{
				 tx.rollback();
			}
			e.printStackTrace();
			
			responseObject.put("isRegistered", false);
			responseObject.put("errorMessage", "Error while saving data.");
		}
		return responseObject;
	}
	
	public Boolean checkAlreadyExists(String username) {
		// TODO Auto-generated method stub
		Transaction tx = null;
		Session session =null;
		Boolean result = false;
		if (username != null){
			try{
				session = SessionFactoryUtil.getSessionFactory().getCurrentSession();
				tx = session.beginTransaction();
				String hql = "FROM User WHERE userName = :username";
				Query query = session.createQuery(hql);
				query.setParameter("username", username);
				List results = query.list();
				if (results.size()>0){
					result = true;
				}else
					result = false;
				tx.commit();
				return result;
			}
			catch(Exception e){
				if( null != tx)
				{
					 tx.rollback();
				}
				e.printStackTrace();
				return result;
			}
		} else
			return result;
	
}
		
}
