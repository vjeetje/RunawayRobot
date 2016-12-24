package xavier.rasschaert.hacker.org.solver;

import org.junit.Assert;
import org.junit.Test;
import xavier.rasschaert.hacker.org.model.Board;
import xavier.rasschaert.hacker.org.model.IntegerArray2D;
import xavier.rasschaert.hacker.org.model.Position;

public class PuzzlePostProcessorTest {

    private final PuzzlePostprocessor puzzlePostprocessor = new PuzzlePostprocessor();

    @Test
    public void testPostProcessSimplePuzzle() {
        int[][] terrainInput = {
                {0, 0, 1},
                {1, 0, 0},
                {0, 1, 0}
        };

        Board board = new Board(new IntegerArray2D(terrainInput), false);
        puzzlePostprocessor.postprocess(board);
        Assert.assertEquals("RDRD", puzzlePostprocessor.postprocess(board));
    }

    @Test
    public void testPostProcessComplexPuzzle() {
        int[][] terrainInput = {
                {0, 0, 0, 1, 1, 1, 0, 1},
                {1, 1, 0, 1, 0, 1, 0, 0},
                {0, 1, 0, 0, 0, 0, 1, 1},
                {0, 0, 1, 1, 1, 0, 1, 0},
                {0, 1, 0, 0, 0, 0, 1, 1},
                {1, 0, 1, 0, 1, 0, 0, 1},
                {0, 1, 0, 0, 0, 1, 0, 0},
                {0, 0, 0, 0, 0, 1, 1, 0}
        };

        Board board = new Board(new IntegerArray2D(terrainInput), false);
        board = board.getSubBoard(Position.ORIGIN, new Position(7, 7)); // remove finish locations
        puzzlePostprocessor.postprocess(board);
        Assert.assertEquals("RRDDRRRDDDRDRD", puzzlePostprocessor.postprocess(board));
    }
}
