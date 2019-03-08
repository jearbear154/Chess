package tests.boardtests;

import chess.boards.StandardBoard;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import chess.pieces.King;

/**
 * StandardBoardTest --- program to test the setup of the standard chess board
 * @author    Jeremy McMahan
 */
public class StandardBoardTest {
    /**
     * Test setting up the standardBoard
     */
    @Test
    public void testStandardBoardConstructor() {
        StandardBoard board = new StandardBoard();
        assertEquals(true, board.getPiece(new int[]{0,4}) instanceof King);
        assertEquals(null, board.getPiece(new int[]{3,4}));
    }
}
