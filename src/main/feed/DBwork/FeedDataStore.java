package main.feed.DBwork;

import com.mongodb.MongoClient;
import main.feed.ForParse.FileWork;
import main.feed.ForParse.Message;
import main.feed.ForParse.WorkWithXML;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.Datastore;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class FeedDataStore {
    private static FeedDataStore ourInstance = new FeedDataStore();
    private static FeedDataStore instance ;
    private static Datastore dataStore;
    private Date  dateLast;

    private FeedDataStore() {
        Morphia morphia  = new Morphia();
        morphia.mapPackage("main.feed.DBwork");
        this.dataStore = morphia.createDatastore(new MongoClient(), "RSSparse");
        dataStore.ensureIndexes();

        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.add(Calendar.DAY_OF_YEAR, -1 * 3);
        this.dateLast = new Date(c.getTime().getYear(),c.getTime().getMonth(),c.getTime().getDate());
    }

    public static FeedDataStore getInstance() {
        if (instance ==null) {
            instance =  new FeedDataStore();
        }
        return instance;
    }

    public void saveFeedMessage(DBFeedMessage message) {
        DBFeedMessage exist = dataStore.createQuery(DBFeedMessage.class)
                .field("link")
                .equal(message.getLink())
                .get();
        if (exist == null )
            dataStore.save(message);
    }

    public void refreshNews(){
        dataStore.delete(dataStore.createQuery(DBFeedMessage.class).filter("myDate <=", dateLast));

        List<String> rss = FileWork.getListFromFile("C:\\Users\\Parus\\Desktop\\Новая папка\\RSSFeedParser\\src\\main\\feed\\ForParse\\rss.txt");
        WorkWithXML xml = new WorkWithXML();
        rss.forEach(s -> {
            List<DBFeedMessage> feed = new ArrayList<>();
            xml.start(s).forEach(message -> {
                if (message.valid())
                feed.add(new DBFeedMessage(message).index());
            });
            feed.forEach(this::saveFeedMessage);
        });
    }

    public List<DBFeedMessage> getFeedByKeword(String keyword) {
        final String key = keyword;
        keyword = "\"" + keyword + "\"";
        List<DBFeedMessage> news = dataStore.createQuery(DBFeedMessage.class)
                .field("words." + keyword.toLowerCase().trim())
                .exists()
                .order("-words." +  keyword.toLowerCase().trim())
                .asList();
        System.out.println(news);

        news.forEach(n -> n.setWordEntryCount(key));
       return news;
    }

}
