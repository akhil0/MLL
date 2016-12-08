package mll.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.json.JSONArray;
import org.json.simple.JSONObject;

import mll.beans.ARuser;
import mll.beans.AdminUser;
import mll.beans.Band;
import mll.beans.Login;
import mll.beans.Musician;
import mll.beans.User;
import mll.utility.SessionFactoryUtil;

public class LoginDAO {

	/**
	 * This method takes the user object consisting of Login credentials and 
	 * returns a Login object depending on whether the user is a musician or 
	 * an admin user.  
	 * @author  Vishal Mehta
	 * @version 1.0
	 * @since   2016-04-06 
	 */
	@SuppressWarnings("unchecked")
	/*public Login validateLogin(Login login) throws Exception 
	{
		// TODO Auto-generated method stub
		Session session = null;
		Transaction tx = null;
		
		try
		{
			// Initialize the session and transaction
			session = SessionFactoryUtil.getSessionFactory().getCurrentSession();
			tx = session.beginTransaction();

			// Query the User, musician and AdminUser Objects to validate the login credentials and determine the type of the user.
			if(null != login && null != login.getUser() && null != login.getUser().getUserName() && null != login.getUser().getPassword() && !"".equals(login.getUser().getUserName()) && !"".equals(null != login.getUser().getPassword()))
			{
				Query query = session.createQuery("from User u where u.userName=:userName and u.password=:password");
				query.setString("userName", login.getUser().getUserName());
				query.setString("password", login.getUser().getPassword());
				//System.out.println(login.getUser().getPassword());
				
				List<User> users = query.list();
				
				if(null != users && users.size() > 0 && null != users.get(0) && null != users.get(0).getId())
				{
					login.getUser().setId(users.get(0).getId());
					login.getUser().setEmailId(users.get(0).getEmailId());
					
					Query musician = session.createQuery("from Musician m where m.id=:id");
					musician.setInteger("id", login.getUser().getId());
					
					List<Musician> ms = musician.list();
					
					if(null != ms && ms.size() > 0 && null != ms.get(0) && null != ms.get(0).getId())
					{
						login.setMusician(ms.get(0));
						login.setType(Login.musicianType);
						login.setValidUser(true);
						login.setCanUpload(true);
						login.setCanBrowse(false);
					} 
					else if 
					{
						Query ar_user = session.createQuery("from ARuser au where au.id=:id");
						ar_user.setInteger("id", login.getUser().getId());
						
						List<AdminUser> aus = ar_user.list();
						
						if(null != aus && aus.size() > 0 && null != aus.get(0) && null != aus.get(0).getId())
						{
							login.setAdmin(aus.get(0));
							login.setType(Login.adminUserType);
							login.setValidUser(true);
							login.setCanUpload(false);
							login.setCanBrowse(true); 
						}
						else
						{
							login.setValidUser(false);
							login.setErrMsg("Username and/or password doesn't match. Please provide valid credentials.");
						}
					}
					else 
					{
						Query admin_user = session.createQuery("from AdminUser au where au.id=:id");
						admin_user.setInteger("id", login.getUser().getId());
						
						List<AdminUser> aus = admin_user.list();
						
						if(null != aus && aus.size() > 0 && null != aus.get(0) && null != aus.get(0).getId())
						{
							login.setAdmin(aus.get(0));
							login.setType(Login.adminUserType);
							login.setValidUser(true);
							login.setCanUpload(false);
							login.setCanBrowse(true); 
						}
						else
						{
							login.setValidUser(false);
							login.setErrMsg("Username and/or password doesn't match. Please provide valid credentials.");
						}
					}
				}
				else
				{
					login.setValidUser(false);
					login.setErrMsg("Username and/or password doesn't match. Please provide valid credentials.");
				}
			}
			
			// Commit the transaction if all the data is successfully saved
			tx.commit();
		}
		catch(Exception e)
		{
			if( null != tx)
			{
				// Rollback the transaction if any error comes during the process
				tx.rollback();
			}
			e.printStackTrace();
			throw e;
		}
		return login;
	}*/
	
	
	public Login validateLogin(Login login) throws Exception 
	{
		// TODO Auto-generated method stub
		Session session = null;
		Transaction tx = null;
		
		try
		{
			// Initialize the session and transaction
			session = SessionFactoryUtil.getSessionFactory().getCurrentSession();
			tx = session.beginTransaction();

			// Query the User, musician and AdminUser Objects to validate the login credentials and determine the type of the user.
			if(null != login && null != login.getUser() && null != login.getUser().getUserName() && null != login.getUser().getPassword() && !"".equals(login.getUser().getUserName()) && !"".equals(null != login.getUser().getPassword()))
			{
				Query query = session.createQuery("from User u where u.userName=:userName and u.password=:password");
				query.setString("userName", login.getUser().getUserName());
				query.setString("password", login.getUser().getPassword());
				//System.out.println(login.getUser().getPassword());
				
				List<User> users = query.list();
				if(null != users && users.size() > 0 && null != users.get(0) && null != users.get(0).getId())
				{
					login.getUser().setId(users.get(0).getId());
					login.getUser().setEmailId(users.get(0).getEmailId());
					login.getUser().setUserType(users.get(0).getUserType());
					String userType=users.get(0).getUserType();
					
					if(userType!=null && userType.equalsIgnoreCase("Musician"))
					{
						Query musician = session.createQuery("from Musician m where m.id=:id");
						Query band=session.createQuery("from Band b where b.musician_id=:id");
						musician.setInteger("id", login.getUser().getId());
						JSONArray bandarray=new JSONArray();
						band.setInteger("id", login.getUser().getId());
						List<Musician> ms = musician.list();
						List<Band> bands=band.list();
						if(null != ms && ms.size() > 0 && null != ms.get(0) && null != ms.get(0).getId())
						{
							login.setMusician(ms.get(0));
							login.setType(Login.musicianType);
							login.setValidUser(true);
							login.setCanUpload(true);
							login.setCanBrowse(false);
						} 
						if(bands!=null && bands.size() >0){
							for(Band ban:bands)
							{
								JSONObject obj=new JSONObject();
								obj.put("id", ban.getId());
								obj.put("name", ban.getName());
								bandarray.put(obj);
							}
							login.setBands(bandarray);
						}
					}
					else if(userType!=null && userType.equalsIgnoreCase("ARUser"))
					{
						Query ar_user = session.createQuery("from ARuser au where au.id=:id");
						ar_user.setInteger("id", login.getUser().getId());
						
						List<ARuser> aus = ar_user.list();
						
						if(null != aus && aus.size() > 0 && null != aus.get(0) && null != aus.get(0).getId())
						{
							login.setAruser(aus.get(0));
							login.setType(Login.adminUserType);
							login.setValidUser(true);
							login.setCanUpload(false);
							login.setCanBrowse(true); 
						}
						
					}
					else 
					{
						Query admin_user = session.createQuery("from AdminUser au where au.id=:id");
						admin_user.setInteger("id", login.getUser().getId());
						
						List<AdminUser> aus = admin_user.list();
						
						if(null != aus && aus.size() > 0 && null != aus.get(0) && null != aus.get(0).getId())
						{
							login.setAdmin(aus.get(0));
							login.setType(Login.adminUserType);
							login.setValidUser(true);
							login.setCanUpload(false);
							login.setCanBrowse(true); 
						}
						
					}
				}
				else
				{
					login.setValidUser(false);
					login.setErrMsg("Username and/or password doesn't match. Please provide valid credentials.");
				}
			}
			
			// Commit the transaction if all the data is successfully saved
			tx.commit();
		}
		catch(Exception e)
		{
			if( null != tx)
			{
				// Rollback the transaction if any error comes during the process
				tx.rollback();
			}
			e.printStackTrace();
			throw e;
		}
		return login;
	}
}

