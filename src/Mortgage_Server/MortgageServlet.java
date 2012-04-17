package Mortgage_Server;

import java.io.*;
import java.lang.Double;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class MortgageServlet
 * 
 * @author Will Wettersten
 */
public class MortgageServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MortgageServlet()
    {
        super();
    }

    /**
     * Processes get requests.  Currently redirects back to index.
     * 
     * @param request	the servlet request from the client
     * @param response	what we are goint to be sending the client
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    {      
    	try
    	{
			response.sendRedirect("/Mortgage_Servlet/WebContent/index.html");		
    	}
    	catch(IOException e)
    	{
    		System.err.println("error on redirect at:");//print a message
			e.printStackTrace();// and stack trace
		}
    }

    /**
     * Processes post requests.  Here we get several parameters from an html form and
     * calculate mortgage statists from them, serving the client an html page with the
     * statistics we calculated.
     * 
     * @param request	the servlet request from the client
     * @param response	what we are goint to be sending the client
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    {
		double price = Double.parseDouble(request.getParameterValues("price")[0]);//price in USD
		double down = Double.parseDouble(request.getParameterValues("down")[0]);//amount down in USD
		double rate = Double.parseDouble(request.getParameterValues("rate")[0]);//yearly interest rate
		double term = Double.parseDouble(request.getParameterValues("term")[0]);//term in years
		
		double financed = price-down;//whatever wasn't down is financed
		rate = rate/100;//percent to decimal
		
		double monthlyPayment = Calculator.calculateMonthlyPayment(down, financed, rate, term);
		double totalPayment = Calculator.calculateTotalPayment(down, monthlyPayment, term);
		double totalInterest = Calculator.calculateTotalInterest(totalPayment, price);
		
        try//print a simple html resposne
        { 
            PrintWriter out = response.getWriter();

            response.setContentType("text/html");
            out.println("<html>");
            out.println("<title>Mortage Calculator</title>");
            out.println("<h1>Here are your mortgage statistics:</h1>");
            out.println("<body BGCOLOR=\"#C9C9C9\">");// for that gray 90's vibe
            
            out.print("<p>Monthly payment: $" + roundTwoPlaces(monthlyPayment) + "</p>");
            out.print("<p>Total payment: $" + roundTwoPlaces(totalPayment) + "</p>");
            out.print("<p>Total interest paid: $" + roundTwoPlaces(totalInterest) + "</p>");
            
            out.println("</body>");
            out.println("</html>");
            out.close();
        }
        catch(IOException e)
    	{
    		System.err.println("error on POST at:");//print a message
			e.printStackTrace();// and stack trace
		}
    }
    
    /**
     * This function uses java int and double precision differences to round a double
     * to two places after the decimal point.
     * 
     * @param d	the number to be rounded
     * @return	the rounded value
     */
    private double roundTwoPlaces(double d)
    {
    	int tempInt = (int)(d*100);
    	double tempDouble = (double)tempInt;
    	return tempDouble/100;
    }
}
