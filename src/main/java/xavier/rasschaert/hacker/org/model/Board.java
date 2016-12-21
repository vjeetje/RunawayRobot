package xavier.rasschaert.hacker.org.model;

import lombok.Getter;
import org.apache.commons.lang3.Range;

import java.util.*;
import java.util.function.Function;

@Getter
public class Board {
    public static final int PASSABLE_LOCATION = 0;
    public static final int IMPASSABLE_LOCATION = 1;

    /**
     * terrain with integer information:
     * 0: robot can use this location
     * >0: robot cannot use this location
     */
    private IntegerArray2D terrain;

    /**
     * number of squares on the horizontal axis
     */
    private int width;

    /**
     * number of squares on the vertical axis
     */
    private int height;

    public Board(int[][] terrain) {
        this.terrain = new IntegerArray2D(terrain);
        this.width = this.terrain.getWidth();
        this.height = this.terrain.getHeight();
    }

    @Override
    public String toString() {
        return terrain.pprint(val -> val == 0 ? "." : "X", " ");
    }

    public boolean isPassable(Position p) {
        return terrain.get(p.getX(), p.getY()) == 0;
    }

    public Set<Position> getSafePositions() {
        Set<Position> safePositions = new HashSet<>();
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                if (isPassable(new Position(i, j))) {
                    safePositions.add(new Position(i, j));
                }
            }
        }
        return safePositions;
    }

    public boolean isOnBoard(Position p) {
        return Range.between(0, width - 1).contains(p.getX()) && Range.between(0, height - 1).contains(p.getY());
    }

    public void markAsImpassable(Position p) {
        terrain.set(p.getX(), p.getY(), IMPASSABLE_LOCATION);
    }

    /**
     * get the 4 neighbours in clockwise order starting from the top neighbour
     *
     * @return
     */
    public List<Optional<Position>> getNeighbours(Position p) {
        return Arrays.asList(getTopNeighbour(p), getRightNeighbour(p), getBottomNeighbour(p), getLeftNeighbour(p));
    }

    public Optional<Position> getLeftNeighbour(Position p) {
        return getNeighbour(p, Position::getLeftNeighbour);
    }

    public Optional<Position> getRightNeighbour(Position p) {
        return getNeighbour(p, Position::getRightNeighbour);
    }

    public Optional<Position> getTopNeighbour(Position p) {
        return getNeighbour(p, Position::getTopNeighbour);
    }

    public Optional<Position> getBottomNeighbour(Position p) {
        return getNeighbour(p, Position::getBottomNeighbour);
    }

    private Optional<Position> getNeighbour(Position p, Function<Position, Position> neighbourFunction) {
        if (p == null) {
            return Optional.empty();
        }

        Position neighbour = neighbourFunction.apply(p);
        return Optional.ofNullable(isOnBoard(neighbour) ? neighbour : null);
    }
}
