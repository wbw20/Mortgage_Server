import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class MortgageServlet extends HttpServlet
{
	public void doGet(HttpServletRequest request, HttpServletResponse response)
	{
		try
		{
			PrintWriter out = response.getWriter();

			out.println("hello world!");
			out.close();
		}
		catch(IOException e)
		{
			System.err.print("an error has occured on GET");
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
	{
		try
		{
			PrintWriter out = response.getWriter();

			out.println("<title>Mortage Calculator</title>");
			out.close();
		}
		catch(IOException e)
		{
			System.err.print("an error has occured on POST");
		}
	}
}
