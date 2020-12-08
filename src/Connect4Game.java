import java.util.EmptyStackException;
import java.util.Stack;

public class Connect4Game {
    private int[][] board;
    private int numTurns;
    private boolean player1;
    private boolean gameOver;
    private Stack<Integer> rmoves;
    private Stack<Integer> cmoves;
    private int winner;

    public Connect4Game() {
        reset();
    }

    public void reset() {
        board = new int[6][7];
        numTurns = 0;
        player1 = true;
        gameOver = false;
        rmoves = new Stack<Integer>();
        cmoves = new Stack<Integer>();
        winner = -10;
    }

    public boolean getGameOver() {
        return gameOver;
    }

    public boolean playTurn(int c) {
        int rplayed = -1;
        if (c > 6) {
            return false;
        }
        for (int i = 5; i > -1; i--) {
            if (board[i][c] == 0) {
                rplayed = i;
                break;
            }
        }
        if (rplayed == -1 || gameOver) {
            return false;
        }
        if (player1) {
            board[rplayed][c] = 1;
        } else {
            board[rplayed][c] = 2;
        }
        numTurns++;
        if (checkWinner(c, rplayed) == 0) {
            player1 = !player1;
        } else {
            winner = checkWinner(c, rplayed);
        }
        rmoves.add(rplayed);
        cmoves.add(c);
        return true;

    }

    public int checkWinner(int c, int r) {
        if (HorizontalLeftWinner(c, r) || HorizontalRightWinner(c, r) || VerticalTopWinner(c, r)
                || VerticalBottomWinner(c, r) || DiagonalBottomRight(c, r) || DiagonalBottomLeft(c, r)
                || DiagonalTopLeft(c, r) || DiagonalTopRight(c, r)) {
            gameOver = true;
            if (player1) {
                return 1;
            } else {
                return 2;
            }
        }
        if (numTurns >= 42) {
            gameOver = true;
            return 3;
        } else {
            return 0;
        }
    }

    public int getWinner() {
        return winner;
    }

    public boolean HorizontalLeftWinner(int c, int r) {
        try {
            return (board[r][c] == board[r][c - 1] && board[r][c - 1] == board[r][c - 2]
                    && board[r][c - 2] == board[r][c - 3] && (board[r][c] != 0));
        } catch (ArrayIndexOutOfBoundsException e) {
            return false;
        }
    }

    public boolean HorizontalRightWinner(int c, int r) {
        try {
            return (board[r][c] == board[r][c + 1] && board[r][c + 1] == board[r][c + 2]
                    && board[r][c + 2] == board[r][c + 3] && (board[r][c] != 0));
        } catch (ArrayIndexOutOfBoundsException e) {
            return false;
        }
    }

    public boolean VerticalTopWinner(int c, int r) {
        try {
            return (board[r][c] == board[r + 1][c] && board[r + 1][c] == board[r + 2][c]
                    && board[r + 2][c] == board[r + 3][c] && (board[r][c] != 0));
        } catch (ArrayIndexOutOfBoundsException e) {
            return false;
        }
    }

    public boolean VerticalBottomWinner(int c, int r) {
        try {
            return (board[r][c] == board[r - 1][c] && board[r - 1][c] == board[r - 2][c]
                    && board[r - 2][c] == board[r - 3][c] && (board[r][c] != 0));
        } catch (ArrayIndexOutOfBoundsException e) {
            return false;
        }
    }

    public boolean DiagonalBottomRight(int c, int r) {
        try {
            return (board[r][c] == board[r + 1][c + 1] && board[r + 1][c + 1] == board[r + 2][c + 2]
                    && board[r + 2][c + 2] == board[r + 3][c + 3] && (board[r][c] != 0));
        } catch (ArrayIndexOutOfBoundsException e) {
            return false;
        }
    }

    public boolean DiagonalTopLeft(int c, int r) {
        try {
            return (board[r][c] == board[r - 1][c - 1] && board[r - 1][c - 1] == board[r - 2][c - 2]
                    && board[r - 2][c - 2] == board[r - 3][c - 3] && (board[r][c] != 0));
        } catch (ArrayIndexOutOfBoundsException e) {
            return false;
        }
    }

    public boolean DiagonalBottomLeft(int c, int r) {
        try {
            return (board[r][c] == board[r + 1][c - 1] && board[r + 1][c - 1] == board[r + 2][c - 2]
                    && board[r + 2][c - 2] == board[r + 3][c - 3] && (board[r][c] != 0));
        } catch (ArrayIndexOutOfBoundsException e) {
            return false;
        }
    }

    public boolean DiagonalTopRight(int c, int r) {
        try {
            return (board[r][c] == board[r - 1][c + 1] && board[r - 1][c + 1] == board[r - 2][c + 2]
                    && board[r - 2][c + 2] == board[r - 3][c + 3] && (board[r][c] != 0));
        } catch (ArrayIndexOutOfBoundsException e) {
            return false;
        }
    }

    public void printGameState() {
        System.out.println("\n\nTurn " + numTurns + ":\n");
        try {
            System.out.println("\n\nColumn: " + cmoves.peek() + " & Row:" + rmoves.peek());
        } catch (EmptyStackException e) {
            System.out.println("Nothing is played yet!");
        }
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                System.out.print(board[i][j]);
                if (j < 10) {
                    System.out.print(" | ");
                }
            }
            if (i < 10) {
                System.out.println("\n---------------------------");
            }
        }
    }

    public void undo() {
        if (gameOver || numTurns ==0) {
            return;
        }
        int rowToUndo = rmoves.pop();
        int ColumnToUndo = cmoves.pop();
        board[rowToUndo][ColumnToUndo] = 0;
        numTurns--;
        player1 = !player1;
    }

    public boolean getCurrentPlayer() {
        return player1;
    }
    
    public int getNumTurms() {
        return numTurns;
    }

    public int getCell(int c, int r) {
        return board[r][c];
    }

    public static void main(String[] args) {
        Connect4Game t = new Connect4Game();

        t.playTurn(0);
        t.printGameState();

        t.playTurn(1);
        t.printGameState();

        t.playTurn(1);
        t.printGameState();

        t.playTurn(2);
        t.printGameState();

        t.playTurn(3);
        t.printGameState();

        t.playTurn(2);
        t.printGameState();

        t.playTurn(2);
        t.printGameState();

        t.playTurn(3);
        t.printGameState();

        t.playTurn(4);
        t.printGameState();

        t.playTurn(3);
        t.printGameState();

        t.playTurn(3);
        t.printGameState();

        System.out.println();
        System.out.println();
        if (t.getGameOver()) {
            System.out.println("Winner is: " + t.getWinner());
        }

    }

}
