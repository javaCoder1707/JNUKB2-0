package com.parser.ru;


import org.apache.http.util.TextUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ParserRU {

//     public static List<ResultRU> receiveNewsResultsByType(String type) throws IOException {
//         List<ResultRU> results = new ArrayList<>();
//         switch (type){
//             case "POLICY_RU":
//                 results = receivePolicyNewsResults();
//                 break;

//             case "MOBILE_RU":
//                 results = receiveMobileNewsResults();
//                 break;

//             case "AUTO_RU":
//                 results = receiveAutoNewsResults();
//                 break;

//             case "SPORT_RU":
//                 results = receiveSportNewsResults();
//                 break;

//             case "ART_RU":
//                 results = receiveArtNewsResults();
//                 break;

//             default:
//                 System.err.println("Invalid news type at  ParserRU.receiveNewsResultByType() call");
//         }

//         return results;
//     }

    public static List<ResultRU> receiveAllNewsResults() throws IOException{
        results.addAll(receivePolicyNewsResults().subList(0,5));
        results.addAll(receiveMobileNewsResults().subList(0,5));
        results.addAll(receiveAutoNewsResults().subList(0,5));
        results.addAll(receiveSportNewsResults().subList(0,5));
        results.addAll(receiveArtNewsResults().subList(0,6));

        Collections.shuffle(results);

        return results;
    }

    public static List<ResultRU> receivePolicyNewsResults() throws IOException {
        List<ResultRU> results = new ArrayList<>();

        System.out.println("Parsing...");
        Document document = Jsoup.connect("https://ria.ru/politics/").get();

        Elements divs = document.getElementsByClass("list-item__content");

        divs.forEach(div -> {
            Element aLink = div.child(1);

            String url = (TextUtils.isEmpty(aLink.attr("href"))) ? "https://ria.ru/politics/" : aLink.attr("href");
            String text = (TextUtils.isEmpty(aLink.text())) ? "Политика" : aLink.text();

            results.add(new ResultRU(url, text, "POLICY_RU"));
        });

        return results;
    }

    public static List<ResultRU> receiveMobileNewsResults() throws IOException {
        List<ResultRU> results = new ArrayList<>();

        System.out.println("Parsing...");
        Document document = Jsoup.connect("https://www.ixbt.com/news/").get();

        Elements elements = document.getElementsByClass("item");

        elements.forEach(element -> {
            Element aLink = (element.children().size() == 2) ? element.child(1) : element.child(2) ;

            String url = (TextUtils.isEmpty(aLink.attr("href"))) ? "https://www.ixbt.com/news/" : aLink.attr("href");
            String text = (TextUtils.isEmpty(aLink.text())) ? "Технологии" : aLink.text();

            results.add(new ResultRU(url, text, "MOBILE_RU"));
        });

        return results;
    }

    public static List<ResultRU> receiveAutoNewsResults() throws IOException {
        List<ResultRU> results = new ArrayList<>();

        System.out.println("Parsing...");
        Document document = Jsoup.connect("https://ria.ru/auto/").get();

        Elements divs = document.getElementsByClass("list-item__content");

        divs.forEach(div -> {
            Element aLink = div.child(1);

            String url = (TextUtils.isEmpty(aLink.attr("href"))) ? "https://ria.ru/auto/" : aLink.attr("href");
            String text = (TextUtils.isEmpty(aLink.text())) ? "Авто" : aLink.text();

            results.add(new ResultRU(url, text, "AUTO_RU"));
        });

        return results;
    }

    public static List<ResultRU> receiveSportNewsResults() throws IOException {
        List<ResultRU> results = new ArrayList<>();

        System.out.println("Parsing...");
        Document document = Jsoup.connect("https://www.sports.ru/news").get();

        Elements aLinks = document.getElementsByClass("short-text");

        aLinks.forEach(aLink -> {
            String url = (TextUtils.isEmpty(aLink.attr("href"))) ? "https://www.sports.ru/news" : aLink.attr("href");
            String text = (TextUtils.isEmpty(aLink.text())) ? "Спорт" : aLink.text();

            results.add(new ResultRU(url, text, "SPORT_RU"));
        });

        return results;
    }

    public static List<ResultRU> receiveArtNewsResults() throws IOException {
        List<ResultRU> results = new ArrayList<>();
        results.addAll(receiveFilmsNewsResults());
        results.addAll(receiveMusicNewsResults());

        Collections.shuffle(results);

        return results;
    }

    private static List<ResultRU> receiveFilmsNewsResults() throws IOException {
        List<ResultRU> results = new ArrayList<>();

        System.out.println("Parsing...");
        Document document = Jsoup.connect("https://ria.ru/category_kino/").get();

        Elements divs = document.getElementsByClass("list-item__content");

        divs.forEach(div -> {
            Element aLink = div.child(1);

            String url = (TextUtils.isEmpty(aLink.attr("href"))) ? "https://ria.ru/category_kino/" : aLink.attr("href");
            String text = (TextUtils.isEmpty(aLink.text())) ? "Искусство" : aLink.text();

            results.add(new ResultRU(url, text, "ART_RU"));
        });

        return results;
    }

    private static List<ResultRU> receiveMusicNewsResults() throws IOException {
        List<ResultRU> results = new ArrayList<>();

        System.out.println("Parsing...");
        Document document = Jsoup.connect("https://ria.ru/category_muzyka/").get();

        Elements divs = document.getElementsByClass("list-item__content");

        divs.forEach(div -> {
            Element aLink = div.child(1);

            String url = (TextUtils.isEmpty(aLink.attr("href"))) ? "https://ria.ru/category_muzyka/" : aLink.attr("href");
            String text = (TextUtils.isEmpty(aLink.text())) ? "Искусство" : aLink.text();

            results.add(new ResultRU(url, text, "ART_RU"));
        });

        return results;
    }
}
