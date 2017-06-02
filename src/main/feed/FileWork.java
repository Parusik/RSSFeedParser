package main.feed;


import java.io.*;
import java.nio.file.*;
import java.util.*;


/**
 * Created by Оля on 01.06.2017.
 */
public class FileWork {

    public List<String>  getListFromFile(String path) {
        File file = new File(path);
        Scanner sc = null;
        try {
            sc = new Scanner(file);
        } catch (FileNotFoundException e) {
            System.out.print("File not found");
        }

        List<String> result = new ArrayList<>();
        while (sc.hasNextLine()) {
            result.add(sc.nextLine());
        }
        System.out.println(result.toString());
        return result;

    }


}
