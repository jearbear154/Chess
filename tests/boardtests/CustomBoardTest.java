package tests.boardtests;

import chess.boards.CustomBoard;
import chess.pieces.UltraKnight;
import chess.pieces.UltraRook;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CustomBoardTest {
    @Test
    public void testCustomBoard() {
        CustomBoard board = new CustomBoard();
        assertEquals(true, board.getPiece(new int[]{5,5}) instanceof UltraRook);
        assertEquals(true, board.getPiece(new int[]{2,3}) instanceof UltraKnight);
    }
}
