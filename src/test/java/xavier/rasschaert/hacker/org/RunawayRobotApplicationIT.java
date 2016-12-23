package xavier.rasschaert.hacker.org;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import xavier.rasschaert.hacker.org.model.Puzzle;
import xavier.rasschaert.hacker.org.network.DataFetcher;

import javax.inject.Inject;
import java.io.IOException;
import java.text.ParseException;
import java.util.Arrays;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestApplicationConfiguration.class)
@ActiveProfiles({"live"})
public class RunawayRobotApplicationIT {

    @Inject
    private DataFetcher dataFetcher;

    @Test
    public void testPuzzleRetrievalLevel1() throws IOException, ParseException {
        Puzzle puzzle = dataFetcher.receivePuzzle(1);
        Assert.assertNotNull(puzzle);
        Assert.assertEquals(1, puzzle.getLevel());
        Assert.assertEquals(2, puzzle.getMinMoves());
        Assert.assertEquals(2, puzzle.getMaxMoves());
        Assert.assertEquals(3, puzzle.getBoard().getWidth());
        Assert.assertEquals(3, puzzle.getBoard().getHeight());
    }

    @Test
    public void testPuzzleSolutionSubmissionLevel1() throws IOException, ParseException {
        dataFetcher.receivePuzzle(1);
        final String[] solutions = new String[]{"RR", "RD", "DR", "DD"};
        for (String solution : solutions) {
            try {
                Puzzle puzzle = dataFetcher.submitPuzzle(solution);
                Assert.assertEquals(2, puzzle.getLevel());
                return;
            } catch (IOException | ParseException ignored) {

            }
        }
        throw new IOException(String.format("Not one of [%s] was accepted as a solution", Arrays.toString(solutions)));
    }

}
