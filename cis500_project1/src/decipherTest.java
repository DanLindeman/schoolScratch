/**
 * Created by daniellindeman on 9/12/15.
 */
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.File;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.OutputStream;
import java.util.Scanner;


public class decipherTest
{
    public static void main(String[] args) throws FileNotFoundException
    {
        {  //This class does IO, deals with the user, orchestrates
           //decrypting the files of their choosing
            Scanner in = new Scanner(System.in);
            try
            {
                //Get user input
                System.out.print("Input file: ");
                String inFile = in.next();
                System.out.print("Output file: ");
                String outFile = in.next();
                System.out.print("Language Key file: ");
                String freqFile = in.next();

                //Open data sources and files to be written
                Scanner input = new Scanner(new File (inFile));
                Scanner freq = new Scanner(new File (freqFile));
                InputStream inStream = new FileInputStream(inFile);
                OutputStream outStream = new FileOutputStream(outFile);

                //Application logic
                Breaker breaker = new Breaker();
                breaker.readFreq(freq);
                breaker.calcFreq(input);
                int key = breaker.findKey();
                Cipher decipher = new Cipher(key);
                decipher.encryptStream(inStream, outStream);

                //Cleanup streams
                inStream.close();
                outStream.close();

            }
            catch (IOException exception)
            {
                System.out.println("Error processing file: " + exception);
            }
        }
    }
}
