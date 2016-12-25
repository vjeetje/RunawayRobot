package xavier.rasschaert.hacker.org.network;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import xavier.rasschaert.hacker.org.exception.PuzzleParseException;
import xavier.rasschaert.hacker.org.model.Puzzle;
import xavier.rasschaert.hacker.org.properties.UrlProperties;

import javax.inject.Inject;
import java.io.IOException;

@Component
@Profile("live")
@RequiredArgsConstructor(onConstructor = @__(@Inject))
public class HackerOrgDataFetcher implements DataFetcher {
    @NonNull
    private final UrlProperties urlProperties;
    @NonNull
    private final PuzzleParser puzzleParser;

    @Override
    public Puzzle submitPuzzle(@NonNull String path) throws IOException, PuzzleParseException {
        Document doc = Jsoup.connect(urlProperties.getSubmitSolution(path)).get();
        return puzzleParser.parsePuzzle(doc);
    }

    @Override
    public Puzzle receivePuzzle(int level) throws IOException, PuzzleParseException {
        Document doc = Jsoup.connect(urlProperties.getGoToLevel(level)).get();
        return puzzleParser.parsePuzzle(doc);
    }
}
