package xavier.rasschaert.hacker.org;

import org.junit.Assert;
import org.junit.Test;
import xavier.rasschaert.hacker.org.model.Board;
import xavier.rasschaert.hacker.org.model.IntegerArray2D;
import xavier.rasschaert.hacker.org.model.Position;
import xavier.rasschaert.hacker.org.model.Puzzle;
import xavier.rasschaert.hacker.org.solver.PuzzleIterator;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PuzzleIteratorTest {

    @Test
    public void testPuzzleIterator() {
        int[][] terrainInput = {
                {0, 0, 0, 1, 0, 1, 0, 1},
                {0, 0, 0, 1, 0, 0, 1, 0},
                {0, 0, 1, 1, 0, 1, 0, 0},
                {1, 1, 0, 0, 0, 1, 0, 0},
                {0, 1, 1, 0, 1, 0, 0, 0},
                {0, 0, 0, 1, 1, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0}
        };
        Puzzle puzzle = new Puzzle(1, 3, 5, new Board(new IntegerArray2D(terrainInput), false));
        Set<Position> expectedPassablePositions = Stream.of(
                new Position(1, 2), new Position(2, 1),
                new Position(4, 0), new Position(0, 4),
                new Position(0, 5), new Position(2, 3), new Position(4, 1)
        ).collect(Collectors.toSet());

        Set<Position> calculatedPassablePositions = new HashSet<>();
        PuzzleIterator puzzleIterator = new PuzzleIterator(puzzle);
        while (puzzleIterator.hasNext()) {
            calculatedPassablePositions.add(puzzleIterator.next());
        }

        Assert.assertEquals(expectedPassablePositions, calculatedPassablePositions);
    }
}
