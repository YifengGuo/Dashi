package api;

import java.io.*;
import java.util.logging.Level;//for kibana listen
import java.util.logging.Logger;//for kibana listen

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import db.DBConnection;
import db.MongoDBConnection;
import db.MySQLDBConnection;

/**
 * Servlet implementation class SearchRestaurants
 */
@WebServlet("/restaurants")
public class SearchRestaurants extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public SearchRestaurants() {
        // TODO Auto-generated constructor stub
    }
	private static final Logger LOGGER = Logger.getLogger(SearchRestaurants.class.getName());// this is extra code for Kibana
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*
		 * Question: why not add to java build path?
		 * Answer: it is runtime library not build time library
		 */
		
		// allow access only if session exists
//		HttpSession session = request.getSession();//if comment, means checking kibana
//		if (session.getAttribute("user") == null) {
//			response.setStatus(403);
//			return;
//		}
		JSONArray array = new JSONArray();
		
		DBConnection connection = new MySQLDBConnection();  //MySQL
		// DBConnection connection = new MongoDBConnection(); // MongoDB
		
		if (request.getParameterMap().containsKey("lat")
				&& request.getParameterMap().containsKey("lon")) {
			// term is null or empty by default
			String term = request.getParameter("term");
			//String userId = (String) session.getAttribute("user");
              String userId = "1111"; //hard code
			double lat = Double.parseDouble(request.getParameter("lat"));
			double lon = Double.parseDouble(request.getParameter("lon"));
			LOGGER.log(Level.INFO, "lat:" + lat + ",lon:" + lon);//extra code: kibana experiment
			array = connection.searchRestaurants(userId, lat, lon, term);
		}
		RpcParser.writeOutput(response, array);

	}


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
