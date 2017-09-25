package edu.bsu.cs222.model;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

public class WikiURLConnector {

    public InputStream URLConnect(String searchTerm) throws Exception {
        String URLPartOne = "https://en.wikipedia.org/w/api.php?action=query&format=json&prop=revisions&titles=";
        String URLPartTwo = "&rvprop=timestamp|user&rvlimit=30&redirects";
        String formattedUrl = URLPartOne + formatSearch(searchTerm) + URLPartTwo;
        URL url = new URL(formattedUrl);
        URLConnection connection = url.openConnection();
        connection.setRequestProperty("User-Agent", "Revision Tracker/0.1 (https://www.cs.bsu.edu/homepages/pvg/courses/cs222Fa17/; artinkel@bsu.edu)");
        return connection.getInputStream();
    }

    public String formatSearch(String url) {
        String formattedSearch = "";
        try {
            formattedSearch = URLEncoder.encode(url, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return formattedSearch;
    }
}