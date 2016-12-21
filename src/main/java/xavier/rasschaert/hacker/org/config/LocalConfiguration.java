package xavier.rasschaert.hacker.org.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import xavier.rasschaert.hacker.org.network.DataFetcher;
import xavier.rasschaert.hacker.org.network.LocalDataFetcher;

@Configuration
@Profile("local")
public class LocalConfiguration {
    @Bean
    public DataFetcher dataFetcher() {
        return new LocalDataFetcher();
    }
}
