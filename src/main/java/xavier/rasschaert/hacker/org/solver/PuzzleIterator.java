package xavier.rasschaert.hacker.org.solver;

import lombok.NonNull;
import xavier.rasschaert.hacker.org.model.Position;
import xavier.rasschaert.hacker.org.model.Puzzle;

import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class PuzzleIterator implements Iterator<Position> {
    /**
     * the puzzle to iterate over
     */
    private final Puzzle puzzle;

    /**
     * iterator over the positions determining the sub boards by their bottom right corner.
     * The sub board is defined by the top left corner of the puzzle and the position of the iterator.
     */
    private final Iterator<Position> iterator;

    public PuzzleIterator(@NonNull Puzzle puzzle) {
        this.puzzle = puzzle;
        iterator = getPassableLocations().iterator();
    }

    @Override
    public boolean hasNext() {
        return iterator.hasNext();
    }

    @Override
    public Position next() {
        return iterator.next();
    }

    /**
     * Get the positions of the bottom right corner of sub boards of the puzzle.
     *
     * @return a list of {@link Position positions}
     */
    private List<Position> getPassableLocations() {
        return IntStream.rangeClosed(puzzle.getMinMoves(), puzzle.getMaxMoves())
                .mapToObj(this::getPassableLocations)
                .flatMap(List::stream)
                .collect(Collectors.toList());
    }

    /**
     * Get the positions at a distance of d from the top left corner.
     *
     * @param d the distance from the top left corner
     * @return a list of {@link Position positions}
     */
    private List<Position> getPassableLocations(int d) {
        return IntStream.rangeClosed(0, d)
                .mapToObj(dx -> new Position(dx, d - dx))
                .filter(puzzle.getBoard()::isPassable)
                .collect(Collectors.toList());
    }
}
