package Mortgage_Server;

/**
 * This class contains utilities for calculating mortgage payments, specifically monthly payments,
 * total payments, and total payments from interest.
 * 
 * @author Will Wettersten
 */
public class Calculator
{
	//months in Gregorian Calendar
    final static int NUMMONTHS = 12;

    /**
     * This function calculates monthly payments for a given mortgage
     * 
     * @param downAmount			the amount down (not financed)
     * @param financedAmount		the amount financed (not down)
     * @param yearlyInterestRate	the interest rate in percent per year
     * @param termLength			the length of the term in years
     * 
     * @return the monthly payments to pay off the mortgage over the given term
     */
    public static double calculateMonthlyPayment(double downAmount, double financedAmount, double yearlyInterestRate, double termLength)
    {
            double monthlyInterestRate = yearlyInterestRate/NUMMONTHS;//length of mortgage in months
            double numMonthlyPayments = termLength*NUMMONTHS;//number of payments over the ecourse of the mortgage

            //the canonical formula for calculating monthly payments 
            return financedAmount*(monthlyInterestRate)/(1 - Math.pow((1 + monthlyInterestRate), -numMonthlyPayments));
    }

    /**
     * This function calculates the total amount payed over the course of the mortgage
     *  
     * @param downAmount			the amount down (not financed)
     * @param monthlyPayment		the monthly payment as calculated in calculateMonthlyPayment()
     * @param termLength			the length of the term in years
     * 
     * @return 						the total amount paid over the course of the mortgage
     */
    public static double calculateTotalPayment(double downAmount, double monthlyPayment, double termLength)
    {
            double numMonthlyPayments = termLength*NUMMONTHS;//number of payments over the ecourse of the mortgage

            return monthlyPayment*numMonthlyPayments + downAmount;//the sum of the amount down and the amount paid 
            													  //in monthly payments
    }

    /**
     * This function calculates the total amount payed over the course of the mortgage
     * 
     * @param downAmount			the amount down (not financed)
     * @param financedAmount		the amount financed (not down)
     * @param yearlyInterestRate	the interest rate in percent per year
     * @param termLength			the length of the term in years
     * 
     * @return 						the total amount paid over the course of the mortgage
     */
    public static double calculateTotalPayment(double downAmount, double financedAmount, double yearlyInterestRate, double termLength)
    {
    		//the amount paid each month
            double monthlyPayment = calculateMonthlyPayment(downAmount, financedAmount, yearlyInterestRate, termLength);
            //total paid over the course of the mortgage
            return calculateTotalPayment(downAmount, monthlyPayment, termLength);
    }

    /**
     * This function calculates the total interest paid over the course of the mortgage
     * 
     * @param totalPayment	the total amount paid over the course of the mortgage
     * @param price			the original price of the home
     * 
     * @return 				the total interest paid over the course of the mortgage
     */
    public static double calculateTotalInterest(double totalPayment, double price)
    {
            return totalPayment - price;//interest is the total - the original price
    }

    /**
     * 
     * @param downAmount			the amount down (not financed)
     * @param financedAmount		the amount financed (not down)
     * @param yearlyInterestRate	the interest rate in percent per year
     * @param termLength			the length of the term in years
     * 
     * @return 						the total interest paid over the course of the mortgage
     */
    public static double calculateTotalInterest(double downAmount, double financedAmount, double yearlyInterestRate, double termLength)
    {
    		//the total amount paid over the course of the mortgage
            double totalPayment = calculateTotalPayment(downAmount, financedAmount, yearlyInterestRate, termLength);
            //the total interest paid over the course of the mortgage
            return calculateTotalInterest(totalPayment, downAmount);
    }
}