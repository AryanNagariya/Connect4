import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.*;

/**
 * This class instantiates a TicTacToe object, which is the model for the game.
 * As the user clicks the game board, the model is updated. Whenever the model
 * is updated, the game board repaints itself and updates its status JLabel to
 * reflect the current state of the model.
 * 
 * This game adheres to a Model-View-Controller design framework. This framework
 * is very effective for turn-based games. We STRONGLY recommend you review
 * these lecture slides, starting at slide 8, for more details on
 * Model-View-Controller:
 * https://www.seas.upenn.edu/~cis120/current/files/slides/lec37.pdf
 * 
 * In a Model-View-Controller framework, GameBoard stores the model as a field
 * and acts as both the controller (with a MouseListener) and the view (with its
 * paintComponent method and the status JLabel).
 */
@SuppressWarnings("serial")
public class GameBoard extends JPanel {

    private Connect4Game ttt; // model for the game
    private JLabel status; // current status text
    private String[] highScoreUser = new String[3]; // Stores Top 3 user's names
    private int[] highScoreTurns = new int[3]; // Stores their respective scores
    private String currPlayerName; // stores the current player's user name

    // Game constants
    public static final int BOARD_WIDTH = 800;
    public static final int BOARD_HEIGHT = 800;

    /**
     * Initializes the game board.
     */
    public GameBoard(JLabel statusInit) {
        // creates border around the court area, JComponent method
        setBorder(BorderFactory.createLineBorder(Color.BLACK));

        setFocusable(true);

        ttt = new Connect4Game(); // initializes model for the game
        status = statusInit; // initializes the status JLabel

        /*
         * Listens for mouseclicks. Updates the model, then updates the game board based
         * off of the updated model.
         */
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Point p = e.getPoint();

                // updates the model given the coordinates of the mouseclick
                ttt.playTurn(p.x / 100);

                updateStatus(); // updates the status JLabel
                repaint(); // repaints the game board
            }
        });
    }

    /**
     * (Re-)sets the game to its initial state.
     */
    public void reset() {
        ttt.reset();
        status.setText("Player 1's Turn");
        repaint();

        // Makes sure this component has keyboard/mouse focus
        requestFocusInWindow();
    }
    
    public void undo() {
        ttt.undo();
        updateStatus();
        repaint();

        // Makes sure this component has keyboard/mouse focus
        requestFocusInWindow();
    }
    
    
    //Display's how to play the game
    public void howtoplay() {
        
        String toshow = "The game of Connect 4 is won by placing 4 checkers in a row. \n" + 
                "The two players alternate their turns. \n"
                + "Use the mouse to select the column you to wish to "
                + "play your checker in." ;
            
        JOptionPane.showMessageDialog(null, toshow);
        repaint();
    }
    
    // Creates a temp values in file and figures out
    // what to high scores to display
    public void boardUsername(String name) {
        try {
            if (highScoreUser[0] == null) {
                File storeHighScores = new File("highscores.txt");
                if (!storeHighScores.exists()) {
                    storeHighScores.createNewFile();
                }
                BufferedWriter bwfile = new BufferedWriter(new FileWriter(storeHighScores));
                bwfile.write("Nouser|43");
                bwfile.newLine();
                bwfile.write("Nouser|43");
                bwfile.newLine();
                bwfile.write("Nouser|43");
                bwfile.close();
                readFromFile();
            }
        } catch (IOException e) {
            return;
        }
        if (ttt.getGameOver()) {
            int currentUserScore = ttt.getNumTurms();
            String[] cloneuser = new String [3];
            int[] clonescore = new int [3];
            for (int i = 0; i < 3; i++) {
                cloneuser[i] = highScoreUser[i];
                clonescore[i] = highScoreTurns[i];
            }
            if (clonescore[0] > currentUserScore) {
                highScoreUser[0] = name;
                highScoreUser[1] = cloneuser[0];
                highScoreUser[2] = cloneuser[1];
                highScoreTurns[0] = currentUserScore;
                highScoreTurns[1] = clonescore[0];
                highScoreTurns[2] = clonescore[1];
            } else if (clonescore[1] > currentUserScore) {
                highScoreUser[1] = name;
                highScoreUser[2] = cloneuser[1];
                highScoreTurns[1] = currentUserScore;
                highScoreTurns[2] = clonescore[1];
            } else if (clonescore[2] > currentUserScore) {
                highScoreUser[2] = name;
                highScoreTurns[2] = currentUserScore;
            } else {
                clonescore[1] = clonescore[1];
            }
            writeToFile();
        }
    }
    
    
    // Writes to high scores file and updates it
    public void writeToFile() {
        BufferedWriter bw = null ;
        try {
            bw = new BufferedWriter(new FileWriter("highscores.txt", false));
            String[] stringsToWrite = 
                {
                        highScoreUser[0] + "|" +  highScoreTurns[0],
                        highScoreUser[1] + "|" +  highScoreTurns[1],
                        highScoreUser[2] + "|" +  highScoreTurns[2]
                };
            for (String s: stringsToWrite) {
                bw.write(s);
                bw.newLine();
            }
            bw.close();
        } catch (IOException e) {
            return;
        }
    }
    
    // passes in the current player's name from their input box
    public void storeUser(String k) {
        currPlayerName = k;
    }
    
    // Initializes the file
    public boolean firstSetpUp() {
        boolean answer;
        try {
            BufferedReader brfile = new BufferedReader(new FileReader("highscores.txt"));
            String theline = brfile.readLine();
            String[] storage = theline.split("\\|");
            if (!storage[0].equals("Nouser0")) {
                answer = true;
            } else {
                answer = false;
            }
            brfile.close();
        } catch (IOException e) {
            return false;
        }
        return answer;
    }
    
    //Reads from the file and updates local array
    public void readFromFile() {
        try {
            BufferedReader brfile = new BufferedReader(new FileReader("highscores.txt"));
            for (int i = 0; i < 3; i++) {
                String theline = brfile.readLine();
                String[] storage = theline.split("\\|");
                highScoreUser[i] = storage[0];
                highScoreTurns[i] = Integer.parseInt(storage[1]);
            }
            brfile.close();
        } catch (IOException e) {
            return;
        }
    }
    
    // gives the name of the current user to file writer
    public void updateHighScore(String name) {
        if (firstSetpUp()) {
            readFromFile();
            
        }
        boardUsername(name);
    }
    
    //displays the current high scores
    public void showHighScore() {
        String[] tempuser = new String [3];
        int[] tempscore = new int [3];
        for (int i = 0; i < 3; i++) {
            tempuser[i] = highScoreUser[i];
            tempscore[i] = highScoreTurns[i];
        }
        if (tempuser[0].equals("Nouser")) {
            tempuser[0] = "nobody";
            tempscore[0] = 0;
        }
        if (tempuser[1].equals("Nouser")) {
            tempuser[1] = "nobody";
            tempscore[1] = 0;
        }
        if (tempuser[2].equals("Nouser")) {
            tempuser[2] = "nobody";
            tempscore[2] = 0;
        }
        String todisplay;
        todisplay = "First position is held by " + tempuser[0] + " with a score of " + 
                tempscore[0]
                + "\n" + "Second Position is held by " + tempuser[1] + " with a score of " 
                + tempscore[1]
                + "\n" + "Third Position is held by " + tempuser[2] + " with a score of "
                + tempscore[2];
        JOptionPane.showMessageDialog(null, todisplay);
    }

    /**
     * Updates the JLabel to reflect the current state of the game.
     */
    private void updateStatus() {
        if (ttt.getCurrentPlayer()) {
            status.setText("Player 1's Turn");
        } else {
            status.setText("Player 2's Turn");
        }

        int winner = ttt.getWinner();
        if (winner == 1) {
            status.setText("Player 1 wins!!!");
            updateHighScore(currPlayerName);
        } else if (winner == 2) {
            status.setText("Player 2 wins!!!");
            updateHighScore(currPlayerName);
        } else if (winner == 3) {
            status.setText("It's a tie.");
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Draws board grid
        g.drawLine(100, 0, 100, 600);
        g.drawLine(200, 0, 200, 600);
        g.drawLine(300, 0, 300, 600);
        g.drawLine(400, 0, 400, 600);
        g.drawLine(500, 0, 500, 600);
        g.drawLine(600, 0, 600, 600);
        g.drawLine(700, 0, 700, 600);
        g.drawLine(0, 100, 700, 100);
        g.drawLine(0, 200, 700, 200);
        g.drawLine(0, 300, 700, 300);
        g.drawLine(0, 400, 700, 400);
        g.drawLine(0, 500, 700, 500);
        g.drawLine(0, 600, 700, 600);
        
        g.drawString("Total Moves : " + ttt.getNumTurms(), 350, 650);
        

        // Draws the checkers
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                int state = ttt.getCell(j, i);
                if (state == 1) {
                    g.setColor(Color.RED);
                    g.drawOval(30 + 100 * j, 30 + 100 * i, 50, 50);
                    g.fillOval(30 + 100 * j, 30 + 100 * i, 50, 50);
                } else if (state == 2) {
                    g.setColor(Color.BLUE);
                    g.drawOval(30 + 100 * j, 30 + 100 * i, 50, 50);
                    g.fillOval(30 + 100 * j, 30 + 100 * i, 50, 50);
                }
            }
        }
    }

    /**
     * Returns the size of the game board.
     */
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(BOARD_WIDTH, BOARD_HEIGHT);
    }
}