package xavier.rasschaert.hacker.org.model;

import lombok.Getter;
import lombok.NonNull;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Getter
public class Board {
    public static final int PASSABLE_LOCATION = 0;
    public static final int IMPASSABLE_LOCATION = 1;

    /**
     * terrain with integer information:
     * 0: robot can use this location
     * >0: robot cannot use this location
     */
    private final IntegerArray2D terrain;

    /**
     * number of squares on the horizontal axis
     */
    private final int width;

    /**
     * number of squares on the vertical axis
     */
    private final int height;

    /**
     * Sets the terrain as the Board.
     *
     * @param terrain            terrain
     * @param addFinishLocations adds an extra column and row of passable locations if true.
     *                           These are the green locations in the original puzzle.
     */
    public Board(@NonNull IntegerArray2D terrain, boolean addFinishLocations) {
        if (addFinishLocations) {
            int w = terrain.getWidth() + 1;
            int h = terrain.getHeight() + 1;
            this.terrain = new IntegerArray2D(w, h, PASSABLE_LOCATION).add(terrain);
        } else {
            this.terrain = terrain.clone();
        }
        this.width = this.terrain.getWidth();
        this.height = this.terrain.getHeight();
    }

    @Override
    public boolean equals(Object object) {
        if (object != null && object instanceof Board) {
            // todo check for size first!
            return IntStream.range(0, getWidth() - 1)
                    .allMatch(i -> IntStream.range(0, getHeight() - 1)
                            .allMatch(j -> isPassable(new Position(i, j)) == ((Board) object).isPassable(new Position(i, j))));
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        return terrain.toString(val -> val == PASSABLE_LOCATION ? "." : "X", " ");
    }

    public boolean isPassable(@NonNull Position p) {
        return terrain.get(p.getX(), p.getY()) == PASSABLE_LOCATION;
    }

    public Set<Position> getPassableLocations() {
        return IntStream.range(0, getWidth())
                .mapToObj(i -> IntStream.range(0, getHeight())
                        .mapToObj(j -> new Position(i, j))
                        .filter(this::isPassable)
                        .collect(Collectors.toSet()))
                .flatMap(Set::stream)
                .collect(Collectors.toSet());
    }

    public boolean isOnBoard(@NonNull Position p) {
        return terrain.contains(p.getX(), p.getY());
    }

    /**
     * is p a finish location (marked as green in the orginal puzzle)
     *
     * @param p the position
     * @return true if p in the right most column or lowest row
     */
    public boolean isOnFinishLocation(@NonNull Position p) {
        return p.getX() == width - 1 || p.getY() == height - 1;
    }

    /**
     * get the sub board with p1 as top left corner and p2 as bottom right corner
     *
     * @param p1 the top left position
     * @param p2 the bottom right position
     * @return the sub board
     */
    public Board getSubBoard(@NonNull Position p1, @NonNull Position p2) {
        return new Board(terrain.slice(p1.getX(), p1.getY(), p2.getX(), p2.getY()), false);
    }

    /**
     * Adds a board on top of the current board
     *
     * @param board a board with the same dimensions
     * @return a board with the impassible locations of both boards
     */
    public Board stackBoard(@NonNull Board board) {
        return new Board(terrain.add(board.getTerrain()), false);
    }

    public void markAsImpassable(@NonNull Position p) {
        terrain.set(p.getX(), p.getY(), IMPASSABLE_LOCATION);
    }

    /**
     * puts a position to its closest match on the board.
     *
     * @param p the position
     * @return p if p is on board, else find the closest position
     */
    public Position clampInBoard(@NonNull Position p) {
        int x = Math.max(0, Math.min(width - 1, p.getX()));
        int y = Math.max(0, Math.min(height - 1, p.getY()));
        return new Position(x, y);
    }

    /**
     * get the 4 neighbours in clockwise order starting from the top neighbour
     *
     * @return the neighbours of the position
     */
    public List<Optional<Position>> getNeighbours(@NonNull Position p) {
        return Arrays.asList(getTopNeighbour(p), getRightNeighbour(p), getBottomNeighbour(p), getLeftNeighbour(p));
    }

    public Optional<Position> getLeftNeighbour(@NonNull Position p) {
        return getNeighbour(p, Position::getLeftNeighbour);
    }

    public Optional<Position> getRightNeighbour(@NonNull Position p) {
        return getNeighbour(p, Position::getRightNeighbour);
    }

    public Optional<Position> getTopNeighbour(@NonNull Position p) {
        return getNeighbour(p, Position::getTopNeighbour);
    }

    public Optional<Position> getBottomNeighbour(@NonNull Position p) {
        return getNeighbour(p, Position::getBottomNeighbour);
    }

    private Optional<Position> getNeighbour(@NonNull Position p, Function<Position, Position> neighbourFunction) {
        Position neighbour = neighbourFunction.apply(p);
        return Optional.ofNullable(isOnBoard(neighbour) ? neighbour : null);
    }
}