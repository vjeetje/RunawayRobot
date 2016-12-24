package xavier.rasschaert.hacker.org;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import xavier.rasschaert.hacker.org.exception.PuzzleNoSolutionException;
import xavier.rasschaert.hacker.org.model.Puzzle;
import xavier.rasschaert.hacker.org.network.DataFetcher;
import xavier.rasschaert.hacker.org.properties.ProgressProperties;
import xavier.rasschaert.hacker.org.solver.PuzzleSolver;

import javax.inject.Inject;
import java.io.IOException;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Inject))
public class ChallengeSolver implements CommandLineRunner {
    private final Logger LOGGER = LoggerFactory.getLogger(ChallengeSolver.class);

    @NonNull
    private final ProgressProperties progressProperties;
    @NonNull
    private final DataFetcher dataFetcher;
    @NonNull
    private final PuzzleSolver puzzleSolver;

    @Override
    public void run(String... args) throws Exception {
        try {
            Puzzle puzzle = dataFetcher.receivePuzzle(progressProperties.getLevel());
            //noinspection InfiniteLoopStatement
            while (true) {
                String path = puzzleSolver.solve(puzzle);
                puzzle = dataFetcher.submitPuzzle(path);
            }
        } catch (IOException | PuzzleNoSolutionException e) {
            LOGGER.error("Could not solve puzzle", e);
        }
    }
}
