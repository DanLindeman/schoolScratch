/**
 * Created by daniellindeman on 10/29/15.
 */

public class Tour {

    private int size;
    private int[][] board;

    public Tour (int s)
    {
        size = s;
        board = new int[size][size];
    }

    public boolean start (int x, int y)
    {
        int i, j;
        for (i = 0; i < size; i++)
            for (j = 0; j < size; j++)
                board [i][j] = 0;
        board[x][y] = 1;

        if ( is_succ(2, x, y) )
        {
            return true;
        }
        else return false;
    }

    public int[][] get_board()
    {
        return board;
    }

    public boolean is_succ (int count, int x, int y)
    {
        int[] A = { 2, 1, -1, -2, -2, -1, 1, 2 },
                B = { 1, 2, 2, 1, -1, -2, -2, -1 };

        int move = 0, new_x, new_y;
        do
        {
            move++;
            new_x = x + A[move - 1];
            new_y = y + B[move - 1];
            if ((new_x >= 0 && new_x < size) && (new_y >= 0 && new_y < size))
                if (board[new_x][new_y] == 0)
                {
                    board[new_x][new_y] = count;
                    if (count < size * size)
                    {
                        if ( ! is_succ (count + 1, new_x, new_y) )
                            board[new_x][new_y] = 0;
                        else return true;
                    }
                    else return true;
                }
        }  while ( move < 8 );
        return false;
    }

    public void reset_tour()
    {
        for (int i = 0; i < size; i++)
            for (int j = 0; j < size; j++)
                board [i][j] = 0;
    }
}
