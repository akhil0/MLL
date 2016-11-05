package mll.service;

import java.util.List;
import mll.beans.Metadata;
import mll.utility.Configuration;

public class RazunaService
{

	public Boolean uploadMedia(List<Metadata> metadata)
	{
		Boolean serviceResponse = null;
		try
		{
			String charset = "UTF-8";
			Configuration config = new Configuration();
			byte[] content = null;
			String fileName = null;
			for(Metadata md : metadata){
				content = md.getSong().getContent();
				fileName = md.getSong().getFileName();
				MultipartUtility multipart = new MultipartUtility(config.RAZUNA_URL, charset);

				multipart.addHeaderField("User-Agent", "Shivani");
				multipart.addHeaderField("Test-Header", "Header-Value");

				multipart.addFormField("fa", "c.apiupload");
				multipart.addFormField("api_key", config.RAZUNA_KEY);
				multipart.addFormField("destfolderid", "CC5DFF287F274153963FF173C505A956");
				
				multipart.addFilePart(fileName, content);
				List<String> response = multipart.finish();
				if(response.contains("SUCCESS")){
					serviceResponse = true;
				} else {
					serviceResponse = false;
				}
				
				/*
				System.out.println("SERVER REPLIED:");
				
				for (String line : response) {
					System.out.println(line);
				}*/
			}
		}
			
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return serviceResponse;
	}

}

