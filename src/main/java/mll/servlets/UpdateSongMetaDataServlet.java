package mll.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.http.ParseException;
import org.json.JSONArray;
import org.json.JSONException;

import mll.service.RazunaService;
import mll.service.UpdateSongMetadataService;

public class UpdateSongMetaDataServlet {

	RazunaService razunaservice =new RazunaService();
	UpdateSongMetadataService updateservice=new UpdateSongMetadataService();
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session=request.getSession();
	    String folderid=new String();
	    JSONArray songs=new JSONArray();
	    String result=new String();
	    if(session.getAttribute("folder_id")!=null)
	    {
	    	folderid=(String) session.getAttribute("folder_id");
	    	System.out.println("folderid is "+folderid);
	    	
	    	try {
	    		result =updateservice.updateSongMetadata(request);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				result="update unsuccessfull, please try again";
				 response.setContentType("application/html");
					PrintWriter out = response.getWriter();
					out.print(result);
					out.flush();
			}
	    }
	   
	    response.setContentType("application/html");
		PrintWriter out = response.getWriter();
		out.print(result);
		out.flush();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	
}
