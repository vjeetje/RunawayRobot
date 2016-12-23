package xavier.rasschaert.hacker.org;

import org.junit.Assert;
import org.junit.Test;
import xavier.rasschaert.hacker.org.model.Board;
import xavier.rasschaert.hacker.org.model.Position;
import xavier.rasschaert.hacker.org.model.Puzzle;

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
        Puzzle puzzle = new Puzzle(1, 3, 5, new Board(terrainInput));
        Set<Position> expectedPassablePositions = Stream.of(
                new Position(1, 2), new Position(2, 1),
                new Position(4, 0), new Position(0, 4),
                new Position(5, 0), new Position(3, 2), new Position(1, 4)
        ).collect(Collectors.toSet());

        Set<Position> calculatedPassablePositions = new HashSet<>();
        PuzzleIterator puzzleIterator = new PuzzleIterator(puzzle);
        while (puzzleIterator.hasNext()) {
            calculatedPassablePositions.add(puzzleIterator.next());
        }

        Assert.assertEquals(expectedPassablePositions, calculatedPassablePositions);
    }
}
