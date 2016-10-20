package mll.service;

import static org.junit.Assert.assertEquals;

import org.json.simple.JSONObject;
import org.junit.Test;

import mll.beans.Invite;


public class InviteServiceTest 
{
	@Test
	public void testPopulateInviteBeansFromRequest1() throws Exception
	{
		InviteService service = new InviteService();
		assertEquals(true, service.populateInviteBeansFromRequest(null, null) == null);
	}
	
	@Test
	public void testPopulateInviteBeansFromRequest2() throws Exception
	{
		InviteService service = new InviteService();
		Invite invite = new Invite();
		assertEquals(true, service.populateInviteBeansFromRequest(invite, null) == invite);
	}
	
	@Test
	public void testPopulateInviteBeansFromRequest3() throws Exception
	{
		InviteService service = new InviteService();
		assertEquals(true, service.populateInviteBeansFromRequest(null, new JSONObject()) == null);
	}
	
	@Test
	public void testPopulateInviteBeansFromRequest4() throws Exception
	{
		InviteService service = new InviteService();
		assertEquals(true, service.populateInviteBeansFromRequest(new Invite(), getInviteGenerateJsonObject()).getActiontype().equals("generate"));
	}
	
	@Test
	public void testPopulateInviteBeansFromRequest5() throws Exception
	{
		InviteService service = new InviteService();
		assertEquals(true, service.populateInviteBeansFromRequest(new Invite(), getInviteGenerateJsonObject()).getToken().getEmailId().equals("abc@xyz.com"));
	}
	
	@Test
	public void testPopulateInviteBeansFromRequest6() throws Exception
	{
		InviteService service = new InviteService();
		assertEquals(true, service.populateInviteBeansFromRequest(new Invite(), getInviteGenerateJsonObject()).getToken().getToken().equals(""));

	}
	
	@Test
	public void testPopulateInviteBeansFromRequest7() throws Exception
	{
		InviteService service = new InviteService();
		assertEquals(true, service.populateInviteBeansFromRequest(new Invite(), getInviteGenerateJsonObject()).getToken().getInviteType().equals("user"));
	}
	
	@Test
	public void testPopulateInviteBeansFromRequest8() throws Exception
	{
		InviteService service = new InviteService();
		assertEquals(true, service.populateInviteBeansFromRequest(new Invite(), getInviteGenerateJsonObject()).getToken().getUserId() == 1);
	}
	
	@Test
	public void testPopulateInviteBeansFromRequest9() throws Exception
	{
		InviteService service = new InviteService();
		assertEquals(true, service.populateInviteBeansFromRequest(new Invite(), getInviteGenerateJsonObject()).getToken().getIsUsed() == false);
	}
	
	@Test
	public void testPopulateInviteBeansFromRequest10() throws Exception
	{
		InviteService service = new InviteService();
		assertEquals(true, service.populateInviteBeansFromRequest(new Invite(), getInviteGenerateJsonObject()).getToken().getIssueDate() != null);
	}
	
	@Test
	public void testPopulateInviteBeansFromRequest11() throws Exception
	{
		InviteService service = new InviteService();
		assertEquals(true, service.populateInviteBeansFromRequest(new Invite(), getInviteValidateJsonObject()).getActiontype().equals("validate"));
	}
	
	@Test
	public void testPopulateInviteBeansFromRequest12() throws Exception
	{
		InviteService service = new InviteService();
		assertEquals(true, service.populateInviteBeansFromRequest(new Invite(), getInviteValidateJsonObject()).getToken().getToken().equals("MLLTCKN11"));
	}
	
	@Test
	public void testPopulateInviteBeansFromRequest13() throws Exception
	{
		InviteService service = new InviteService();
		assertEquals(true, service.populateInviteBeansFromRequest(new Invite(), getInviteValidateJsonObject()).getToken().getInviteType().equals("user"));
	}
	
	@SuppressWarnings("unchecked")
	public JSONObject getInviteGenerateJsonObject()
	{
		JSONObject tokenJsonObject = new JSONObject();
		
		tokenJsonObject.put("actionType","generate");
		tokenJsonObject.put("email","abc@xyz.com");
		tokenJsonObject.put("inviteType","user");
		tokenJsonObject.put("userId",1L);
		
		return tokenJsonObject;
	}
	
	@SuppressWarnings("unchecked")
	public JSONObject getInviteValidateJsonObject()
	{
		JSONObject tokenJsonObject = new JSONObject();
		
		tokenJsonObject.put("actionType","validate");
		tokenJsonObject.put("token","MLLTCKN11");
		tokenJsonObject.put("inviteType","user");
		
		return tokenJsonObject;
	}
}

	
