package xavier.rasschaert.hacker.org.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Puzzle {
    /**
     * the level
     */
    private final int level;

    /**
     * the minimum number of instructions to loop
     */
    private final int minMoves;

    /**
     * the maximum number of instructions to loop
     */
    private final int maxMoves;

    /**
     * the board
     */
    private final Board board;

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Puzzle{");
        sb.append(System.lineSeparator());
        sb.append("level=").append(level).append(System.lineSeparator());
        sb.append("minMoves=").append(minMoves).append(System.lineSeparator());
        sb.append("maxMoves=").append(maxMoves).append(System.lineSeparator());
        sb.append("board=").append(System.lineSeparator());
        sb.append(board).append(System.lineSeparator());
        sb.append('}');
        return sb.toString();
    }
}
