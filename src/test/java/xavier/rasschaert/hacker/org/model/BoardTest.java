package xavier.rasschaert.hacker.org.model;

import org.junit.Assert;
import org.junit.Test;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BoardTest {

    @Test
    public void testGetPassablePositions() {
        int[][] terrainInput = {
                {0, 1, 0},
                {1, 0, 1},
                {0, 1, 0},
        };
        Board board = new Board(new IntegerArray2D(terrainInput), false);
        Set<Position> expectedPassablePositions = Stream.of(
                new Position(0, 0), new Position(2, 0),
                new Position(1, 1),
                new Position(0, 2), new Position(2, 2)
        ).collect(Collectors.toSet());

        Assert.assertEquals(expectedPassablePositions, board.getPassableLocations());
    }

    @Test
    public void testBoardEquals() {
        int[][] terrain1 = {
                {0, 1, 0},
                {1, 0, 1},
                {0, 1, 0}
        };
        int[][] terrain2 = {
                {0, 1, 0},
                {1, 1, 1},
                {0, 1, 0}
        };
        int[][] terrain3 = {
                {0, 3, 0},
                {5, 2, 1},
                {0, 4, 0}
        };

        Assert.assertEquals(new Board(new IntegerArray2D(terrain1), true), new Board(new IntegerArray2D(terrain1), true));
        Assert.assertEquals(new Board(new IntegerArray2D(terrain1), false), new Board(new IntegerArray2D(terrain1), false));
        Assert.assertEquals(new Board(new IntegerArray2D(terrain2), true), new Board(new IntegerArray2D(terrain2), true));
        Assert.assertEquals(new Board(new IntegerArray2D(terrain2), false), new Board(new IntegerArray2D(terrain2), false));
        Assert.assertNotEquals(new Board(new IntegerArray2D(terrain1), false), new Board(new IntegerArray2D(terrain2), false));
        Assert.assertNotEquals(new Board(new IntegerArray2D(terrain1), false), new Board(new IntegerArray2D(terrain3), false));
        Assert.assertEquals(new Board(new IntegerArray2D(terrain2), false), new Board(new IntegerArray2D(terrain3), false));
        Assert.assertEquals(new Board(new IntegerArray2D(terrain2), true), new Board(new IntegerArray2D(terrain3), true));
        Assert.assertEquals(new Board(new IntegerArray2D(terrain3), true), new Board(new IntegerArray2D(terrain3), true));
    }
}