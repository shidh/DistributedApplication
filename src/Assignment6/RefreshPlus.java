package Assignment6;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

/**
 * Servlet implementation class RefreshPlus
 */
public class RefreshPlus extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RefreshPlus() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		HttpSession session = request.getSession();
		Integer currency = (Integer) session.getAttribute("currency");
		if (currency == null) {
			currency = new Integer(0);
		} else {
			currency = new Integer(currency.intValue() + 10);
		}
		session.setAttribute("currency", currency);

		PrintWriter out = response.getWriter();
		String title = "Show your currency";
		String docType = "<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.0 "
				+ "Transitional//EN\">\n";
		out.println(docType + "<HTML>\n" + "<HEAD><TITLE>" + title
				+ "</TITLE></HEAD>\n" + "<BODY>\n" + "<CENTER>\n"
				+ "<H2>Show your currency</H2>\n" 
				+ "<form>\n"
				+ "Your Currency is:\n"+ "<input type=text name=\"currency\" value="
				+ currency 
				+ " disabled>\n"
				+ "<input type=\"submit\" value=\" Submit to plus 10$ \">\n" 
				+"</form>\n"
				+"</CENTER></BODY></HTML>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doGet(request, response);
	}

}
