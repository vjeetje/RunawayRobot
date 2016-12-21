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
    /**
     * The x-coordinate
     */
    private int x;

    /**
     * The y-coordinate
     */
    private int y;

    /**
     * get the 4 neighbours in clockwise order starting from the top neighbour
     *
     * @return
     */
    public Position[] getNeighbours() {
        return new Position[]{
                getTopNeighbour(),
                getRightNeighbour(),
                getBottomNeighbour(),
                getLeftNeighbour()
        };
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
}
