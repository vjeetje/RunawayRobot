package xavier.rasschaert.hacker.org.model;

import lombok.Getter;
import lombok.NonNull;
import org.apache.commons.lang3.Range;

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
    private IntegerArray2D terrain;

    /**
     * number of squares on the horizontal axis
     */
    private int width;

    /**
     * number of squares on the vertical axis
     */
    private int height;

    public Board(@NonNull int[][] terrain) {
        this.terrain = new IntegerArray2D(terrain);
        this.width = this.terrain.getWidth();
        this.height = this.terrain.getHeight();
    }

    @Override
    public String toString() {
        return terrain.pprint(val -> val == 0 ? "." : "X", " ");
    }

    public boolean isPassable(@NonNull Position p) {
        return terrain.get(p.getX(), p.getY()) == 0;
    }

    public Set<Position> getSafePositions() {
        return IntStream.range(0, getWidth())
                .mapToObj(i -> IntStream.range(0, getHeight())
                        .mapToObj(j -> new Position(i, j))
                        .collect(Collectors.toSet()))
                .flatMap(Set::stream)
                .collect(Collectors.toSet());
    }

    public boolean isOnBoard(@NonNull Position p) {
        return Range.between(0, width - 1).contains(p.getX()) && Range.between(0, height - 1).contains(p.getY());
    }

    public void markAsImpassable(@NonNull Position p) {
        terrain.set(p.getX(), p.getY(), IMPASSABLE_LOCATION);
    }

    /**
     * get the 4 neighbours in clockwise order starting from the top neighbour
     *
     * @return
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
