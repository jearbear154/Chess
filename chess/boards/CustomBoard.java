package chess.boards;

import chess.Color;
import chess.boards.StandardBoard;
import chess.pieces.*;

/**
 * CustomBoard --- class that represents the board of a normal chess game with the UltraKnight and UltraRook pieces
 * included
 * @author     Jeremy McMahan
 */
public class CustomBoard extends StandardBoard {
    //sets up the board
    public CustomBoard() {
        super();
        addPiece(new UltraKnight(Color.W, 2,3), new int[]{2,3});
        addPiece(new UltraKnight(Color.B, 5,3), new int[]{5,3});
        addPiece(new UltraRook(Color.W,2,5), new int[]{2,5});
        addPiece(new UltraRook(Color.B,5,5), new int[]{5,5});
    }
}
