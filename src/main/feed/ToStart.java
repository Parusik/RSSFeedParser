package main.feed;

import java.util.*;

/**
 * Created by Оля on 02.06.2017.
 */
public class ToStart {
    public static void start(){
        FileWork fromFile = new FileWork("C:\\Users\\Оля\\IdeaProjects\\FEEDS\\src\\main\\feed\\rss.txt");
        WorkWithXML xml = new WorkWithXML();
        List<String> rss = fromFile.getListFromFile();
        for(int i=0; i<rss.size();i++)
            xml.start(rss.get(i));
    }
}
