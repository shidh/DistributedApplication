package Assignment8A;

import java.io.*;
import java.util.Date;
import javax.servlet.*;
import javax.servlet.http.*;
import org.json.GetJson;


public class Golobal extends HttpServlet {

	private static final long serialVersionUID = 1L;
	boolean globalStatus = false;
	boolean localStatus = false;
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Golobal() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Date date = new Date();
		String responseStr = "";
		String URL = "http://free.worldweatheronline.com/feed/"
				+ "weather.ashx?q=48.006897,11.344560&format=json&num_of_days=1&key=3dd40f9add041907121910";
		String json = GetJson.getJson(URL);
		String weather = GetJson.parseWeather(json);

		if (globalStatus == false && localStatus == false) {
			responseStr = "Current Time is:\n" + date.toString() + "</br>"
					+ weather;
			globalStatus = true;

		} else if (globalStatus == false && localStatus == true) {
			localStatus = false;
			responseStr = "Current Time is:\n" + date.toString() + "</br>"
					+ weather;
			globalStatus = true;

		} else {
			responseStr = "";
			globalStatus = false;
		}

		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		out.println("<div class=\"a\" align=\"center\">"
				+ "<form action=\"Golobal\" method=\"get\">"
				+ "<input type=\"submit\" value=\"Global\" />"
				+ "</form>" 
				+ "<form action=\"Local\" method=\"get\">"
				+ "<input type=\"submit\" value=\"Local\" />" + "</form>"
				+ "</div>" 
				+ "<div id=\"content\"  align=\"center\" >"
				+ responseStr 
				+ "</div>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doGet(request, response);
	}
}
