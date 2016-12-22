package xavier.rasschaert.hacker.org;

import org.springframework.stereotype.Component;
import xavier.rasschaert.hacker.org.model.Board;
import xavier.rasschaert.hacker.org.model.Puzzle;

import java.text.ParseException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Component
public class PuzzleParser {

    private static final String REGEXP_VALID_PUZZLE = "^FVterrainString=([\\.X]*)&FVinsMax=([\\d]*)&FVinsMin=([\\d]*)&FVboardX=([\\d]*)&FVboardY=([\\d]*)&FVlevel=([\\d]*)$";

    private static final char IMPASSABLE_LOCATION = 'X';

    private static final int INDEX_TERRAIN = 0;
    private static final int INDEX_MAX_MOVES = 1;
    private static final int INDEX_MIN_MOVES = 2;
    private static final int INDEX_BOARD_WIDTH = 3;
    private static final int INDEX_BOARD_HEIGHT = 4;
    private static final int INDEX_LEVEL = 5;

    public Puzzle parsePuzzle(String rawPuzzle) throws ParseException {
        List<String> parameters = parseParameters(rawPuzzle);
        int level = Integer.valueOf(parameters.get(INDEX_LEVEL));
        int minMoves = Integer.valueOf(parameters.get(INDEX_MIN_MOVES));
        int maxMoves = Integer.valueOf(parameters.get(INDEX_MAX_MOVES));
        int boardWidth = Integer.valueOf(parameters.get(INDEX_BOARD_WIDTH));
        int boardHeight = Integer.valueOf(parameters.get(INDEX_BOARD_HEIGHT));
        String terrain = parameters.get(INDEX_TERRAIN);

        Board board = parseMap(terrain, boardWidth, boardHeight);
        return new Puzzle(level, minMoves, maxMoves, board);
    }

    private List<String> parseParameters(String rawPuzzle) throws ParseException {
        Pattern pattern = Pattern.compile(REGEXP_VALID_PUZZLE);
        Matcher matcher = pattern.matcher(rawPuzzle);
        if (matcher.matches()) {
            return IntStream.rangeClosed(1, 6).mapToObj(matcher::group).collect(Collectors.toList());
        } else {
            throw new ParseException(String.format("Not a valid puzzle: [%s]", rawPuzzle), 0);
        }
    }

    private Board parseMap(String terrainString, int boardWidth, int boardHeight) throws ParseException {
        int[][] values = IntStream.range(0, boardWidth)
                .mapToObj(x -> IntStream.range(0, boardHeight)
                        .map(y -> terrainString.charAt(x + y * boardWidth) == IMPASSABLE_LOCATION ? 1 : 0)
                        .toArray())
                .toArray(int[][]::new);
        return new Board(values);
    }
}
