package MorgageCalculator;

Public class Calculator
{
	//TODO: comments
	final int NUMMONTHS = 12;

	public double calculateMonthlyPayment(double downAmount, double financedAmount, double yearlyInterestRate, double termLength)
	{
		double monthlyInterestRate = yearlyInterestRate/NUMMONTHS;
		double numMonthlyPayments = termLength*NUMMONTHS;

		return financedAmount*(monthlyInterestRate)/(1 - Math.pow((1 + monthlyInterestRate), -numMonthlyPayments));
	}

	public double calculateTotalPayment(double downAmount, double monthlyPayment, double termLength)
	{
		double numMonthlyPayments = termLength*NUMMONTHS;

		return monthlyPayment*numMonthlyPayments + downAmount;
	}

	public double calculateTotalPayment(double downAmount, double financedAmount, double interestRate, double termLength)
	{
		double monthlyPayment = calculateMonthlyPayment(downAmount, financedAmount, interestRate, termLength);
		return calculateTotalInterest(downAmount, monthlyPayment, termLength);
	}

	public double calculateTotalInterest(double totalPayment, double fromDown)
	{
		return totalPayment - fromInterest;
	}

	public double calculateTotalInterest(double downAmount, double financedAmount, double interestRate, double termLength)
	{
		double totalPayment = calculateTotalPayment(downAmount, financedAmount, interestRate, termLength);

		return calculateTotalInterest(totalPayment, downAmount);
	}
}
