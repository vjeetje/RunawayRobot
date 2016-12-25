package xavier.rasschaert.hacker.org.network;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import xavier.rasschaert.hacker.org.TestApplicationConfiguration;
import xavier.rasschaert.hacker.org.exception.PuzzleParseException;
import xavier.rasschaert.hacker.org.model.Puzzle;

import javax.inject.Inject;
import java.io.IOException;
import java.util.Arrays;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestApplicationConfiguration.class)
@ActiveProfiles({"live"})
public class DataFetcherIT {

    @Inject
    private DataFetcher dataFetcher;

    @Test
    public void testPuzzleRetrievalLevel1() throws IOException, PuzzleParseException {
        Puzzle puzzle = dataFetcher.receivePuzzle(1);
        Assert.assertNotNull(puzzle);
        Assert.assertEquals(1, puzzle.getLevel());
        Assert.assertEquals(2, puzzle.getMinMoves());
        Assert.assertEquals(2, puzzle.getMaxMoves());
        Assert.assertEquals(4, puzzle.getBoard().getWidth());
        Assert.assertEquals(4, puzzle.getBoard().getHeight());
    }

    @Test(expected = PuzzleParseException.class)
    public void testPuzzleRetrievalBadLevel() throws IOException, PuzzleParseException {
        dataFetcher.receivePuzzle(-1);
    }

    @Test
    public void testPuzzleSolutionSubmissionLevel1() throws IOException, PuzzleParseException {
        dataFetcher.receivePuzzle(1);
        final String[] solutions = new String[]{"RR", "RD", "DR", "DD"};
        for (String solution : solutions) {
            try {
                Puzzle puzzle = dataFetcher.submitPuzzle(solution);
                Assert.assertEquals(2, puzzle.getLevel());
                return;
            } catch (IOException | PuzzleParseException ignored) {

            }
        }
        throw new IOException(String.format("Not one of [%s] was accepted as a solution", Arrays.toString(solutions)));
    }

    @Test(expected = PuzzleParseException.class)
    public void testPuzzleSolutionSubmissionBadLevel() throws IOException, PuzzleParseException {
        dataFetcher.submitPuzzle("INVALID_PATH");
    }
}
