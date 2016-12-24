package xavier.rasschaert.hacker.org.solver;

import lombok.NonNull;
import org.springframework.stereotype.Component;
import xavier.rasschaert.hacker.org.model.Board;
import xavier.rasschaert.hacker.org.model.Position;

import java.util.Optional;

@Component
public class PuzzlePostprocessor {
    public static final String MOVE_RIGHT = "R";
    public static final String MOVE_DOWN = "D";

    /**
     * extract the path from the sub board
     *
     * @param board a sub board with a solution
     * @return a valid path
     */
    public String postprocess(@NonNull Board board) {
        Position p = Position.ORIGIN;
        StringBuilder sb = new StringBuilder();
        while (!new Position(board.getWidth() - 1, board.getHeight() - 1).equals(p)) {
            Optional<Position> r = board.getRightNeighbour(p);
            Optional<Position> d = board.getBottomNeighbour(p);
            if (r.isPresent() && board.isPassable(r.get())) {
                sb.append(MOVE_RIGHT);
                p = r.get();
            } else {
                assert d.isPresent();
                sb.append(MOVE_DOWN);
                p = d.get();
            }
        }
        return sb.toString();
    }
}
