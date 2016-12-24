package xavier.rasschaert.hacker.org.network;

import org.junit.Assert;
import org.junit.Test;
import xavier.rasschaert.hacker.org.exception.PuzzleParseException;
import xavier.rasschaert.hacker.org.model.Board;
import xavier.rasschaert.hacker.org.model.IntegerArray2D;
import xavier.rasschaert.hacker.org.model.Puzzle;

public class PuzzleParserTest {
    private PuzzleParser puzzleParser = new PuzzleParser();

    @Test
    public void testPuzzleParsing() throws PuzzleParseException {
        String rawPuzzle = "FVterrainString=.X.X.XXX...X&FVinsMax=2&FVinsMin=1&FVboardX=3&FVboardY=4&FVlevel=5";
        Puzzle puzzle = puzzleParser.parsePuzzle(rawPuzzle);
        int[][] terrain = new int[][]{{0, 1, 0}, {1, 0, 1}, {1, 1, 0}, {0, 0, 1}};
        Assert.assertEquals(new Board(new IntegerArray2D(terrain), true), puzzle.getBoard());
        Assert.assertEquals(1, puzzle.getMinMoves());
        Assert.assertEquals(2, puzzle.getMaxMoves());
        Assert.assertEquals(5, puzzle.getLevel());
    }


    @Test(expected = PuzzleParseException.class)
    public void testPuzzleParsingExceptionIncompleteData() throws PuzzleParseException {
        String rawPuzzle = "FVterrainString=.X.X.XXX...X";
        Puzzle puzzle = puzzleParser.parsePuzzle(rawPuzzle);
    }

    @Test(expected = PuzzleParseException.class)
    public void testPuzzleParsingExceptionInvalidData() throws PuzzleParseException {
        String rawPuzzle = "FVterrainString=.X.Y.ZZZ...Y&FVinsMax=2&FVinsMin=1&FVboardX=3&FVboardY=4&FVlevel=5";
        Puzzle puzzle = puzzleParser.parsePuzzle(rawPuzzle);
    }

    @Test(expected = PuzzleParseException.class)
    public void testPuzzleParsingExceptionMissingCharacter() throws PuzzleParseException {
        String rawPuzzle = "FVstring=.X.Y.ZZZ...Y&FVinsMax=2&FVinsMin=1&FVboardX=3&FVboardY=4&FVlevel=5";
        Puzzle puzzle = puzzleParser.parsePuzzle(rawPuzzle);
    }
}
