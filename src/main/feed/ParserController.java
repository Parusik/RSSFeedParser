package main.feed;

import main.feed.DBwork.DBFeedMessage;
import main.feed.DBwork.FeedDataStore;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;


@RestController
public class ParserController {

    @RequestMapping(value = "/getNews", method = RequestMethod.POST)
    public List<DBFeedMessage> parse(@RequestParam(value = "input", defaultValue = "") String input) throws Exception {
        try {
            return  FeedDataStore.getInstance().getFeedByKeword(input.trim());
        }
        catch (NoSuchElementException e) {
            return new ArrayList<>();
        }
    }
}