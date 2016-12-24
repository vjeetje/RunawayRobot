package xavier.rasschaert.hacker.org;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
public class RunawayRobotApplication {
    public static void main(String[] args) {
        SpringApplication.run(RunawayRobotApplication.class, args);
    }
}
