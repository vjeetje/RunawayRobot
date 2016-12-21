package xavier.rasschaert.hacker.org;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "runaway-robot", ignoreUnknownFields = false)
public class RunawayRobotProperties {
    private Url url;
    private Progress progress;

    public Url getUrl() {
        return url;
    }

    public void setUrl(Url url) {
        this.url = url;
    }

    public Progress getProgress() {
        return progress;
    }

    public void setProgress(Progress progress) {
        this.progress = progress;
    }

    @Getter
    @Setter
    public static class Url {

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

        public String getSubmitSolutionUrl(String path) {
            return String.format(submitSolution, path);
        }

        public String getGoToLevelUrl(int level) {
            return String.format(goToLevel, level);
        }
    }

    @Getter
    @Setter
    public static class Progress {
        /**
         * The level of the first puzzle to solve
         */
        private int level;
    }
}
