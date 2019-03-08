package tests;

import chess.ChessGameGUI;
import chess.Color;
import org.junit.Test;

/**
 * ChessGameGUITest --- class that tests the dialogue box functionality of the GUI
 * @author    Jeremy McMahan
 */
public class ChessGameGUITest {
    @Test
    public void testDialogue() {
        ChessGameGUI gui = new ChessGameGUI(null);
        gui.check();
        gui.gameWon(Color.W);
        gui.gameWon(Color.B);
        gui.invalidMove();
        gui.staleMate();
    }
}
