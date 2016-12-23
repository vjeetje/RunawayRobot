package xavier.rasschaert.hacker.org.network;

import xavier.rasschaert.hacker.org.model.Puzzle;

import java.io.IOException;
import java.text.ParseException;

public interface DataFetcher {
    /**
     * submit the solution for the current puzzle
     *
     * @param path the path the robot will repeat to solve the puzzle
     * @return the next {@link Puzzle} to solve
     * @throws IOException on connection problem
     * @throws ParseException on exception parsing puzzle
     */
    Puzzle submitPuzzle(String path) throws IOException, ParseException;

    /**
     * change the level of the puzzle to solve
     * and return the puzzle of that level
     *
     * @param level the level of the puzzle
     * @return the {@link Puzzle} of the given level
     * @throws IOException on connection problem
     * @throws ParseException on exception parsing puzzle
     */
    Puzzle receivePuzzle(int level) throws IOException, ParseException;
}
