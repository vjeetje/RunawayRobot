package xavier.rasschaert.hacker.org.network;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private static final Logger LOGGER = LoggerFactory.getLogger(HackerOrgDataFetcher.class);

    @NonNull
    private final UrlProperties urlProperties;
    @NonNull
    private final PuzzleParser puzzleParser;

    @Override
    public Puzzle submitPuzzle(@NonNull String path) throws IOException, PuzzleParseException {
        Document doc = Jsoup.connect(urlProperties.getSubmitSolution(path)).get();
        LOGGER.error(doc.toString());
        return getPuzzle(doc);
    }

    @Override
    public Puzzle receivePuzzle(int level) throws IOException, PuzzleParseException {
        Document doc = Jsoup.connect(urlProperties.getGoToLevel(level)).get();
        LOGGER.error(doc.toString());
        return getPuzzle(doc);
    }

    private Puzzle getPuzzle(@NonNull Document doc) throws PuzzleParseException {
        Element el = doc.select("object>param[name=FlashVars]").first();
        if (el == null) {
            LOGGER.error(doc.toString());
            throw new PuzzleParseException("No object with param FlashVars was found.");
        } else {
            return puzzleParser.parsePuzzle(el.val());
        }
    }
}
