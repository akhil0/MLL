package mll.service;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.URL;

public class DropboxService 
{
	/**
	* This method takes dropbox url and download the content
	* on from the given url if its valid.
	*
	* @author  Dhaval Patel
	* @version 1.0
	* @since   2016-03-25
	*/
	public byte[] getContentFromDropbox(String urlStr) throws Exception
	{
		if(null == urlStr || "".equals(urlStr))
		{
			return null;
		}
		
		try
		{
			URL url = new URL(urlStr);
			InputStream in = new BufferedInputStream(url.openStream());
			
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			byte[] content = new byte[1024];
			int n = 0;
			while (-1 != (n = in.read(content))) 
			{
			    out.write(content, 0, n);
			}
			out.close();
			in.close();
			
			return content;
		}
		catch(Exception e)
		{
			return null;
		}
		
		
	}
}
