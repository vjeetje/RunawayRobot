package xavier.rasschaert.hacker.org.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "runawayRobot.url")
public class UrlProperties {
    /**
     * Url to receive the puzzle of the current level
     * Requires no arguments
     */
    private String requestPuzzle;

    /**
     * Url to submit the solution to the puzzle of the current level
     * arg1: the path of the puzzle solution
     */
    private String submitSolution;

    /**
     * Url to change the current level of the puzzle
     * arg1: the level of the puzzle
     */
    private String goToLevel;

    public String getSubmitSolution(String path) {
        return String.format(submitSolution, path);
    }

    public String getGoToLevel(int level) {
        return String.format(goToLevel, level);
    }
}
