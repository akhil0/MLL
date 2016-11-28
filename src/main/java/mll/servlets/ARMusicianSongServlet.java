package mll.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.http.ParseException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.simple.JSONObject;
import mll.service.RazunaService;

public class ARMusicianSongServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;
	RazunaService razunaService = null;
	
	public void init(ServletConfig config) throws ServletException 
	{
		razunaService = new RazunaService();
	}
	
	@SuppressWarnings("unchecked")
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException  
	{
		String folderId = request.getParameter("folderId");
		JSONObject responseObject = new JSONObject();
		JSONArray jsonArray = null;
		try {
			jsonArray = razunaService.RetrieveSongs(folderId);
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		}
		responseObject.put("songs", jsonArray);
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		out.print(responseObject);
		out.flush();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		doGet(request, response);
	}
}