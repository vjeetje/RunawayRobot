package xavier.rasschaert.hacker.org.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "runawayRobot.credentials")
public class CredentialsProperties {
    private Submit submit;

    @Getter
    @Setter
    public static class Submit {
        /**
         * The username
         */
        private String username;
        /**
         * The safe password for automated submission
         */
        private String safePassword;
    }
}
