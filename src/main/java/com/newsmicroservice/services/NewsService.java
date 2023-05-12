package com.newsmicroservice.services;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.newsmicroservice.collections.*;
import com.newsmicroservice.repositories.NewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


import java.util.List;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
@Service
public class NewsService {


    private final NewsRepository NewsRepository;
    private final RestTemplate restTemplate;

//    public String fetchStockData() {
//        //   String url = "https://api.polygon.io/v1/open-close/TSLA/2023-01-09?adjusted=true&";
////        HttpHeaders headers = new HttpHeaders();
////        headers.setBearerAuth(apiKey);
////        HttpEntity<String> requestEntity = new HttpEntity<>(headers);
////        String response = restTemplate.getForObject(url, String.class,requestEntity);
//        //    url+="apikey=" + apiKey;
//        String url = "https://serpapi.com/search.json?apikey=7ba471a4862d7a11e51ebc0668c2b38e255bfb24c403f5d0fbd54d5dd287fb93&engine=google_finance&q=GOOG:NASDAQ";
//        String resposne = restTemplate.getForObject(url, String.class);
//        JsonObject jsonObject = JsonParser.parseString(resposne).getAsJsonObject();
//        //  JsonObject resposne = restTemplate.getForObject(url, JsonObject.class);
//
//        return jsonObject.get("summary").toString();
//    }

    @Autowired
    public NewsService(NewsRepository NewsRepository, RestTemplate restTemplate) {
        this.NewsRepository = NewsRepository;
        this.restTemplate = restTemplate;
    }

    public List<News> getAllNews() {
        List<News> result = NewsRepository.findAll();
        System.out.println(result);
        return result;
    }

    public List<News> getNewsByStock(String stock) {
        Pageable pageable = PageRequest.of(0, 6);
        return NewsRepository.findByStock(stock, pageable);
    }

    public List<News> getFrontPageNews() {
        Pageable pageable = PageRequest.of(0, 10);
        return NewsRepository.findLatestNews(pageable);
    }

//    public List<Stock> getAllStocks() {
//        return NewsRepository.findAll();
//    }
//
//    public void addNewStock() {
//    }
}
