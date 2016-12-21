package xavier.rasschaert.hacker.org;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import xavier.rasschaert.hacker.org.model.Board;
import xavier.rasschaert.hacker.org.model.Puzzle;

import java.text.ParseException;

@Component
public class PuzzleParser {

    public Puzzle parsePuzzle(String flashVars) throws ParseException {
        String[] parameterAndValues = flashVars.split("&");
        if (parameterAndValues.length != PUZZLE_PARAM.values().length) {
            throw new ParseException(String.format("Expected [%s] values in the param FlashVars, actual values was [%s]", PUZZLE_PARAM.values().length, parameterAndValues.length), 0);
        }

        int level = (safeIntegerConversion(parseParameterValue(parameterAndValues[PUZZLE_PARAM.FV_LEVEL.ordinal()])));
        int minMoves = (safeIntegerConversion(parseParameterValue(parameterAndValues[PUZZLE_PARAM.FV_INS_MIN.ordinal()])));
        int maxMoves = (safeIntegerConversion(parseParameterValue(parameterAndValues[PUZZLE_PARAM.FV_INS_MAX.ordinal()])));
        int boardWidth = safeIntegerConversion(parseParameterValue(parameterAndValues[PUZZLE_PARAM.FV_BOARD_X.ordinal()]));
        int boardHeight = safeIntegerConversion(parseParameterValue(parameterAndValues[PUZZLE_PARAM.FV_BOARD_Y.ordinal()]));
        String terrainString = parseParameterValue(parameterAndValues[PUZZLE_PARAM.FV_TERRAIN_STRING.ordinal()]);
        Board board = parseMap(terrainString, boardWidth, boardHeight);
        return new Puzzle(level, minMoves, maxMoves, board);
    }

    private int safeIntegerConversion(String number) throws ParseException {
        try {
            return Integer.valueOf(number);
        } catch (NumberFormatException e) {
            throw new ParseException(String.format("Could not convert [%s] to an integer", number), 0);
        }
    }

    private String parseParameterValue(String parameterAndValue) throws ParseException {
        String[] pv = StringUtils.split(parameterAndValue, '=');
        if (pv.length != 2) {
            throw new ParseException(String.format("The input [%s] was not of the format 'X=Y'.", parameterAndValue), 0);
        }
        return pv[1];
    }

    private Board parseMap(String terrainString, int boardWidth, int boardHeight) throws ParseException {
        int[][] values = new int[boardWidth][boardHeight];
        if (terrainString.length() != boardWidth * boardHeight) {
            throw new ParseException("The terrain size is not consistent with the board width and height.", 0);
        }
        String[] rows = splitToEqualChunks(terrainString, boardWidth);
        for (int i = 0; i < rows.length; i++) {
            values[i] = parseRow(rows[i]);
        }
        return new Board(values);
    }

    private int[] parseRow(String terrainRow) {
        int[] terrain = new int[terrainRow.length()];
        for (int i = 0; i < terrain.length; i++) {
            terrain[i] = terrainRow.charAt(i) == 'X' ? 1 : 0;
        }
        return terrain;
    }

    private String[] splitToEqualChunks(String text, int charsInChunks) {
        int len = text.length();
        String[] chunks = new String[(int) Math.ceil(len * 1.0 / charsInChunks)];
        for (int i = 0, j = 0; i < len; i += charsInChunks, j++) {
            chunks[j] = text.substring(i, Math.min(len, i + charsInChunks));
        }
        return chunks;
    }

    private enum PUZZLE_PARAM {
        FV_TERRAIN_STRING,
        FV_INS_MAX,
        FV_INS_MIN,
        FV_BOARD_X,
        FV_BOARD_Y,
        FV_LEVEL
    }
}
