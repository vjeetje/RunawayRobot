package xavier.rasschaert.hacker.org;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import xavier.rasschaert.hacker.org.model.Puzzle;
import xavier.rasschaert.hacker.org.network.DataFetcher;
import xavier.rasschaert.hacker.org.properties.UrlProperties;

import javax.inject.Inject;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Inject))
public class PuzzleSolver implements CommandLineRunner {
    private final Logger LOGGER = LoggerFactory.getLogger(PuzzleSolver.class);

    @NonNull
    private final UrlProperties urlProperties;
    @NonNull
    private final DataFetcher dataFetcher;
    @NonNull
    private final PuzzlePreProcessor puzzlePreProcessor;

    @Override
    public void run(String... args) throws Exception {
        //dataFetcher.goToLevel(urlProperties.getProgress().getLevel());
        Puzzle puzzle = dataFetcher.receivePuzzle(1);//todo add level property to start from
        LOGGER.info(puzzle.toString());
        puzzlePreProcessor.preprocess(puzzle);
        LOGGER.info(puzzle.toString());
        // Board map = new Board();
    }
}
