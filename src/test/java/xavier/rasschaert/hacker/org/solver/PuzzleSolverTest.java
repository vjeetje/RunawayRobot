package xavier.rasschaert.hacker.org.solver;

import org.junit.Assert;
import org.junit.Test;
import xavier.rasschaert.hacker.org.exception.PuzzleNoSolutionException;
import xavier.rasschaert.hacker.org.model.Board;
import xavier.rasschaert.hacker.org.model.IntegerArray2D;
import xavier.rasschaert.hacker.org.model.Puzzle;

public class PuzzleSolverTest {

    private static final PuzzlePreprocessor puzzlePreProcessor = new PuzzlePreprocessor();
    private static final PuzzleAlgorithm puzzleAlgorithm = new PuzzleAlgorithm(puzzlePreProcessor);
    private static final PuzzlePostprocessor puzzlePostprocessor = new PuzzlePostprocessor();

    private static final PuzzleSolver puzzleSolver = new PuzzleSolver(puzzlePreProcessor, puzzleAlgorithm, puzzlePostprocessor);

    @Test
    public void testPuzzleSolvingLevel7() throws PuzzleNoSolutionException {
        int[][] terrainInput = {
                {0, 0, 0, 0, 0, 1},
                {1, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0},
                {1, 0, 0, 0, 0, 0},
                {1, 0, 0, 0, 0, 0},
                {0, 0, 0, 1, 1, 0}
        };

        Puzzle puzzle = new Puzzle(7, 3, 4, new Board(new IntegerArray2D(terrainInput), true));
        Assert.assertEquals("RRD", puzzleSolver.solve(puzzle));
    }

    @Test
    public void testPuzzleSolvingLevel18() throws PuzzleNoSolutionException {

        int[][] terrainInput = {
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0},
                {0, 0, 0, 1, 0, 1, 0, 1, 0, 0, 0, 0, 0},
                {0, 1, 0, 1, 0, 0, 1, 1, 0, 0, 0, 0, 0},
                {0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 1, 0, 0},
                {0, 1, 0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0},
                {0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0},
                {0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0},
                {0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {1, 1, 0, 0, 0, 1, 0, 0, 1, 0, 0, 1, 0},
                {1, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 1, 0, 0, 0, 1, 1, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}
        };

        Puzzle puzzle = new Puzzle(18, 5, 7, new Board(new IntegerArray2D(terrainInput), true));
        Assert.assertEquals("DDDDRRR", puzzleSolver.solve(puzzle));
    }
}
