package Assignment8B;

import java.io.*;
import java.util.Date;
import javax.servlet.*;
import javax.servlet.http.*;
import org.json.GetJson;


public class GetTimeandWeather extends HttpServlet {

	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GetTimeandWeather() {
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
		String URL = "http://free.worldweatheronline.com/feed/" +
				"weather.ashx?q=48.006897,11.344560&format=json&num_of_days=1&key=3dd40f9add041907121910";
				
		String json = GetJson.getJson(URL);
		String weather = GetJson.parseWeather(json);
		PrintWriter out = response.getWriter();
		out.println("Current Time is:\n" 
				   +date.toString()
				   +"</br>"
				   +weather);
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
