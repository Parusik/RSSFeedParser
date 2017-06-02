package main.feed.DBwork;

import main.feed.ForParse.FileWork;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.springframework.http.HttpHeaders.USER_AGENT;

/**
 * Created by Parus on 02.06.2017.
 */
public class MorphologicServise {
    final static  private String link = "http://open.xerox.com/bus/op/fst-nlp-tools/PartOfSpeechTagging";
    public  static  HashMap<String,Integer> IndexFeed(String input) throws Exception{
        HashMap<String,Integer> words = new HashMap<>();
        HttpClient httpclient = HttpClients.createDefault();
        HttpPost httppost = new HttpPost(link);
        //Headers
        httppost.setHeader("User-Agent", USER_AGENT);
        // Request parameters and other properties.
        List<NameValuePair> params = new ArrayList<NameValuePair>(2);
        params.add(new BasicNameValuePair("inputtext",input));
        params.add(new BasicNameValuePair("language", "English"));
        httppost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
        //Execute and get the response.
        HttpResponse response = httpclient.execute(httppost);
        //sleep
        Thread.sleep(560);
        System.out.println("Response Code : " + response.getStatusLine().getStatusCode());
        HttpEntity entity = response.getEntity();
        if (entity != null) {
            InputStream instream = entity.getContent();
            JSONObject answer = new JSONObject(convertStreamToString(instream));
            try {
                if (!answer.has("PartOfSpeechTaggingResponse")){
                    throw new Exception();
                }
                final Pattern p = Pattern.compile("\"base-form\":\"\\w*\"");
                Matcher m = p.matcher(answer.toString());
                ArrayList<String> matches = new ArrayList<>();
                while (m.find()) {
                    if (m.group().length() > 16)
                        matches.add(m.group(0));
                }
                matches.forEach(ma -> {
                    String word  = ma.split(":")[1].replace("\"\"","");
                    if (words.containsKey(word)){
                        words.put(word,words.get(word) + 1);
                    }
                    else words.put(word,1);
                });
            }
            catch (Exception e){}
            finally {
                instream.close();
            }
        }
        List<String> stopwords = FileWork.getListFromFile("C:\\Users\\Parus\\Desktop\\Новая папка\\RSSFeedParser\\src\\main\\feed\\DBwork\\stop-words.txt");
        words.forEach((a,b)-> {
            if (stopwords.contains(a)){
                words.remove(a);
            }
        });
        return words;
    }

    static String convertStreamToString(java.io.InputStream is) {
        java.util.Scanner s = new java.util.Scanner(is).useDelimiter("\\A");
        return s.hasNext() ? s.next() : "";
    }
}

