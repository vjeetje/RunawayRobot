package xavier.rasschaert.hacker.org.network;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;
import xavier.rasschaert.hacker.org.PuzzleParser;
import xavier.rasschaert.hacker.org.RunawayRobotProperties;
import xavier.rasschaert.hacker.org.model.Puzzle;

import javax.inject.Inject;
import java.io.IOException;
import java.text.ParseException;

public class HackerOrgDataFetcher implements DataFetcher {
    @Inject
    private RunawayRobotProperties runawayRobotProperties;
    @Inject
    private PuzzleParser puzzleParser;

    @Override
    public boolean submitPuzzle(String path) throws IOException {
        Document doc = Jsoup.connect(runawayRobotProperties.getUrl().getSubmitSolutionUrl(path)).get();
        //Elements newsHeadlines = doc.select("#mp-itn b a");
        throw new NotImplementedException();
    }

    @Override
    public void goToLevel(int level) throws IOException {
        throw new NotImplementedException();
    }

    @Override
    public Puzzle receivePuzzle() throws IOException, ParseException {
        Document doc = Jsoup.connect(runawayRobotProperties.getUrl().getRequestPuzzle()).get();
        Element flashVarsElement = doc.select("object>param[name=FlashVars]").first();
        if (flashVarsElement == null) {
            throw new ParseException("No object with param FlashVars was found.", 0);
        }
        if (StringUtils.isEmpty(flashVarsElement.val())) {
            throw new ParseException("The param FlashVars was empty", 0);
        }
        return puzzleParser.parsePuzzle(flashVarsElement.val());
    }
}
