package com.newsmicroservice.controllers;
import java.util.*;
import com.mqconfig.MQSender;
import com.newsmicroservice.collections.News;
import com.newsmicroservice.services.NewsService;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@EnableRabbit
@RequestMapping("/news")
public class NewsController {

    private final NewsService NewsService;

    @Autowired
    MQSender mqSender;

    @Autowired
    public NewsController(NewsService NewsService) {
        this.NewsService = NewsService;
    }


//    public String sendToQueue(String message) {
//        mqSender.send(message);
//        return "Message sent to the RabbitMQ Successfully";
//    }


    @GetMapping("/{stock}")
    public List<News> getNewsByStock(@PathVariable String stock) {
        return NewsService.getNewsByStock(stock);
    }

    @GetMapping()
    public List<News> getFrontPageNews() {
        return NewsService.getFrontPageNews();
    }
    //    public List<News> getNews() {
//        return NewsService.getAllNews();
//    }


    //    @PostMapping
//    public String postMessage(@RequestBody String message){
//        return sendToQueue(message);
//    }
}
