package main.feed;

/**
 * Created by Оля on 02.06.2017.
 */

import org.w3c.dom.*;
import org.xml.sax.*;
import java.io.*;
import javax.xml.parsers.*;
import java.io.IOException;
import java.net.*;
import java.util.*;

public class WorkWithXML {

    String channel="";

    public List<Message> start(String adress)
    {

        List<Message> messages = new ArrayList<>();

        URL url = null;
        URLConnection connection = null;
        Document doc = null;
        System.out.println(adress);
        try {
            url = new URL(adress);
            connection = url.openConnection();
            doc = parseXML(connection.getInputStream());
            connection = url.openConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }

        Node chan = doc.getElementsByTagName("channel").item(0);
        Element uniq = (Element)chan;
        channel = uniq.getElementsByTagName("title").item(0).getTextContent().replace(" ","").replace("|","") + ".txt";

        try {
            getStringOfXML(new BufferedReader(new InputStreamReader(connection.getInputStream())));
        } catch (IOException e) {
            e.printStackTrace();
        }

        NodeList itemNodes = doc.getElementsByTagName("item");
        for(int i=0; i<itemNodes.getLength();i++)
        {
            Message message = new Message();
            Node nNode = itemNodes.item(i);
            Element eElement = (Element) nNode;
            try {
                message.setAuthor(eElement.getElementsByTagName("author").item(0).getTextContent());
                message.setLink(eElement.getElementsByTagName("link").item(0).getTextContent());
                message.setDescription(eElement.getElementsByTagName("description").item(0).getTextContent());
                message.setTitle(eElement.getElementsByTagName("title").item(0).getTextContent());
            }
            catch(Exception e){

        }
            messages.add(message);
        }
        return messages;
    }

    private void getStringOfXML(BufferedReader stream){
        String result = "";
        String inputLine;
        try {
            while ((inputLine = stream.readLine()) != null)
                result += inputLine+ " ";
        } catch (IOException e) {
            e.printStackTrace();
        }
        result = result.replace(" ","");
        System.out.println(channel);
        try{
            PrintWriter writer = new PrintWriter("C:\\Users\\Оля\\IdeaProjects\\FEEDS\\src\\main\\feed\\"+channel, "UTF-8");
            writer.println(result);
            writer.close();
        } catch (IOException e) {
            // do something
        }
    }

    private Document parseXML(InputStream stream)
            throws Exception
    {
        DocumentBuilderFactory objDocumentBuilderFactory = null;
        DocumentBuilder objDocumentBuilder = null;
        Document doc = null;
        try
        {
            objDocumentBuilderFactory = DocumentBuilderFactory.newInstance();
            objDocumentBuilder = objDocumentBuilderFactory.newDocumentBuilder();

            doc = objDocumentBuilder.parse(stream);
        }
        catch(Exception ex)
        {
            throw ex;
        }
        return doc;
    }
}
