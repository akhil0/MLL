package mll.service;

import java.util.List;
import mll.beans.Metadata;

public class RazunaService
{

	public Boolean uploadMedia(List<Metadata> metadata)
	{
		Boolean serviceResponse = null;
		try
		{
			String charset = "UTF-8";
			String requestURL = "http://localhost:8080/razuna/raz1/dam/index.cfm";
			
			byte[] content = null;
			String fileName = null;
			for(Metadata md : metadata){
				content = md.getSong().getContent();
				fileName = md.getSong().getFileName();
				MultipartUtility multipart = new MultipartUtility(requestURL, charset);

				multipart.addHeaderField("User-Agent", "Shivani");
				multipart.addHeaderField("Test-Header", "Header-Value");

				multipart.addFormField("fa", "c.apiupload");
				multipart.addFormField("api_key", "D8766A71A7844D14B71391EB7F047E26");
				multipart.addFormField("destfolderid", "CC5DFF287F274153963FF173C505A956");
				
				multipart.addFilePart(fileName, content);
				multipart.finish();
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

