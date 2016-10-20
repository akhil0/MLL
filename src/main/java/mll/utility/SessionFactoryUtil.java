package mll.utility;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
 
public class SessionFactoryUtil 
{
	 private static final SessionFactory sessionFactory;

	//We need to initialize this object only one time so static block
	 static
	 {
		 try 
		 {
			 // Create the SessionFactory from hibernate.cfg.xml
			 sessionFactory = new Configuration().configure().buildSessionFactory();
		 } 
		 catch (Throwable ex) 
		 {
			 System.err.println("Initial SessionFactory creation failed." + ex);
			 throw new ExceptionInInitializerError(ex);
		 }
	 }
	
	 public static SessionFactory getSessionFactory() 
	 {	
		 return sessionFactory;
	 }
}