package xavier.rasschaert.hacker.org.network;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;
import xavier.rasschaert.hacker.org.PuzzleParser;
import xavier.rasschaert.hacker.org.model.Puzzle;
import xavier.rasschaert.hacker.org.properties.UrlProperties;

import javax.inject.Inject;
import java.io.IOException;
import java.text.ParseException;

public class HackerOrgDataFetcher implements DataFetcher {
    @Inject
    private UrlProperties urlProperties;
    @Inject
    private PuzzleParser puzzleParser;

    @Override
    public boolean submitPuzzle(String path) throws IOException {
        Document doc = Jsoup.connect(urlProperties.getSubmitSolutionUrl(path)).get();
        //Elements newsHeadlines = doc.select("#mp-itn b a");
        throw new NotImplementedException();
    }

    @Override
    public void goToLevel(int level) throws IOException {
        throw new NotImplementedException();
    }

    @Override
    public Puzzle receivePuzzle() throws IOException, ParseException {
        Document doc = Jsoup.connect(urlProperties.getRequestPuzzle()).get();
        Element flashVarsElement = doc.select("object>param[name=FlashVars]").first();
        if (flashVarsElement == null) {
            throw new ParseException("No object with param FlashVars was found.", 0);
        }
        return puzzleParser.parsePuzzle(flashVarsElement.val());
    }
}
