/**
 * Created by daniellindeman on 10/29/15.
 */
import javax.swing.*;

class TourTest {

    public static void main (String[] args)
    {
        final int SIZE = 5;		// size of the chess board
        Tour tour = new Tour(SIZE);
        View view = new View(SIZE, SIZE, tour);
        JFrame frame = new JFrame("Knight's Tour");
        frame.getContentPane().add(view);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}