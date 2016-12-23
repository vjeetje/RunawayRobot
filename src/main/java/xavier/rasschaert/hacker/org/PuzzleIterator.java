package xavier.rasschaert.hacker.org;

import xavier.rasschaert.hacker.org.model.Position;
import xavier.rasschaert.hacker.org.model.Puzzle;

import java.util.Iterator;
import java.util.Set;
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

    public PuzzleIterator(Puzzle puzzle) {
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
     * Get the Positions of the bottom right corner of sub boards of the puzzle.
     *
     * @return a list of {@link Position positions}
     */
    private Set<Position> getPassableLocations() {
        return IntStream.rangeClosed(puzzle.getMinMoves(), puzzle.getMaxMoves())
                .mapToObj(this::getPassableLocations)
                .flatMap(Set::stream)
                .collect(Collectors.toSet());
    }

    /**
     * Get the Positions at a distance of d from the top left corner.
     *
     * @param d the distance from the top left corner
     * @return a list of {@link Position positions}
     */
    private Set<Position> getPassableLocations(int d) {
        return IntStream.rangeClosed(0, d)
                .mapToObj(dx -> new Position(dx, d - dx))
                .filter(puzzle.getBoard()::isPassable)
                .collect(Collectors.toSet());
    }
}
