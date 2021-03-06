package main.feed.DBwork;

import main.feed.ForParse.Message;
import org.bson.types.ObjectId;

import org.mongodb.morphia.annotations.Id;
import java.util.Date;
import java.util.HashMap;
public class DBFeedMessage  {
    @Id
    private ObjectId id;
    private String title;
    private String link;
    private String description;
    private String author;
    private Integer count = 0;

    private String name;
    private Date myDate;
    private HashMap<String,Integer> words;

    public DBFeedMessage() {super(); }

    public DBFeedMessage( String title, String link, String description, String author ){
        this.author = author;
        this.description = description;
        this.link = link;
        this.title = title;
        this.myDate = new Date();
    }

    public DBFeedMessage(Message message) {
        this.author = message.getAuthor();
        this.description = message.getDescription();
        this.link = message.getLink();
        this.title = message.getTitle();
        this.myDate = new Date();
    }

    //title
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    //description
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    //link
    public String getLink() {
        return link;
    }
    public void setLink(String link) {
        this.link = link;
    }

    //author
    public String getAuthor() {
        return author;
    }
    public void setAuthor(String author) {
        this.author = author;
    }

    //date
    public Date getPubDate() {
        return myDate;
    }

    //count
    public  void setWordEntryCount(String word) {
        if (words != null) {
            this.count = words.get(word);
        }
    }

    //indexing
    public DBFeedMessage index() {
        try {
            this.words = MorphologicServise.IndexFeed(this.description + this.title );
        } catch (Exception e) {
            this.words = new HashMap<>();
            e.printStackTrace();
        }

        return this;
    }

    @Override
    public String toString() {
        return "FeedMessage [title=" + title + ", description=" + description
                + ", link=" + link + ", author=" + author + "]";
    }
}
