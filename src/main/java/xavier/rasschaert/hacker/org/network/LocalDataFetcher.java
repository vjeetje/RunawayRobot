package xavier.rasschaert.hacker.org.network;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import xavier.rasschaert.hacker.org.model.Board;
import xavier.rasschaert.hacker.org.model.Puzzle;

import java.io.IOException;
import java.text.ParseException;

@Component
@Profile("local")
public class LocalDataFetcher implements DataFetcher {
    @Override
    public Puzzle submitPuzzle(String path) throws IOException, ParseException {
        return getPuzzle();
    }

    @Override
    public Puzzle receivePuzzle(int level) throws IOException, ParseException {
        return getPuzzle();
    }

    private Puzzle getPuzzle() {
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
