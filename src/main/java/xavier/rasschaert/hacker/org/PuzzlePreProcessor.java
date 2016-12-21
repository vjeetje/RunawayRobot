package xavier.rasschaert.hacker.org;

import org.springframework.stereotype.Component;
import xavier.rasschaert.hacker.org.model.Board;
import xavier.rasschaert.hacker.org.model.Position;
import xavier.rasschaert.hacker.org.model.Puzzle;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class PuzzlePreProcessor {

    public void preprocess(Puzzle puzzle) {
        Board board = puzzle.getBoard();
        Set<Position> unprocessedPositions = board.getSafePositions();
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
                if (canBecomeImpassible(board, neighbours)) {
                    board.markAsImpassable(pos);
                    unprocessedPositions.addAll(getPassableNeighbours(board, neighbours));
                }
            }
        }
    }

    private boolean canBecomeImpassible(Board board, List<Optional<Position>> neighbours) {
        Optional<Position> top = neighbours.get(0);
        Optional<Position> right = neighbours.get(1);
        Optional<Position> bottom = neighbours.get(2);
        Optional<Position> left = neighbours.get(3);

        boolean topBlocks = !top.isPresent() || !board.isPassable(top.get());
        boolean rightBlocks = right.isPresent() && !board.isPassable(right.get());
        boolean bottomBlocks = bottom.isPresent() && !board.isPassable(bottom.get());
        boolean leftBlocks = !left.isPresent() || !board.isPassable(left.get());

        return (leftBlocks && topBlocks && (top.isPresent() || left.isPresent())) || (rightBlocks && bottomBlocks);
    }

    private Set<Position> getPassableNeighbours(Board board, List<Optional<Position>> neighbours) {
        return neighbours.stream()
                .filter(Optional::isPresent)
                .map(Optional::get)
                .filter(board::isPassable)
                .collect(Collectors.toSet());
    }
}
