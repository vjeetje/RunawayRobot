package xavier.rasschaert.hacker.org.network;

import xavier.rasschaert.hacker.org.model.Puzzle;

import java.io.IOException;
import java.text.ParseException;

public interface DataFetcher {
    /**
     * submit the solution for the current puzzle
     *
     * @param path the path the robot will repeat to solve the puzzle
     * @return true if the solution was accepted
     * @throws IOException on connection problem
     */
    boolean submitPuzzle(String path) throws IOException;

    /**
     * change the level of the puzzle to solve
     *
     * @param level the level of the puzzle
     * @throws IOException on connection problem
     */
    void goToLevel(int level) throws IOException;

    /**
     * request the {@link Puzzle puzzle} of the current level
     *
     * @return the {@link Puzzle}
     * @throws IOException    on connection problem
     * @throws ParseException on malformed response
     */
    Puzzle receivePuzzle() throws IOException, ParseException;
}
