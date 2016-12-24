package xavier.rasschaert.hacker.org.solver;

import lombok.NonNull;
import org.springframework.stereotype.Component;
import xavier.rasschaert.hacker.org.model.Board;
import xavier.rasschaert.hacker.org.model.Position;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class PuzzlePreprocessor {
    /**
     * Mark positions that can never be reached as impassible.
     *
     * @param board the board
     * @Param isSubBoard is the board a sub board
     */
    public void preprocess(@NonNull Board board, boolean isSubBoard) {
        Set<Position> unprocessedPositions = board.getPassableLocations();
        while (!unprocessedPositions.isEmpty()) {
            Set<Position> processingPositions = new HashSet<>(unprocessedPositions);
            Iterator<Position> iterator = processingPositions.iterator();
            unprocessedPositions.clear();
            while (iterator.hasNext()) {
                Position pos = iterator.next();
                if (!board.isPassable(pos)) {
                    continue;
                }

                List<Optional<Position>> neighbours = board.getNeighbours(pos);
                if (canBecomeImpassible(board, neighbours, isSubBoard)) {
                    board.markAsImpassable(pos);
                    unprocessedPositions.addAll(getPassableNeighbours(board, neighbours));
                }
            }
        }
    }

    private boolean canBecomeImpassible(@NonNull Board board, @NonNull List<Optional<Position>> neighbours, boolean isSubBoard) {
        Optional<Position> top = neighbours.get(0);
        Optional<Position> right = neighbours.get(1);
        Optional<Position> bottom = neighbours.get(2);
        Optional<Position> left = neighbours.get(3);

        boolean topBlocks = !top.isPresent() || !board.isPassable(top.get());
        boolean leftBlocks = !left.isPresent() || !board.isPassable(left.get());
        boolean rightBlocks, bottomBlocks;
        if (isSubBoard) {
            rightBlocks = !right.isPresent() || !board.isPassable(right.get());
            bottomBlocks = !bottom.isPresent() || !board.isPassable(bottom.get());
        } else {
            rightBlocks = right.isPresent() && !board.isPassable(right.get());
            bottomBlocks = bottom.isPresent() && !board.isPassable(bottom.get());
        }

        return (leftBlocks && topBlocks && (top.isPresent() || left.isPresent())) ||
                (rightBlocks && bottomBlocks && (bottom.isPresent() || right.isPresent()));
    }

    private Set<Position> getPassableNeighbours(@NonNull Board board, @NonNull List<Optional<Position>> neighbours) {
        return neighbours.stream()
                .filter(Optional::isPresent)
                .map(Optional::get)
                .filter(board::isPassable)
                .collect(Collectors.toSet());
    }
}
