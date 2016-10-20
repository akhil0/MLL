package mll.service;

import java.util.List;

/*
import org.nuxeo.ecm.automation.client.Constants;
import org.nuxeo.ecm.automation.client.Session;
import org.nuxeo.ecm.automation.client.adapters.DocumentService;
import org.nuxeo.ecm.automation.client.jaxrs.impl.HttpAutomationClient;
import org.nuxeo.ecm.automation.client.model.Document;
import org.nuxeo.ecm.automation.client.model.PathRef;
*/

import mll.beans.Metadata;
import mll.utility.Configuration;

public class NuxeoService
{
	Configuration conf = new Configuration();
	//HttpAutomationClient client = null;
	//Session session = null;
	
	public NuxeoService() 
	{
		try
		{
			//client = new HttpAutomationClient(conf.NUXEO_URL);
			//session = (Session) client.getSession(conf.NUXEO_USER_NAME, conf.NUXEO_PASSWORD);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void uploadMedia(List<Metadata> metadatas)
	{
		try
		{
			if(null != metadatas)
			{
				for(Metadata metadata : metadatas)
				{
					if(null != metadata && null != metadata.getSong() && null != metadata.getSong().getId())
					{
						/*
						// Fetch the root of Nuxeo repository
						Document root = (Document) session.newRequest("Document.Fetch").set("value", "/").execute();
						
						// create a file document
						  
						// We cannot send byte array to Nuxeo so we can write byte array to server directory and then send that file path to Nuxeo. 
						session.newRequest("Document.Create").setInput(root).set("type", "File").set("name", "myfile"+metadata.getSong().getId()).set("properties", "dc:title=MLL-SONG "+metadata.getSong().getId()).execute();
						session.newRequest("Blob.Attach").setHeader(Constants.HEADER_NX_VOIDOP, "true").setInput(metadata.getSong().getContent()).set("document", "/myfile"+metadata.getSong().getId()).execute();
						
						//Fetch the document for JSON
						Document document = (Document) session.newRequest(DocumentService.GetDocumentChild).setInput(new PathRef("/")).set("name", "myfile"+metadata.getSong().getId()).execute();
						document.set("dc:description", metadata.getMetadataJson().toString());
						document = (Document) session.newRequest("Document.Update").setInput(document).set("properties", document).execute();
						*/
					}
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}