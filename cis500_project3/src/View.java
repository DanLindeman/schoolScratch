/**
 * Created by daniellindeman on 11/1/15.
 */
import javax.swing.*;
import javax.swing.Icon;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class View extends JPanel
{
    private JButton[][] board;
    private JPanel center, input_panel;
    private Icon knight_icon = new ImageIcon("src/knight.png");
    private JButton find_solution_button, reset_button, print_tour_button, step_through_button;
    private int rows, columns;
    private Tour tour;
    private boolean successful_tour;
    private int step = 1;
    private int[][] tour_board;

    public View(int rows, int columns, Tour tour)
    {
        this.rows = rows;
        this.columns = columns;
        this.tour = tour;
        this.tour_board = tour.get_board();

        center = new JPanel();
        input_panel = new JPanel();

        setPreferredSize(new Dimension(800, 800));
        setLayout(new BorderLayout());
        add(center, BorderLayout.CENTER);
        add(input_panel, BorderLayout.SOUTH);

        ButtonHandler handler = new ButtonHandler();

        find_solution_button = new JButton("Find Solution");
        input_panel.add(find_solution_button);
        find_solution_button.addActionListener(handler);

        print_tour_button = new JButton("Print Tour");
        input_panel.add(print_tour_button);
        print_tour_button.addActionListener(handler);
        print_tour_button.setEnabled(false);

        step_through_button = new JButton("Step Through");
        input_panel.add(step_through_button);
        step_through_button.addActionListener(handler);

        reset_button = new JButton("Reset");
        input_panel.add(reset_button);
        reset_button.addActionListener(handler);

        center.setLayout(new GridLayout(rows, columns));
        board = new JButton[rows][columns];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                board[i][j] = new JButton();
                board[i][j].addActionListener(handler);
                center.add(board[i][j]);
            }
        }
    }

    private void display_tour()
    {
        clear_screen();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                String text = Integer.toString(tour_board[i][j]);
                board[i][j].setText(text);
                if (tour_board[i][j] == 1)
                {
                    board[i][j].setText("");
                    board[i][j].setIcon(knight_icon);
                }
            }
        }
    }

    private void clear_screen()
    {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                board[i][j].setIcon(null);
                board[i][j].setText("");
            }
        }
    }

    private void reset()
    {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                board[i][j].setIcon(null);
                board[i][j].setText("");
                tour.reset_tour();
            }
        }
        successful_tour = false;
        step = 1;
        print_tour_button.setEnabled(false);
    }

    private void print_tour()
    {
        if (tour_board[0][0] == 0)  // All board entries will be 0 if no solution exists
            System.out.println("No Solution");
        else {
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < columns; j++) {
                    System.out.print("\t" + tour_board[i][j]);
                }
                System.out.println("");
            }
        }
    }

    private void step_through()
    {
        clear_screen();
        if (tour_board[0][0] != 0)  //If a solution exists, all board entries will be non-zero
        {
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < columns; j++) {
                    if (tour_board[i][j] <= step)
                    {
                        board[i][j].setIcon(null);
                        board[i][j].setText(Integer.toString(tour_board[i][j]));
                    }
                }
            }
            step += 1;
        }
    }

    private void find_solution()
    {
        step = 1;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                //  Because I set the icon to the knight icon earlier
                //  I use it to decide which button was selected.
                if (board[i][j].getIcon() == knight_icon)
                {
                    successful_tour = tour.start(i, j);
                }
            }
        }
        if (successful_tour)
        {
            display_tour();
            print_tour_button.setEnabled(true);
        }
        else
        {
            JOptionPane.showMessageDialog(center, "No Solution");
        }

    }

    private class ButtonHandler implements ActionListener
    {
        public void actionPerformed(ActionEvent event)
        {
            if (event.getSource() == reset_button)
                reset();
            if (event.getSource() == print_tour_button)
                print_tour();
            if (event.getSource() == step_through_button)
                step_through();
            if (event.getSource() == find_solution_button)
                find_solution();
            else
            {
                for (int i = 0; i < rows; i++) {
                    for (int j = 0; j < columns; j++) {
                        if (board[i][j] == event.getSource())
                        {
                            clear_screen();
                            step = 1;
                            //  I use setting an Icon because I wanted some graphical flourish
                            //  I could have used java.awt.geom.Point for this instead by setting
                            //  some private point's location to x, and y and checking it later
                            //  during find_solution()...but this methods works as well.
                            board[i][j].setIcon(knight_icon);
                        }
                    }
                }
            }
        }
    }
}
