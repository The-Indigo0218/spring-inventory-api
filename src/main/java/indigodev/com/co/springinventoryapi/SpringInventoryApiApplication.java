package indigodev.com.co.springinventoryapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class SpringInventoryApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringInventoryApiApplication.class, args);
    }

}
