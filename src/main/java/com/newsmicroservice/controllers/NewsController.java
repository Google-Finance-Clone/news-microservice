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

    private final Object lock = new Object();
    private boolean freezeFlag = false;

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

    @PostMapping("/freeze")
    public boolean freeze() {
        System.out.print("checkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkk" + " " + freezeFlag );

        synchronized (lock) {
            freezeFlag = true;
            NewsService.freeze();
        }
        return freezeFlag;
    }

    @PostMapping("/unfreeze")
    public boolean unfreeze() {
        System.out.print("checkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkk" + " " + freezeFlag );

        synchronized (lock) {
            freezeFlag = false;
            lock.notifyAll();
            NewsService.unfreeze();
        }
        return freezeFlag;
    }


    @GetMapping("/{stock}")
    public Object getNewsByStock(@PathVariable String stock) {

        synchronized (lock) {
            if (freezeFlag) {
                // Return an error response indicating that the application is frozen
                return "App is frozen" ;
            } else {
        return NewsService.getNewsByStock(stock); }}
    }

    @GetMapping()
    public Object getFrontPageNews() {
        synchronized (lock) {
            if (freezeFlag) {
                // Return an error response indicating that the application is frozen
                return "App is frozen" ;
            } else {
        return NewsService.getFrontPageNews(); }}

    }
    //    public List<News> getNews() {
//        return NewsService.getAllNews();
//    }


    //    @PostMapping
//    public String postMessage(@RequestBody String message){
//        return sendToQueue(message);
//    }
}
