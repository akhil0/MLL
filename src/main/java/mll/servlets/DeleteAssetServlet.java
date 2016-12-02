package mll.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.http.ParseException;
import org.json.JSONArray;
import org.json.JSONException;

import mll.service.RazunaService;

/**
 * Servlet implementation class DeleteAsset
 */
public class DeleteAssetServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private RazunaService razunaservice;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteAssetServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    public void init(ServletConfig config) throws ServletException 
	{
		
		razunaservice=new RazunaService();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session=request.getSession();
	    String assetId = new String();
	    JSONArray songs=new JSONArray();
	    if(session.getAttribute("folder_id")!=null)
	    {
	    	assetId = request.getParameter("assetId");	
	    }
	    try {
			razunaservice.deleteAsset(assetId);
			
		} catch (ParseException | JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		out.print(songs);
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
