package main.feed.ForParse;



/**
 * Created by Оля on 02.06.2017.
 */
public class Message {
    public String title;
    public String description;
    public String link;
    public String author;

    public void setTitle(String title){
        this.title = title;
    }

    public void setDescription(String description){
        this.description = description;
    }

    public void setLink(String link){
        this.link = link;
    }

    public void setAuthor(String author){
        this.author = author;
    }

    public String getTitle(){
        return this.title;
    }

    public String getDescription(){
        return this.description;
    }

    public String getLink(){
        return this.link;
    }

    public String getAuthor(){
        return this.author;
    }

    public boolean valid( ) {
        if (this.description == "" || this.title == "" || this.link == ""){
            return false;
        }
         return true;
    }
    @Override
    public String toString() {
        return "FeedMessage [title=" + title + ", description=" + description
                + ", link=" + link + ", author=" + author + "]";
    }
}
