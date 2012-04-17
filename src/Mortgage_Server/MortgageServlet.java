package Mortgage_Server;

import java.io.*;
import java.lang.Double;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class MortgageServlet
 */
public class MortgageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MortgageServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    {      
    	try
    	{
			response.sendRedirect("/Mortgage_Servlet/WebContent/index.html");		
    	}
    	catch(IOException e1)
    	{
    		System.err.println("error on redirect");
			e1.printStackTrace();
		}
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    {
		double price = Double.parseDouble(request.getParameterValues("price")[0]);
		double down = Double.parseDouble(request.getParameterValues("down")[0]);
		double rate = Double.parseDouble(request.getParameterValues("rate")[0]);
		double term = Double.parseDouble(request.getParameterValues("term")[0]);
		
		double financed = price-down;//whatever wasn't down is financed
		rate = rate/100;//percent to double
		
		double monthlyPayment = Calculator.calculateMonthlyPayment(down, financed, rate, term);
		double totalPayment = Calculator.calculateTotalPayment(down, monthlyPayment, term);
		double totalInterest = Calculator.calculateTotalInterest(totalPayment, price);
		
        try
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
            System.err.print("an error has occured on POST");
    }
    }
    
    private double roundTwoPlaces(double d)
    {
    	int tempInt = (int)(d*100);
    	double tempDouble = (double)tempInt;
    	return tempDouble/100;
    }
}
