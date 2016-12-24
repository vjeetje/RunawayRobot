package xavier.rasschaert.hacker.org.solver;

import org.junit.Assert;
import org.junit.Test;
import xavier.rasschaert.hacker.org.model.Board;
import xavier.rasschaert.hacker.org.model.IntegerArray2D;

public class PuzzlePreProcessorTest {

    private final PuzzlePreprocessor puzzlePreProcessor = new PuzzlePreprocessor();

    @Test
    public void testPreProcessSimplePuzzle() {
        int[][] terrainInput = {
                {0, 0, 0, 0, 1, 1, 0, 1},
                {0, 0, 0, 0, 0, 1, 0, 0},
                {0, 1, 0, 0, 0, 0, 0, 1},
                {0, 0, 0, 1, 0, 0, 1, 0},
                {0, 0, 0, 0, 0, 0, 0, 1},
                {1, 0, 1, 0, 0, 0, 0, 0},
                {0, 1, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0}
        };

        Board board = new Board(new IntegerArray2D(terrainInput), true);
        puzzlePreProcessor.preprocess(board, false);

        int[][] terrainExpectedOutput = {
                {0, 0, 0, 0, 1, 1, 1, 1, 1},
                {0, 0, 0, 0, 0, 1, 1, 1, 1},
                {0, 1, 0, 0, 0, 0, 1, 1, 1},
                {0, 0, 0, 1, 0, 0, 1, 1, 1},
                {0, 0, 0, 0, 0, 0, 0, 1, 1},
                {1, 1, 1, 0, 0, 0, 0, 0, 0},
                {1, 1, 1, 0, 0, 0, 0, 0, 0},
                {1, 1, 1, 0, 0, 0, 0, 0, 0},
                {1, 1, 1, 0, 0, 0, 0, 0, 0}
        };

        Assert.assertEquals(new IntegerArray2D(terrainExpectedOutput), board.getTerrain());
    }

    @Test
    public void testPreProcessComplexPuzzle() {
        int[][] terrainInput = {
                {0, 0, 0, 0, 1, 1, 0, 1},
                {0, 0, 0, 0, 0, 1, 0, 0},
                {0, 1, 0, 0, 1, 0, 0, 1},
                {0, 0, 0, 1, 0, 0, 1, 0},
                {0, 1, 0, 0, 0, 0, 0, 1},
                {1, 0, 1, 0, 1, 1, 0, 0},
                {0, 1, 0, 0, 0, 1, 0, 0},
                {0, 0, 0, 0, 0, 1, 0, 1}
        };

        Board board = new Board(new IntegerArray2D(terrainInput), true);
        puzzlePreProcessor.preprocess(board, false);

        int[][] terrainExpectedOutput = {
                {0, 0, 0, 1, 1, 1, 1, 1, 1},
                {0, 0, 0, 1, 1, 1, 1, 1, 1},
                {0, 1, 0, 1, 1, 1, 1, 1, 1},
                {0, 0, 0, 1, 1, 1, 1, 1, 1},
                {1, 1, 0, 0, 0, 0, 0, 1, 1},
                {1, 1, 1, 0, 1, 1, 0, 0, 0},
                {1, 1, 1, 0, 0, 1, 0, 0, 0},
                {1, 1, 1, 0, 0, 1, 0, 1, 0},
                {1, 1, 1, 0, 0, 0, 0, 0, 0}
        };

        Assert.assertEquals(new IntegerArray2D(terrainExpectedOutput), board.getTerrain());
    }

    @Test
    public void testPreProcessImpossiblePuzzle() {
        int[][] terrainInput = {
                {0, 0, 0, 0, 0, 0, 0, 1},
                {0, 0, 0, 0, 0, 0, 1, 0},
                {0, 0, 0, 0, 0, 1, 0, 0},
                {0, 1, 0, 0, 0, 1, 0, 0},
                {1, 0, 1, 0, 1, 0, 0, 0},
                {0, 0, 0, 1, 1, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0}
        };

        Board board = new Board(new IntegerArray2D(terrainInput), true);
        puzzlePreProcessor.preprocess(board, false);

        int[][] terrainExpectedOutput = {
                {1, 1, 1, 1, 1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1, 1, 1, 1, 1}
        };

        Assert.assertEquals(new IntegerArray2D(terrainExpectedOutput), board.getTerrain());
    }

    @Test
    public void testPreProcessSubBoardEliminateTopRow() {
        int[][] terrainInput = {
                {0, 0, 0, 0},
                {0, 1, 1, 1},
                {0, 1, 1, 1},
                {0, 1, 1, 1},
                {0, 0, 0, 0}
        };

        Board board = new Board(new IntegerArray2D(terrainInput), false);
        puzzlePreProcessor.preprocess(board, true);

        int[][] terrainExpectedOutput = {
                {0, 1, 1, 1},
                {0, 1, 1, 1},
                {0, 1, 1, 1},
                {0, 1, 1, 1},
                {0, 0, 0, 0}
        };

        Assert.assertEquals(new IntegerArray2D(terrainExpectedOutput), board.getTerrain());
    }

    @Test
    public void testPreProcessSubBoardEliminateBottomRow() {
        int[][] terrainInput = {
                {0, 0, 0, 0},
                {0, 1, 1, 1},
                {0, 1, 1, 1},
                {0, 1, 0, 1},
                {0, 0, 1, 0}
        };

        Board board = new Board(new IntegerArray2D(terrainInput), false);
        puzzlePreProcessor.preprocess(board, true);

        int[][] terrainExpectedOutput = {
                {1, 1, 1, 1},
                {1, 1, 1, 1},
                {1, 1, 1, 1},
                {1, 1, 1, 1},
                {1, 1, 1, 1}
        };

        Assert.assertEquals(new IntegerArray2D(terrainExpectedOutput), board.getTerrain());
    }
}
