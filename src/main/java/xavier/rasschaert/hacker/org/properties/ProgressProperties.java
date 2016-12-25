package xavier.rasschaert.hacker.org.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "runawayRobot.progress")
public class ProgressProperties {
    private Level level;

    @Getter
    @Setter
    public static class Level {
        /**
         * The level of the first puzzle to solve
         */
        private int min;

        /**
         * The level of the last puzzle to solve
         */
        private int max;
    }
}
