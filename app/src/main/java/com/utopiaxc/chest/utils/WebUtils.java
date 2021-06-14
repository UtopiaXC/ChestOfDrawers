package com.utopiaxc.chest.utils;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class WebUtils {
    private static final String userAgent = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/78.0.3904.87 Safari/537.36";
    public static Document getFromURL(String URL){
            try {
                Connection.Response response = Jsoup.connect(URL)
                        .userAgent(userAgent)
                        .method(Connection.Method.GET)
                        .execute();

                return response.parse();
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
    }
}
