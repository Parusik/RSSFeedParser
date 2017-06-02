package main.feed;


import java.io.*;
import java.nio.file.*;
import java.util.*;


/**
 * Created by Оля on 01.06.2017.
 */
public class FileWork {
    File file;
    String path;
    public FileWork (String s) {
        this.path = s;
        file = new File(path);
    }


    public List<String>  getListFromFile() {
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
    public void addInFile(String toAdd) {
        try {
            Files.write(Paths.get(path), toAdd.getBytes(), StandardOpenOption.APPEND);
        } catch (IOException e) {
            System.out.println("Error in adding a new source");
        }
    }
}
