package main.java.sbt.HomeTasks.Three;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

/**
 * Created by god on 8/31/2016.
 */
public class UrlDownloader {

    public static String getContentUrl(String url) throws IOException {
        if (url == null || !url.startsWith("http"))  {
            return null;
        }
        return new Scanner(new URL(url).openStream(), "UTF-8")
                .useDelimiter("\\A")
                .next();

    }
}

