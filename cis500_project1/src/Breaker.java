/**
 * Created by daniellindeman on 9/10/15.
 */
import java.io.IOException;
import java.util.Scanner;

public class Breaker {
    private double [] given = new double [26]; //the frequencies of the given language (ex: English)
    private double [] found = new double [26]; //has the letter frequency for letters in a read file

    public void readFreq(Scanner frequencyFile) throws IOException
    {
        //Given a frequency file with lines of the format "A 0.015"
        int i = 0;
        while (frequencyFile.hasNextLine())
        {
            String line = frequencyFile.nextLine();
            Double data = Double.parseDouble(line.split(" ")[1]);
            this.given[i] = data;
            i++;
        }
    }
    public void calcFreq(Scanner inputData) throws IOException
    {
        //For each character, add one to the 'found' array at the appropriate location
        int totalChars = 0;
        while (inputData.hasNext())
        {
            String next = inputData.next();
            char[] word = next.toCharArray();
            for (char letter: word)
            {
                if (Character.isUpperCase(letter))
                {
                    found[letter - 'A']++;//results in a number 0-25 for capitals
                }
                if (Character.isLowerCase(letter))
                {
                    found[letter - 'a']++;//results in a number 0-25 for lowers
                }
                totalChars ++;//increment the total number of characters regardless
            }
        }
        for (int i=0; i < found.length; i++)
        {
            //Instead of the total 'finds'
            // we actually want their frequency out of the total
            found[i] = found[i]/totalChars;
        }
    }

    public int findKey()//Returns a negative integer to be used to decipher text
    {
        double [] square_differences = new double [26];
        for (int rotation = 0; rotation < 26; rotation++)
        {
            double sum_of_square_differences = 0;
            for (int i = 0; i < 26; i++)
            {
                //Sum all of the square differences (between given and found frequencies) for each letter
                double square_difference = Math.pow(given[i] - found[(i + rotation)%26], 2);
                sum_of_square_differences += square_difference;
            }
            square_differences[rotation] = sum_of_square_differences;
        }
        double least_difference = square_differences[0];
        int least_index = 0;
        for (int i = 0; i < 26; i++)
        {
            if (square_differences[i] < least_difference)
            {
                //Sets the least difference only if it is smaller than our current
                least_index = i;
                least_difference = square_differences[i];
            }
        }
        //This is done so we can use the pre-made Cipher class's "rotate backward" functionality
        return -1 * least_index;
    }
}