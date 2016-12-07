package mll.service;

import mll.beans.Token;
import mll.dao.TokenDAO;
import mll.utility.Configuration;

public class ResendEmailtoMusicianService
{
	TokenDAO dao;

	public ResendEmailtoMusicianService() {
		dao = new TokenDAO();
	}
	
	public static void main(String args[])
	{
		System.out.println(new ResendEmailtoMusicianService().sendEmail("MLLTKN26"));
	}
	
	public boolean sendEmail(String token)
	{
		Token tokenObj = null;
		try {
			tokenObj = dao.getTokenForTokenId(token);
			if(tokenObj != null && tokenObj.getIsUsed() == false) {
				MailService mailservice = new MailService();
				Configuration conf = new Configuration();
				String url = conf.INVITE_URL+ "musician" + "/registration/token/" + token;
				String subjectLine = "Invite from Media Licencing Lab";
				String msg = "\n\nHere is your personalized invitation link. Use it to create your profile in the platform :: " + url + "\n\nIf you received this message in error, or if you have a problem during the registration process, please contact "+ conf.EMAIL_ADDRESS_FOR_SUPPORT +".\n\nThanks,\nMedia Team";
				mailservice.sendMail(tokenObj.getEmailId(), subjectLine, msg);
				return true;
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}		
}