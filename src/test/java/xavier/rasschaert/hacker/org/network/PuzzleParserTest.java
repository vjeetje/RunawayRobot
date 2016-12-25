package xavier.rasschaert.hacker.org.network;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import xavier.rasschaert.hacker.org.exception.PuzzleParseException;
import xavier.rasschaert.hacker.org.model.Board;
import xavier.rasschaert.hacker.org.model.IntegerArray2D;
import xavier.rasschaert.hacker.org.model.Puzzle;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class PuzzleParserTest {
    private static String challengePageTemplate, puzzleTemplate;

    private PuzzleParser puzzleParser = new PuzzleParser();

    @Before
    public void setUp() throws IOException, URISyntaxException {
        challengePageTemplate = new String(Files.readAllBytes(Paths.get(getClass().getResource("/challengePageTemplate.html").toURI())));
        puzzleTemplate = new String(Files.readAllBytes(Paths.get(getClass().getResource("/puzzleTemplate.html").toURI())));
    }

    @Test
    public void testPuzzleParsing() throws PuzzleParseException {
        String rawPuzzle = "FVterrainString=.X.X.XXX...X&FVinsMax=2&FVinsMin=1&FVboardX=3&FVboardY=4&FVlevel=5";
        Puzzle puzzle = puzzleParser.parsePuzzle(getDocumentWithPuzzle(rawPuzzle));
        int[][] terrain = new int[][]{{0, 1, 0}, {1, 0, 1}, {1, 1, 0}, {0, 0, 1}};
        Assert.assertEquals(new Board(new IntegerArray2D(terrain), true), puzzle.getBoard());
        Assert.assertEquals(1, puzzle.getMinMoves());
        Assert.assertEquals(2, puzzle.getMaxMoves());
        Assert.assertEquals(5, puzzle.getLevel());
    }

    @Test
    public void testPuzzleParsingInComments() throws PuzzleParseException {
        String rawPuzzle = "FVterrainString=.X.X.XXX...X&FVinsMax=2&FVinsMin=1&FVboardX=3&FVboardY=4&FVlevel=5";
        Puzzle puzzle = puzzleParser.parsePuzzle(getDocumentWithPuzzleInComments(rawPuzzle));
        int[][] terrain = new int[][]{{0, 1, 0}, {1, 0, 1}, {1, 1, 0}, {0, 0, 1}};
        Assert.assertEquals(new Board(new IntegerArray2D(terrain), true), puzzle.getBoard());
        Assert.assertEquals(1, puzzle.getMinMoves());
        Assert.assertEquals(2, puzzle.getMaxMoves());
        Assert.assertEquals(5, puzzle.getLevel());
    }

    @Test(expected = PuzzleParseException.class)
    public void testPuzzleParsingExceptionIncompleteData() throws PuzzleParseException {
        String rawPuzzle = "FVterrainString=.X.X.XXX...X";
        puzzleParser.parsePuzzle(getDocumentWithPuzzle(rawPuzzle));
    }

    @Test(expected = PuzzleParseException.class)
    public void testPuzzleParsingExceptionInvalidData() throws PuzzleParseException {
        String rawPuzzle = "FVterrainString=.X.Y.ZZZ...Y&FVinsMax=2&FVinsMin=1&FVboardX=3&FVboardY=4&FVlevel=5";
        puzzleParser.parsePuzzle(getDocumentWithPuzzle(rawPuzzle));
    }

    @Test(expected = PuzzleParseException.class)
    public void testPuzzleParsingExceptionMissingCharacter() throws PuzzleParseException {
        String rawPuzzle = "FVstring=.X.Y.ZZZ...Y&FVinsMax=2&FVinsMin=1&FVboardX=3&FVboardY=4&FVlevel=5";
        puzzleParser.parsePuzzle(getDocumentWithPuzzle(rawPuzzle));
    }

    private Document getDocumentWithPuzzle(String flashVars) {
        String htmlPuzzle = String.format(puzzleTemplate, flashVars, flashVars);
        String htmlPage = String.format(challengePageTemplate, htmlPuzzle);
        return Jsoup.parse(htmlPage);
    }

    private Document getDocumentWithPuzzleInComments(String flashVars) {
        String htmlPuzzle = String.format(puzzleTemplate, flashVars, flashVars);
        String htmlPuzzleInComments = String.format("<!--%s-->", htmlPuzzle);
        String htmlPage = String.format(challengePageTemplate, htmlPuzzleInComments);
        return Jsoup.parse(htmlPage);
    }
}
