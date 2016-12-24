package xavier.rasschaert.hacker.org.model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode
public class Position {
    public static final Position ORIGIN = new Position(0, 0);

    /**
     * The x-coordinate
     */
    private final int x;

    /**
     * The y-coordinate
     */
    private final int y;

    public Position add(int x, int y) {
        return new Position(this.x + x, this.y + y);
    }

    public Position getLeftNeighbour() {
        return new Position(x - 1, y);
    }

    public Position getRightNeighbour() {
        return new Position(x + 1, y);
    }

    public Position getTopNeighbour() {
        return new Position(x, y - 1);
    }

    public Position getBottomNeighbour() {
        return new Position(x, y + 1);
    }

    @Override
    public String toString() {
        return String.format("(%d, %d)", x, y);
    }
}
