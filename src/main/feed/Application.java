package main.feed;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@SpringBootApplication
public class Application {

    public static void main(String[] args) throws Exception {

        ToStart.start();
        SpringApplication.run(Application.class, args);
    }
}
