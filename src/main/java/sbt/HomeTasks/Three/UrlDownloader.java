package main.java.sbt.HomeTasks.Three;

import java.io.IOException;
import java.net.URL;
import java.util.Scanner;

/**
 * Created by god on 8/31/2016.
 */
public class UrlDownloader {
    public static void main(String[] args) throws IOException {
        String out =
                new Scanner(new URL("http://www.google.com").openStream(), "UTF-8")
                        .useDelimiter("\\A")
                        .next();
        System.out.println(out);
    }
}
