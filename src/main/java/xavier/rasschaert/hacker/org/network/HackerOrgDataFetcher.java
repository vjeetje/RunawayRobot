package xavier.rasschaert.hacker.org.network;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import xavier.rasschaert.hacker.org.PuzzleParser;
import xavier.rasschaert.hacker.org.model.Puzzle;
import xavier.rasschaert.hacker.org.properties.UrlProperties;

import javax.inject.Inject;
import java.io.IOException;
import java.text.ParseException;

@Component
@Profile("live")
@RequiredArgsConstructor(onConstructor = @__(@Inject))
public class HackerOrgDataFetcher implements DataFetcher {
    @NonNull
    private final UrlProperties urlProperties;
    @NonNull
    private final PuzzleParser puzzleParser;

    @Override
    public Puzzle submitPuzzle(String path) throws IOException, ParseException {
        Document doc = Jsoup.connect(urlProperties.getSubmitSolution(path)).get();
        return getPuzzle(doc);
    }

    @Override
    public Puzzle receivePuzzle(int level) throws IOException, ParseException {
        Document doc = Jsoup.connect(urlProperties.getGoToLevel(level)).get();
        return getPuzzle(doc);
    }

    private Puzzle getPuzzle(Document doc) throws ParseException {
        Element el = doc.select("object>param[name=FlashVars]").first();
        if (el == null) {
            throw new ParseException("No object with param FlashVars was found.", 0);
        } else {
            return puzzleParser.parsePuzzle(el.val());
        }
    }
}
