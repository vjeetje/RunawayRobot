package xavier.rasschaert.hacker.org.solver;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import xavier.rasschaert.hacker.org.exception.PuzzleNoSolutionException;
import xavier.rasschaert.hacker.org.model.Board;
import xavier.rasschaert.hacker.org.model.Puzzle;

import javax.inject.Inject;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Inject))
public class PuzzleSolver {
    @NonNull
    private final PuzzlePreprocessor puzzlePreProcessor;
    @NonNull
    private final PuzzleAlgorithm puzzleAlgorithm;
    @NonNull
    private final PuzzlePostprocessor puzzlePostprocessor;

    /**
     * solve the puzzle
     *
     * @param puzzle the puzzle
     * @return a valid path
     */
    public String solve(@NonNull Puzzle puzzle) throws PuzzleNoSolutionException {
        puzzlePreProcessor.preprocess(puzzle.getBoard(), false);
        Board board = puzzleAlgorithm.execute(puzzle);
        return puzzlePostprocessor.postprocess(board);
    }
}