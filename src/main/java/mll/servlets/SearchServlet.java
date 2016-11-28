package mll.servlets;

import java.io.IOException;

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
 * Servlet implementation class SearchServlet
 */
//@WebServlet("/SearchServlet")
public class SearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private RazunaService razunaservice;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchServlet() {
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
	    String folderid=new String();
	    String searchWord = new String();
	    JSONArray songs=new JSONArray();
	    if(session.getAttribute("folder_id")!=null)
	    {
	    	folderid=(String) session.getAttribute("folder_id");
	    	searchWord = (String) session.getAttribute("search");
	    	System.out.println("folderid is "+folderid);
	    	
	    }
	    try {
			songs=razunaservice.searchKeyword(folderid, searchWord);
			
		} catch (ParseException | JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
