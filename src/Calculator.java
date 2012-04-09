package MorgageCalculator;

Public class Calculator
{
	//TODO: EVERYTHING!!!
	public double calculateMonthlyPayment(double downAmount, double financedAmount, double interestRate, double termLength)
	{
		return 0.0;
	}

	public double calculateTotalInterest(double monthlyPayment, double termLength)
	{
		return 0.0;
	}

	public double calculateTotalInterest(double downAmount, double financedAmount, double interestRate, double termLength)
	{
		double monthlyPayment = calculateMonthlyPayment(downAmount, financedAmount, interestRate, termLength);
		return calculateTotalInterest(monthlyPayment, termLength);
	}

	public double calculateTotalPayment(double fromInterest, double fromDown)
	{
		return 0.0;
	}

	public double calculateTotalPayment(double downAmount, double financedAmount, double interestRate, double termLength)
	{
		double fromInterest = calculateTotalInterest(downAmount, financedAmount, interestRate, termLength);
		return calculateTotalPayment(fromInterest, downAmount);
	}
}
