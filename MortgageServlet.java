import java.io.*;
import java.servlet.*;

public class MortgageServlet extends HttpServlet
{
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		out.println("<title>Mortage Calculator</title>");

		//TODO:  get input and call backend functions

		out.close();
	}
}
