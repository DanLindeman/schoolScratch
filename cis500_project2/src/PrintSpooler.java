/**
 * Created by daniellindeman on 9/27/15.
 */
public class PrintSpooler {
    private static PrintSpooler spooler;
    public static PrintSpooler getSpooler()
    {
        if (spooler == null) {
            spooler = new PrintSpooler();
        }
        return spooler;
    }
    public void printDocument (String s)
    {
        System.out.println(s);
    }
}
