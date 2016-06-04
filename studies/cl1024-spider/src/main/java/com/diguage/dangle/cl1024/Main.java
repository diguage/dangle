package com.diguage.dangle.cl1024;

import org.jsoup.*;
import org.jsoup.nodes.*;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * Created by diguage on 16/5/29.
 */
public class Main {
    private static final Logger log = LoggerFactory.getLogger(Main.class);
    private static final String webHost = "http://cl.fffkkk.me";
    private static final String indexPage = webHost + "/index.php";
    private static final String firstPage = webHost + "/htm_data/20/1605/1918022.html";
    private static final String urlFormater = webHost + "/read.php?tid=1918022&fpage=0&toread=&page=%d";


    public static void main(String[] args) {
        try {
            Document doc = Jsoup.connect(indexPage).get();
//            Elements pageBars = doc.select("#main.pages");
//            Element pageBar = pageBars.first();
//            Elements pages = pageBar.select("a");
//            for (Element page : pages) {
//                System.out.println(page.html());
//            }
        } catch (IOException e) {
            log.error("connect to {}", firstPage, e);
        }
    }
}
