package xavier.rasschaert.hacker.org;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import xavier.rasschaert.hacker.org.model.Puzzle;
import xavier.rasschaert.hacker.org.network.DataFetcher;
import xavier.rasschaert.hacker.org.properties.UrlProperties;

import javax.inject.Inject;

@Component
public class PuzzleSolver implements CommandLineRunner {
    private final Logger LOGGER = LoggerFactory.getLogger(PuzzleSolver.class);

    @Inject
    private UrlProperties urlProperties;
    @Inject
    private DataFetcher dataFetcher;
    @Inject
    private PuzzlePreProcessor puzzlePreProcessor;

    @Override
    public void run(String... args) throws Exception {
        // todo enable constructor @inject warning
        // todo use constructor @inject and bean configuration file
        //dataFetcher.goToLevel(urlProperties.getProgress().getLevel());
        Puzzle puzzle = dataFetcher.receivePuzzle(1);//todo add level property to start from
        LOGGER.info(puzzle.toString());
        puzzlePreProcessor.preprocess(puzzle);
        LOGGER.info(puzzle.toString());
        // Board map = new Board();
    }
}
