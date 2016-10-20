package mll.dao;

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
				{
					invite.getToken().setIsUsed(true);
					session.update(invite.getToken());
					
					Integer userId = (Integer)session.save(userdetails.getUsers());
					
					if(null != userId)
					{
						responseObject.put("isRegistered", true);
						responseObject.put("userId", userId);
						
						if(userdetails.getType().equalsIgnoreCase("user"))
						{
							userdetails.getAdminUser().setId(userId);
							session.save(userdetails.getAdminUser());
					        responseObject.put("browse", true);
							responseObject.put("upload", false);
						}
						else if(userdetails.getType().equalsIgnoreCase("musician"))
						{
							userdetails.getMusician().setId(userId);
							session.save(userdetails.getMusician());
					        responseObject.put("browse", false);
							responseObject.put("upload", true);
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
}
