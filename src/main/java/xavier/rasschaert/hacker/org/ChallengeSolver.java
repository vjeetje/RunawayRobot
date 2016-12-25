package xavier.rasschaert.hacker.org;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import xavier.rasschaert.hacker.org.exception.PuzzleParseException;
import xavier.rasschaert.hacker.org.model.Puzzle;
import xavier.rasschaert.hacker.org.network.DataFetcher;
import xavier.rasschaert.hacker.org.properties.ProgressProperties;
import xavier.rasschaert.hacker.org.solver.PuzzleSolver;

import javax.inject.Inject;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Inject))
public class ChallengeSolver implements CommandLineRunner {
    @NonNull
    private final ProgressProperties progressProperties;
    @NonNull
    private final DataFetcher dataFetcher;
    @NonNull
    private final PuzzleSolver puzzleSolver;

    @Override
    public void run(String... args) throws Exception {
        int level = progressProperties.getLevel().getMin();
        while (level <= progressProperties.getLevel().getMax()) {
            Puzzle puzzle = dataFetcher.receivePuzzle(level++);
            String path = puzzleSolver.solve(puzzle);
            try {//the levels 510 till 513 can be solved but don't return a valid puzzle
                dataFetcher.submitPuzzle(path);
            } catch (PuzzleParseException ignored) {

            }
        }
    }
}
