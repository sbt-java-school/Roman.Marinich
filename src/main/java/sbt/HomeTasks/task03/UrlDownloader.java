package main.java.sbt.HomeTasks.task03;

import java.io.IOException;
import java.net.URL;
import java.util.Scanner;

import static main.java.sbt.HomeTasks.task01.Contract.*;

/**
 * Created by god on 8/31/2016.
 */
public class UrlDownloader {

    public static void main(String[] args) {
        try {

            String exp = getContentUrl("http://google.com");

            String url = "http://google.com";
            Scanner s = new Scanner(new URL(url).openStream(), "UTF-8");

            //s = s.useDelimiter("\\A");

            String finals = s.next();

            int x = 1;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getContentUrl(String url) throws IOException {
        isNotNull(url);
        isStartWith(url, "http");

        return new Scanner(new URL(url).openStream(), "UTF-8")
                .useDelimiter("\\A")
                .next();

    }
}

