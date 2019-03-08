package tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import tests.boardtests.ChessBoardTest;
import tests.boardtests.CustomBoardTest;
import tests.boardtests.StandardBoardTest;
import tests.piecetests.*;

@RunWith(Suite.class)
@Suite.SuiteClasses({BishopTest.class, KingTest.class, KnightTest.class, PawnTest.class, QueenTest.class, RookTest.class,
	BoardConfigurationTest.class, ChessBoardTest.class, ChessTest.class, UltraKnightTest.class, UltraRookTest.class,
        StandardBoardTest.class, CustomBoardTest.class, ChessGameGUITest.class})

public class AllTests {

}
