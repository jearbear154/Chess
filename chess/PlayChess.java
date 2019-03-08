package chess;

import chess.pieces.Piece;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * PlayChess --- class that simulates a round of chess games graphically
 * The game works by having the user click dialogue boxes to choose who goes first and the desired type of chess board
 * Then, by clicking on a square and another, the game will move the piece from the first clicked square to the second
 * clicked square. Once a player wins or a stale mate occurs, the user is notified and the game resets with the scores
 * updated. The users then may again choose who goes first and the board type. The game is complete once the users
 * decide to exit the window.
 * @author    Jeremy McMahan
 */
public class PlayChess {
    private static BoardLayout.Square fromButton; //The square that we are moving a piece from
    private static BoardLayout.Square toButton; //The square that we are moving a piece to
    private static ChessGameGUI gui; //The GUI for the board
    private static Chess game; //The current chess game

    /**
     * SquareController --- class to receive the squares the user clicks on and update the board accordingly
     */
    private static class SquareController implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (fromButton == null || toButton != null) { //we have not clicked a new piece yet
                fromButton = (BoardLayout.Square) e.getSource();
                toButton = null; //signal waiting for the square to move to
            } else {
                toButton = ((BoardLayout.Square) e.getSource());
                if (game.movePiece(fromButton.position, toButton.position)) {
                    updateBoard();
                } else {
                    gui.invalidMove();
                }
            }
        }

        /**
         * Updates the GUI to show the move just made
         */
        private void updateBoard() {
            toButton.setText(fromButton.getText()); //move the piece icon to the new square
            fromButton.setText("");
            gui.syncBoard();
            if (game.isCheckMate()) {
                game.swapTurn(); //current player in check mate so other player won
                gui.gameWon(game.getPlayer());
                reset();
            } else if (game.isStaleMate()) {
                gui.staleMate();
                reset();
            } else if (game.isCheck()) {
                gui.check();
            }
        }
    }

    /**
     * OptionsController --- class to control the user actions: reset, undo, forfeit
     */
    private static class OptionsController implements  ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getActionCommand().equals("Undo")) {
                game.undoLastMove();
                Piece resurrected = game.getBoard().getPiece(toButton.position);
                fromButton.setText(toButton.getText()); //put the moved piece's icon back
                toButton.setText((resurrected != null)? resurrected.toString() : "");
                gui.syncBoard();
            } else if (e.getActionCommand().equals("Reset")){
                reset();
            } else { //Forfeit
                game.swapTurn(); //current player forfeited so other player won
                gui.gameWon(game.getPlayer());
                reset();
            }
        }
    }

    /**
     * Sets up the GUI and starts the chess game
     * @param args
     */
    public static void main(String[] args) {
        fromButton = null;
        toButton = null;
        gui = new ChessGameGUI(new OptionsController());
        reset();
    }

    /**
     * Resets the board and updates the GUI
     */
    private static void reset() {
        game = new Chess(gui.getPlayerStart(), gui.getBoardType());
        gui.setUpBoard(game.getBoard(), new SquareController());
    }

}
