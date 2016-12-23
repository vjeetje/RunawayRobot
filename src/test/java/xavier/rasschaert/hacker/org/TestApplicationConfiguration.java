package xavier.rasschaert.hacker.org;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(
        excludeFilters = {
                @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, value = CommandLineRunner.class),
                @ComponentScan.Filter(type = FilterType.ANNOTATION, value = SpringBootApplication.class)
        })
@EnableAutoConfiguration
public class TestApplicationConfiguration {
}