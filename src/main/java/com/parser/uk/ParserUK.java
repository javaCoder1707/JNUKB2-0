package com.parser.uk;

import org.apache.http.util.TextUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ParserUK {

//     public static List<ResultUK> receiveNewsResultsByType(String type) throws IOException {
//         List<ResultUK> results = new ArrayList<>();
//         switch (type){
//             case "NEWS_UK":
//                 results = receiveResultsNewsOfUK();
//                 break;

//             case "WORLD_UK":
//                 results = receiveWorldNewsResults();
//                 break;

//             case "POLITIC_UK":
//                 results = receivePoliticNewsResults();
//                 break;

//             case "MOTORS_UK":
//                 results = receiveMotorsNewsResults();
//                 break;

//             case "SPORT_UK":
//                 results = receiveSportNewsResults();
//                 break;

//             default:
//                 System.err.println("Invalid news type at ParserUK.receiveNewsResultsByType() call ");
//         }

//         return results;
//     }

    public static List<ResultUK> receiveAllNewsResults() throws IOException {
        List<ResultUK> results = new ArrayList<>();

        System.out.println("Parsing...");
        Document document = Jsoup.connect("https://www.thesun.co.uk/news/").get();

        Elements divs = document.getElementsByClass("rail__item-content");

        divs.forEach(div -> {
            Element aLink = div.child(0);
            Element header = div.child(0).child(0).child(0);

            String url = (TextUtils.isEmpty(aLink.attr("href"))) ? "https://www.thesun.co.uk/news/" : aLink.attr("href");
            String text = (TextUtils.isEmpty(header.text())) ? "News" : header.text();

            results.add(new ResultUK(url, text, "ALL_UK"));

        });

        return results;
    }

    public static List<ResultUK> receiveResultsNewsOfUK() throws IOException {
        List<ResultUK> results = new ArrayList<>();

        System.out.println("Parsing...");
        Document document = Jsoup.connect("https://www.thesun.co.uk/news/uknews/").get();

        Elements aLinks = document.getElementsByClass("text-anchor-wrap");

        aLinks.forEach(aLink -> {
            Element header = aLink.child(0);

            String url = (TextUtils.isEmpty(aLink.attr("href"))) ? "https://www.thesun.co.uk/news/uknews/" : aLink.attr("href");
            String text = (TextUtils.isEmpty(header.text())) ? "News of UK" : header.text();

            results.add(new ResultUK(url, text, "NEWS_UK"));
        });


        return results;
    }

    public static List<ResultUK> receiveWorldNewsResults() throws IOException{
        List<ResultUK> results = new ArrayList<>();

        System.out.println("Parsing...");
        Document document = Jsoup.connect("https://www.thesun.co.uk/news/worldnews/").get();

        Elements aLinks = document.getElementsByClass("text-anchor-wrap");

        aLinks.forEach(aLink -> {
            Element header = aLink.child(0);

            String url = (TextUtils.isEmpty(aLink.attr("href"))) ? "https://www.thesun.co.uk/news/worldnews/" : aLink.attr("href");
            String text = (TextUtils.isEmpty(header.text())) ? "World News" : header.text();

            results.add(new ResultUK(url, text, "WORLD_UK"));
        });

        return results;
    }

    public static List<ResultUK> receivePoliticNewsResults() throws IOException {
        List<ResultUK> results = new ArrayList<>();

        System.out.println("Parsing...");
        Document document = Jsoup.connect("https://www.thesun.co.uk/news/politics/").get();

        Elements aLinks = document.getElementsByClass("text-anchor-wrap");

        aLinks.forEach(aLink -> {
            Element header = aLink.child(0);

            String url = (TextUtils.isEmpty(aLink.attr("href"))) ? "https://www.thesun.co.uk/news/politics/" : aLink.attr("href");
            String text = (TextUtils.isEmpty(header.text())) ? "Politic News" : header.text();

            results.add(new ResultUK(url, text,"POLITIC_UK"));
        });

        return results;
    }

    public static List<ResultUK> receiveMotorsNewsResults() throws IOException {
        List<ResultUK> results = new ArrayList<>();

        System.out.println("Parsing...");
        Document document = Jsoup.connect("https://www.thesun.co.uk/motors/").get();

        Elements aLinks = document.getElementsByClass("text-anchor-wrap");

        aLinks.forEach(aLink -> {
            Element header = aLink.child(0);

            String url = (TextUtils.isEmpty(aLink.attr("href"))) ? "https://www.thesun.co.uk/motors/" : aLink.attr("href");
            String text = (TextUtils.isEmpty(header.text())) ? "Motors" : header.text();

            results.add(new ResultUK(url, text,"MOTORS_UK"));
        });

        return results;
    }

    public static List<ResultUK> receiveSportNewsResults() throws IOException {
        List<ResultUK> results = new ArrayList<>();

        System.out.println("Parsing...");
        Document document = Jsoup.connect("https://www.thesun.co.uk/sport/").get();

        Elements divs = document.getElementsByClass("rail__item-content");

        divs.forEach(div -> {
            Element aLink = div.child(0);
            Element header = div.child(0).child(0).child(0);

            String url = (TextUtils.isEmpty(aLink.attr("href"))) ? "https://www.thesun.co.uk/sport/" : aLink.attr("href");
            String text = (TextUtils.isEmpty(header.text())) ? "Sport" : header.text();

            results.add(new ResultUK(url, text,"SPORT_UK"));
        });

        return results;
    }
}
