package ac.uk.bristol.cs.santa.grottobooking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("file:${user.home}/.secret.properties")
@SpringBootApplication
public class GrottoBookingApplication {

    public static void main(String[] args) {
        SpringApplication.run(GrottoBookingApplication.class, args);
    }
}
