package Assignment8A;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import org.json.GetJson;

public class Local extends HttpServlet {

	private static final long serialVersionUID = 1L;
	boolean globalStatus = false;
	boolean localStatus = false;
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Local() {
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

		String responseStr = "";
		String location = "Munich";   //Maybe dynamically detected by IP address in the future
		String URL = "http://free.worldweatheronline.com/feed/"
				+ "weather.ashx?q=48.006897,11.344560&format=json&num_of_days=1&key=3dd40f9add041907121910";
		String json = GetJson.getJson(URL);
		String temp = GetJson.parseTemp(json);

		if (globalStatus == false && localStatus == false) {
			responseStr = "Your Place is:\n" + location + "</br>"
					+ temp;
			localStatus = true;

		} else if (globalStatus == true && localStatus == false) {
			globalStatus = false;
			responseStr = "Tour Place is:\n" + location + "</br>"
					+ temp;
			localStatus = true;

		} else {
			responseStr = "";
			localStatus = false;
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
