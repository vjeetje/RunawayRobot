package xavier.rasschaert.hacker.org.solver;

import org.junit.Assert;
import org.junit.Test;
import xavier.rasschaert.hacker.org.exception.PuzzleNoSolutionException;
import xavier.rasschaert.hacker.org.model.Board;
import xavier.rasschaert.hacker.org.model.IntegerArray2D;
import xavier.rasschaert.hacker.org.model.Puzzle;

public class PuzzleAlgorithmTest {
    private final PuzzlePreprocessor puzzlePreprocessor = new PuzzlePreprocessor();
    private final PuzzleAlgorithm puzzleAlgorithm = new PuzzleAlgorithm(puzzlePreprocessor);

    // todo rewrite test with finished algorithm implementation
    @Test
    public void testAlgorithmWithOneDimensionalSubBoard() throws PuzzleNoSolutionException {
        int[][] terrainInput = {
                {0, 0, 0, 0, 0, 1, 0, 1},
                {0, 0, 1, 0, 0, 1, 0, 0},
                {0, 0, 0, 0, 0, 1, 0, 1},
                {1, 0, 0, 1, 0, 0, 1, 0},
                {0, 0, 0, 1, 0, 0, 1, 1},
                {1, 0, 0, 0, 1, 0, 1, 0},
                {0, 1, 0, 0, 1, 0, 1, 0},
                {0, 0, 0, 0, 0, 0, 0, 0}
        };

        Puzzle puzzle = new Puzzle(1, 3, 5, new Board(new IntegerArray2D(terrainInput), true));
        Board subBoard = puzzleAlgorithm.execute(puzzle);

        int[][] terrainExpectedOutput = {
                {0, 1, 1, 0}
        };

        // todo this equals is not working right, should be false, but it succeeds atm
        Assert.assertEquals(new Board(new IntegerArray2D(terrainExpectedOutput), false), subBoard);
    }

    // todo rewrite test with finished algorithm implementation
    @Test
    public void testAlgorithmWithTwoDimensionalSubBoard() throws PuzzleNoSolutionException {
        int[][] terrainInput = {
                {0, 0, 0, 1, 0, 1, 0, 1},
                {0, 0, 0, 1, 0, 1, 0, 0},
                {0, 0, 0, 0, 0, 1, 0, 1},
                {1, 0, 0, 1, 0, 0, 1, 0},
                {1, 0, 0, 1, 0, 0, 1, 1},
                {1, 1, 0, 0, 1, 0, 1, 0},
                {0, 1, 0, 0, 1, 0, 1, 0},
                {0, 0, 0, 0, 0, 0, 0, 0}
        };

        Puzzle puzzle = new Puzzle(1, 3, 5, new Board(new IntegerArray2D(terrainInput), true));
        Board subBoard = puzzleAlgorithm.execute(puzzle);

        int[][] terrainExpectedOutput = {
                {0, 0},
                {0, 1},
                {0, 1},
                {1, 0},
        };

        // todo this equals is not working right, should be false, but it succeeds atm
        Assert.assertEquals(new Board(new IntegerArray2D(terrainExpectedOutput), false), subBoard);
    }

    // todo test which ends outside and it matters
    // todo test that ends exactly at the border
    // todo test puzzle with no solution error thrown
}
