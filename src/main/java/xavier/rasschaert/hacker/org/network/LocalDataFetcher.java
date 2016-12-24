package xavier.rasschaert.hacker.org.network;

import lombok.NonNull;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import xavier.rasschaert.hacker.org.exception.PuzzleParseException;
import xavier.rasschaert.hacker.org.model.Board;
import xavier.rasschaert.hacker.org.model.IntegerArray2D;
import xavier.rasschaert.hacker.org.model.Puzzle;

import java.io.IOException;

@Component
@Profile("local")
public class LocalDataFetcher implements DataFetcher {
    @Override
    public Puzzle submitPuzzle(@NonNull String path) throws IOException, PuzzleParseException {
        return getPuzzle();
    }

    @Override
    public Puzzle receivePuzzle(int level) throws IOException, PuzzleParseException {
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
        return new Puzzle(10, 3, 5, new Board(new IntegerArray2D(terrain), true));
    }
}
