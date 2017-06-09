/**
 * Created by daniellindeman on 10/18/15.
 */
public class AmortizedLoan extends Loan
{
    public AmortizedLoan(String name, double rate, int years, double amount)
    {
        super(name, rate, years, amount);
    }
    public void calcMonthlyPayment ()
    {
        int lengthInMonths = length * 12;
        double monthlyRate = interestRate / 12;
        double n = Math.pow((1 + monthlyRate),(lengthInMonths));
        monthlyPayment = principle * ((monthlyRate * n)/(n - 1));
    }
    public String toString()
    {
        return "Full Amortized Loan";
    }
}
