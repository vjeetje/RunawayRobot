package xavier.rasschaert.hacker.org.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import xavier.rasschaert.hacker.org.network.DataFetcher;
import xavier.rasschaert.hacker.org.network.HackerOrgDataFetcher;

@Configuration
@Profile("live")
public class LiveConfiguration {
    @Bean
    public DataFetcher dataFetcher() {
        return new HackerOrgDataFetcher();
    }
}
