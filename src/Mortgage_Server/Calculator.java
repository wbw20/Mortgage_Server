package Mortgage_Server;

public class Calculator
{
    //TODO: comments
    final static int NUMMONTHS = 12;

    public static double calculateMonthlyPayment(double downAmount, double financedAmount, double yearlyInterestRate, double termLength)
    {
            double monthlyInterestRate = yearlyInterestRate/NUMMONTHS;
            double numMonthlyPayments = termLength*NUMMONTHS;

            return financedAmount*(monthlyInterestRate)/(1 - Math.pow((1 + monthlyInterestRate), -numMonthlyPayments));
    }

    public static double calculateTotalPayment(double downAmount, double monthlyPayment, double termLength)
    {
            double numMonthlyPayments = termLength*NUMMONTHS;

            return monthlyPayment*numMonthlyPayments + downAmount;
    }

    public static double calculateTotalPayment(double downAmount, double financedAmount, double interestRate, double termLength)
    {
            double monthlyPayment = calculateMonthlyPayment(downAmount, financedAmount, interestRate, termLength);
            return calculateTotalPayment(downAmount, monthlyPayment, termLength);
    }

    public static double calculateTotalInterest(double totalPayment, double fromDown)
    {
            return totalPayment - fromDown;
    }

    public static double calculateTotalInterest(double downAmount, double financedAmount, double interestRate, double termLength)
    {
            double totalPayment = calculateTotalPayment(downAmount, financedAmount, interestRate, termLength);

            return calculateTotalInterest(totalPayment, downAmount);
    }
}