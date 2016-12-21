package xavier.rasschaert.hacker.org.network;

import xavier.rasschaert.hacker.org.model.Board;
import xavier.rasschaert.hacker.org.model.Puzzle;

import java.io.IOException;
import java.text.ParseException;

public class LocalDataFetcher implements DataFetcher {
    @Override
    public boolean submitPuzzle(String path) throws IOException {
        return true;
    }

    @Override
    public void goToLevel(int level) throws IOException {

    }

    @Override
    public Puzzle receivePuzzle() throws IOException, ParseException {
        int[][] terrain = {
                {0, 0, 0, 0, 1, 1, 0, 1},
                {0, 0, 0, 0, 0, 1, 0, 0},
                {0, 1, 0, 0, 0, 0, 0, 1},
                {0, 0, 0, 1, 0, 0, 1, 0},
                {0, 0, 0, 0, 0, 0, 0, 1},
                {1, 0, 1, 0, 0, 0, 0, 0},
                {0, 1, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0}
        };
        return new Puzzle(10, 3, 5, new Board(terrain));
    }
}
