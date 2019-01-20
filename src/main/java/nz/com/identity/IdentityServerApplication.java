package nz.com.identity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.client.RestTemplateAutoConfiguration;
import org.springframework.boot.autoconfigure.websocket.reactive.WebSocketReactiveAutoConfiguration;
import org.springframework.boot.autoconfigure.websocket.servlet.WebSocketServletAutoConfiguration;

@SpringBootApplication(exclude = {RestTemplateAutoConfiguration.class,
		WebSocketReactiveAutoConfiguration.class, WebSocketServletAutoConfiguration.class})
public class IdentityServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(IdentityServerApplication.class, args);
	}
}
