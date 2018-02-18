package project.webshop.config.jobs;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableScheduling
@EnableAsync
public class DailyCronJob {

    @Scheduled(fixedRate=5000)
    public void printMessage() {
        System.out.println("I am called by Spring scheduler");
    }

    @Scheduled(fixedDelay = 1000 * 60 * 60) // a cada hora
    public void reportCurrentTime()
    {

        // do something

    }


}
