package project.webshop;

import org.jsondoc.spring.boot.starter.EnableJSONDoc;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

@SpringBootApplication
@EnableRedisHttpSession(maxInactiveIntervalInSeconds = 5 * 60)
@EnableJSONDoc
public class WebshopApplication {
	public final static String SESSION_KEY = "webshop_key";

	public static void main(String[] args) {
		SpringApplication.run(WebshopApplication.class, args);
	}
}
