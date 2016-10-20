package mll.service;

import java.io.BufferedReader;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import mll.beans.Invite;
import mll.dao.InviteDAO;

public class InviteService
{
	InviteDAO dao;
	
	public InviteService() 
	{
		dao = new InviteDAO();
	}
	
	/**
	* This method takes HTTP request and response objects as input and 
	* first validates the invite request and if it is valid then based 
	* on the access type it transfer it to proper service method and 
	* return the JSON object as a response.
	* @author  Dhaval Patel
	* @version 1.0
	* @since   2016-04-06 
	*/
	@SuppressWarnings("unchecked")
	public JSONObject handleInviteRequest(HttpServletRequest request, HttpServletResponse response)
	{
		JSONObject responseObject = new JSONObject();
		
		Invite invite = populateInviteBeansFromRequest(request);
		
		if(null != invite.getActiontype())
		{
			if(invite.getActiontype().equalsIgnoreCase("generate"))
			{
				responseObject = generateInvite(invite);
			}
			else
			{
				responseObject = validateInvite(invite);
			}
		}
		else
		{
			responseObject.put("isGenerated", false);
			responseObject.put("isValid", false);
			responseObject.put("errorMessage", "Error while processing this invite request. Please try again with valid invite details.");
		}
		
		return responseObject;
	}
	
	
	/**
	* This method takes Invite object as input and first generate the 
	* token and url string and also send the mail to the recipient. It 
	* returns the response as a JSON object. 
	* @author  Dhaval Patel
	* @version 1.0
	* @since   2016-04-06 
	*/
	@SuppressWarnings("unchecked")
	public JSONObject generateInvite(Invite invite)
	{
		try
		{
			invite = dao.generateInvite(invite);
				
			if(invite.getIsGenerated())
			{
				MailService mailService = new MailService();
				mailService.sendInvite(invite);
			}
			else
			{
				invite.setIsGenerated(false);
				invite.setMessage("Request does not contain valid data. Please submit with proper invite details.");
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			invite.setIsGenerated(false);
			invite.setMessage("Error while sending Invite. Please submit with proper invite details.");
		}
		
		JSONObject responseObject = new JSONObject();
		responseObject.put("isGenerated", invite.getIsGenerated());
		responseObject.put("message", invite.getMessage());
		responseObject.put("URL", invite.getUrl());
		
		return responseObject;
	}
	
	
	/**
	* This method takes Invite object as an input and validate the invite. 
	* Based on the invite the response JSON object will be returned.
	* @author  Dhaval Patel
	* @version 1.0
	* @since   2016-04-06 
	*/
	@SuppressWarnings("unchecked")
	public JSONObject validateInvite(Invite invite)
	{
		try
		{
			invite = dao.validateInvite(invite);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			invite.setIsValid(false);
			invite.setMessage("Error while validating Invite. Please submit again.");
		}
		
		JSONObject responseObject = new JSONObject();
		responseObject.put("isValid", invite.getIsValid());
		responseObject.put("errorMessage", invite.getMessage());
		
		return responseObject;
	}
	
	
	/**
	* This method takes http request and populate the form beans from JSON
	*  object and creates the Invite object based on the action type.
	* @author  Dhaval Patel
	* @version 1.0
	* @since   2016-04-06 
	*/
	public Invite populateInviteBeansFromRequest(HttpServletRequest request)
	{
		Invite invite = new Invite();
		
		try
		{
			StringBuffer requestStr = new StringBuffer();
		   	BufferedReader reader = request.getReader();
	    	String line = null;
	    	while ((line = reader.readLine()) != null)
	    	{
	    		requestStr.append(line);
	    	}
	    	
	    	JSONParser parser = new JSONParser();
	    	JSONObject tokenJsonObject = (JSONObject) parser.parse(requestStr.toString());
	    	
	    	invite = populateInviteBeansFromRequest(invite, tokenJsonObject);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			// Error message will be set from the main method.
		}
		
		return invite;
	}
	
	public Invite populateInviteBeansFromRequest(Invite invite, JSONObject tokenJsonObject)
	{
		if(null != invite && null != tokenJsonObject)
		{
			invite.setActiontype((String) tokenJsonObject.get("actionType"));
			
			if(invite.getActiontype().equalsIgnoreCase("generate"))
			{
				invite.getToken().setEmailId((String) tokenJsonObject.get("email"));
		    	invite.getToken().setToken("");
		    	invite.getToken().setInviteType((String) tokenJsonObject.get("inviteType"));
		    	invite.getToken().setMessageBody((String) tokenJsonObject.get("messageBody"));
		    	invite.getToken().setIssueDate(new Date());
		    	invite.getToken().setIsUsed(false);
		    	invite.getToken().setUserId(((Long) tokenJsonObject.get("userId")).intValue());
			}
			else
			{
		    	invite.getToken().setToken((String) tokenJsonObject.get("token"));
		    	invite.getToken().setInviteType((String) tokenJsonObject.get("inviteType"));
			}
		}
		
		return invite;
	}
}