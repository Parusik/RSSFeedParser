package main.feed;


import main.feed.DBwork.DBFeedMessage;
import main.feed.DBwork.FeedDataStore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class Application {
    public static void main(String[] args) throws Exception {
        FeedDataStore.getInstance().refreshNews();
        SpringApplication.run(Application.class, args);
    }
}
