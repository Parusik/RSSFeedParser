package main.feed;


import main.feed.DBwork.DBFeedMessage;
import main.feed.DBwork.FeedDataStore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@SpringBootApplication
public class Application {
    public static void main(String[] args) throws Exception {
        FeedDataStore.getInstance().refreshNews();
        FeedDataStore.getInstance().saveFeedMessage(new DBFeedMessage("k","t444"+Math.random(),"relating to or concerned with the morphology of plants and animals","t").index());
        ToStart.start();
        SpringApplication.run(Application.class, args);
    }
}
