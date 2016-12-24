package xavier.rasschaert.hacker.org.solver;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import xavier.rasschaert.hacker.org.exception.PuzzleNoSolutionException;
import xavier.rasschaert.hacker.org.model.Board;
import xavier.rasschaert.hacker.org.model.IntegerArray2D;
import xavier.rasschaert.hacker.org.model.Position;
import xavier.rasschaert.hacker.org.model.Puzzle;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Inject))
public class PuzzleAlgorithm {

    @NonNull
    private final PuzzlePreprocessor puzzlePreprocessor;

    /**
     * search for a sub board within the minMoves and MaxMoves of the puzzle.
     * All positions that are impassible are marked as such.
     * The result is a sub board which can be solved.
     *
     * @param puzzle the puzzle
     * @return a sub board with a solution
     */
    public Board execute(Puzzle puzzle) throws PuzzleNoSolutionException {
        PuzzleIterator puzzleIterator = new PuzzleIterator(puzzle);
        while (puzzleIterator.hasNext()) {
            Position firstPathEnding = puzzleIterator.next();
            List<Position> pathEndings = getAllPathEndings(puzzle.getBoard(), firstPathEnding);
            if (!pathEndings.isEmpty()) {
                Board subBoard = getSubBoard(puzzle.getBoard(), pathEndings);
                puzzlePreprocessor.preprocess(subBoard, true);
                if (isSolvable(subBoard)) {
                    return subBoard;
                }
            }
        }
        throw new PuzzleNoSolutionException("The puzzle has no solution.");
    }

    /**
     * get the list of the path endings when looping through all iterations
     *
     * @param board           the board
     * @param firstPathEnding the ending location of the first iteration
     * @return the path endings of iterations if solvable, otherwise an empty list
     */
    private List<Position> getAllPathEndings(@NonNull Board board, @NonNull Position firstPathEnding) {
        List<Position> allPathEndings = new ArrayList<>();
        Position lastPathEnding = Position.ORIGIN;
        while (!board.isOnFinishLocation(lastPathEnding)) {
            lastPathEnding = lastPathEnding.add(firstPathEnding.getX(), firstPathEnding.getY());
            if (!board.isOnBoard(lastPathEnding)) {
                lastPathEnding = board.clampInBoard(lastPathEnding);
            } else if (!board.isPassable(lastPathEnding)) {
                return new ArrayList<>();
            }
            allPathEndings.add(lastPathEnding);
        }
        return allPathEndings;
    }

    /**
     * gets a sub board with all the impassible locations of each sub board defined by the path endings
     *
     * @param mainBoard   the complete board of the puzzle
     * @param pathEndings the list of all path endings
     * @return a sub board
     */
    private Board getSubBoard(@NonNull Board mainBoard, @NonNull List<Position> pathEndings) {
        assert pathEndings.size() > 0;
        Board subBoard = new Board(new IntegerArray2D(pathEndings.get(0).getX() + 1, pathEndings.get(0).getY() + 1, 0), false);
        Position p1 = Position.ORIGIN;
        for (Position p2 : pathEndings) {
            subBoard = subBoard.stackBoard(mainBoard.getSubBoard(p1, p2));
            p1 = p2;
        }
        return subBoard;
    }

    /**
     * does the subboard have a solution
     *
     * @param subBoard the sub board
     * @return true if the subboard can be solved
     */
    private boolean isSolvable(Board subBoard) {
        Position bottomRightPos = new Position(subBoard.getWidth() - 1, subBoard.getHeight() - 1);
        return subBoard.isPassable(Position.ORIGIN) && subBoard.isPassable(bottomRightPos);
    }
}
