package xavier.rasschaert.hacker.org;

import org.junit.Assert;
import org.junit.Test;
import xavier.rasschaert.hacker.org.model.Board;
import xavier.rasschaert.hacker.org.model.IntegerArray2D;
import xavier.rasschaert.hacker.org.model.Puzzle;

public class PuzzlePreProcessorTest {

    private final PuzzlePreProcessor puzzlePreProcessor = new PuzzlePreProcessor();

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

        Puzzle puzzle = new Puzzle(1, 3, 5, new Board(terrainInput));
        puzzlePreProcessor.preprocess(puzzle);

        int[][] terrainExpectedOutput = {
                {0, 0, 0, 0, 1, 1, 1, 1},
                {0, 0, 0, 0, 0, 1, 1, 1},
                {0, 1, 0, 0, 0, 0, 1, 1},
                {0, 0, 0, 1, 0, 0, 1, 1},
                {0, 0, 0, 0, 0, 0, 0, 1},
                {1, 1, 1, 0, 0, 0, 0, 0},
                {1, 1, 1, 0, 0, 0, 0, 0},
                {1, 1, 1, 0, 0, 0, 0, 0}
        };

        Assert.assertEquals(new IntegerArray2D(terrainExpectedOutput), puzzle.getBoard().getTerrain());
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

        Puzzle puzzle = new Puzzle(2, 3, 5, new Board(terrainInput));
        puzzlePreProcessor.preprocess(puzzle);

        int[][] terrainExpectedOutput = {
                {0, 0, 0, 1, 1, 1, 1, 1},
                {0, 0, 0, 1, 1, 1, 1, 1},
                {0, 1, 0, 1, 1, 1, 1, 1},
                {0, 0, 0, 1, 1, 1, 1, 1},
                {1, 1, 0, 0, 0, 0, 0, 1},
                {1, 1, 1, 0, 1, 1, 0, 0},
                {1, 1, 1, 0, 0, 1, 0, 0},
                {1, 1, 1, 0, 0, 1, 0, 0}
        };

        Assert.assertEquals(new IntegerArray2D(terrainExpectedOutput), puzzle.getBoard().getTerrain());
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

        Puzzle puzzle = new Puzzle(3, 3, 5, new Board(terrainInput));
        puzzlePreProcessor.preprocess(puzzle);

        int[][] terrainExpectedOutput = {
                {1, 1, 1, 1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1, 1, 1, 1}
        };

        Assert.assertEquals(new IntegerArray2D(terrainExpectedOutput), puzzle.getBoard().getTerrain());
    }
}
