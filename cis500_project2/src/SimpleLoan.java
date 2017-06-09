/**
 * Created by daniellindeman on 10/18/15.
 */
public class SimpleLoan extends Loan
{
    public SimpleLoan(String name, double rate, int years, double amount)
    {
        super(name, rate, years, amount);
    }
    public void calcMonthlyPayment ()
    {
        int lengthInMonths = length * 12;
        monthlyPayment = (principle * ((interestRate + 1)))/lengthInMonths;

    }
    public String toString()
    {
        return "Simple Interest Loan";
    }
}