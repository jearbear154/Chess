package chess;

import chess.boards.ChessBoard;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * ChessGameGUI --- class that represents a chess game graphically
 * @author    Jeremy McMahan
 */
public class ChessGameGUI {
    private BoardLayout boardLayout; //the graphical representation of the chess board
    private JFrame frame; //The window of the GUI
    private JPanel gameOptions; //contains the user control options such as reset and undo
    private JLabel scoreW; //shows the score of player W
    private JLabel scoreB; //shows the score of player B

    /**
     * Sets up the basic layout for the chessboard game other than the board itself
     * @param options an action listener that handles the clicking of a button in the option menu bar
     */
    public ChessGameGUI(ActionListener options) {
        initializeFrame();
        initializeOptions(options);
        frame.getContentPane().add(gameOptions, BorderLayout.NORTH);
    }

    /**
     * Sets up the graphical representation of the chess board and adds it to the frame
     * @param board the chess board we are modeling after
     * @param squares the action listener to handle the clicking of a square
     */
    public void setUpBoard(ChessBoard board, ActionListener squares) {
        if (boardLayout != null) { //If we already have a board being shown overwrite it
            frame.remove(boardLayout);
        }
        boardLayout = new BoardLayout(board, squares);
        frame.getContentPane().add(boardLayout, BorderLayout.CENTER);
        frame.validate();
    }

    /**
     * Opens a dialogue box that lets the user choose the desired chess board type
     * @return String the representation of the board type that was chosen
     */
    public String getBoardType() {
        return (JOptionPane.showOptionDialog(frame, "What board type would you like to use?",
                "Board Options", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null,
                new String[] {"Standard", "Custom"}, "Standard")
                == JOptionPane.YES_OPTION)? "Standard" : "Custom";
    }

    /**
     * Opens a dialogue box that lets the user choose which color goes first
     * @return String the representation of the color chosen
     */
    public String getPlayerStart() {
        return (JOptionPane.showOptionDialog(frame, "Which player goes first?", "Player Options",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, new String[]{"W","B"},
                "W") == JOptionPane.YES_OPTION)? "W" : "B";
    }

    /**
     * Opens a dialogue box alerting the user that player has won and updates the score
     * @param player the color of the winning player
     */
    public void gameWon(chess.Color player) {
        JOptionPane.showMessageDialog(frame, "Player " + player + " wins!", "GAME OVER",
                JOptionPane.INFORMATION_MESSAGE);

        if (player == chess.Color.W) { //adds one to the score of player
            scoreW.setText("Score W: " + (Integer.parseInt(scoreW.getText().substring(9)) + 1));
        } else {
            scoreB.setText("Score B: " + (Integer.parseInt(scoreB.getText().substring(9)) + 1));
        }

        gameOptions.repaint();
    }

    /**
     * Opens a dialogue box alerting the user that a stale mate has occurred
     */
    public void staleMate() {
        JOptionPane.showMessageDialog(frame, "Stale Mate", "GAME OVER", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Opens a dialogue box alerting the player that he is in check
     */
    public void check() { JOptionPane.showMessageDialog(frame, "Check!"); }

    /**
     * Opens a dialogue box alerting the user that the move was invalid
     */
    public void invalidMove() { JOptionPane.showMessageDialog(frame, "Invalid Move"); }

    /**
     * Refreshes the graphical representation board to be used when a change occurs
     */
    public void syncBoard() { boardLayout.repaint(); }

    /**
     * Creates the JFrame with all the desired window settings
     */
    private void initializeFrame() {
        //the length of a side of the JFrame, chosen because it looks best to me
        int frameSize = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight()*9/10;
        frame = new JFrame();
        frame.setSize(new Dimension(frameSize,frameSize));
        frame.setTitle("Chess");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    /**
     * Creates the menu bar that allows the user to reset, undo, forfeit, and view their score
     * @param options an action listener to handle clicking of the menu bar
     */
    private void initializeOptions(ActionListener options) {
        gameOptions = new JPanel((new GridLayout(1,4)));
        JButton reset = new JButton("Reset");
        JButton undo = new JButton("Undo");
        JButton forfeit = new JButton("Forfeit");
        scoreW = new JLabel("Score W: 0");
        scoreB = new JLabel("Score B: 0");
        for (JButton button : new JButton[]{reset, undo, forfeit}) {
            button.addActionListener(options);
            gameOptions.add(button);
        }
        gameOptions.add(scoreW);
        gameOptions.add(scoreB);
    }
}
