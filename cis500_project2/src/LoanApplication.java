/**
 * Created by daniellindeman on 9/27/15.
 */
import java.util.Scanner;

public class LoanApplication {
    private Loan loan;
    private Scanner in = new Scanner(System.in);
    private PrintSpooler printer = PrintSpooler.getSpooler();
    private void run()
    {
        printer.printDocument("Loan Type [Simple, Amortized]: " );
        String type = in.nextLine().toLowerCase();
        printer.printDocument("Name: " );
        String name = in.nextLine();
        printer.printDocument("Principle: " );
        double principle = Double.parseDouble(in.nextLine());
        printer.printDocument("Length: " );
        int length = Integer.parseInt(in.nextLine());
        printer.printDocument("Rate: " );
        double rate = Double.parseDouble(in.nextLine());
        if (type.equals("simple"))
        {
            loan = new SimpleLoan(name, rate, length, principle);
        }
        else if (type.equals("amortized"))
        {
            loan = new AmortizedLoan(name, rate, length, principle);
        }
        else
        {
            printer.printDocument("Invalid Loan Type: " + type);
            run();
        }
        String summary = loan.process();
        printer.printDocument(summary);

        /*
        After processing the initial loan
        we ask the user if they wish to continue
        */
        printer.printDocument("Would you like to process another loan?");
        printer.printDocument("Type 'yes' to process another loan: ");
        String answer = in.nextLine().toLowerCase();
        if (answer.equals("yes"))
        {
            run();
        }
        else
        {
            printer.printDocument("Exiting Program...");
            System.exit(0);
        }
    }
    public static void main(String[] args)
    {
        new LoanApplication().run();
    }
}
