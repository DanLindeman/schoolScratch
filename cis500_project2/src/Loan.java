/**
 * Created by daniellindeman on 9/27/15.
 */
public abstract class Loan
{
    protected String name;                             // the applicant’s name
    protected double interestRate;                     // the annual interest rate
    protected int length;                              // the  length of the loan in years
    protected double principle;                        // the principle
    protected double monthlyPayment;                   // the monthly payment

    public Loan (String name, double rate, int years, double amount)
    {
        this.name = name;
        this.interestRate = rate / 100; // convert percent to decimal
        this.length = years;
        this.principle = amount;
    }
    public String process ()
    {
        calcMonthlyPayment();
        return makeSummary();
    }
    abstract public void calcMonthlyPayment ();
    public String makeSummary()
    {
        return               this.toString() + "\n" +
        "Name: " +           this.name + "\n" +
        "Principle: $ " +    this.principle + "\n" +
        "Interest Rate: " + (this.interestRate * 100) + " %" + "\n" +
        "Length of Loan: " + this.length + " years" + "\n" +
        "Payment: $ " +      this.monthlyPayment;
    }
    public String toString() { return "Loan"; }
}



