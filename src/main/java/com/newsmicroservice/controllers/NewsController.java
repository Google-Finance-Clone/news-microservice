package com.newsmicroservice.controllers;
import java.util.*;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.mqconfig.MQSender;
import com.newsmicroservice.collections.News;
import com.newsmicroservice.services.NewsService;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@EnableRabbit
@RequestMapping("/news")
public class NewsController {

    private final NewsService newsService;

    private final Object lock = new Object();
    private boolean freezeFlag = false;

    @Autowired
    MQSender mqSender;

    @Autowired
    public NewsController(NewsService newsService) {
        this.newsService = newsService;
    }


    @RabbitListener(queues = "newsSender1")
    public String receiveMessage(String message) {

        System.out.println("message received");

        //{ method: (get,post,etc),
        //route: stocks/{ticker},
        //body:{if needed},
        //correlationId: notused }

        Gson gson = new Gson();

//        remove first and last character from message
        message = message.replace("\\", "");

        JsonObject request = gson.fromJson(message, JsonObject.class);

        // Check if status attribute exists in the request object

        String correlationId = request.get("correlationId").getAsString();
        String route = request.get("route").getAsString();
        String method = request.get("method").getAsString();
        method = method.toLowerCase();
        JsonObject body = request.get("body").getAsJsonObject();
        //split route by '/'
        String[] routeSplit = route.split("/");
        String response = "";
        String responseValue = "";
        if(routeSplit.length == 1)
        {
            responseValue = gson.toJson(newsService.getFrontPageNews());
            return responseValue;
        }
        else
        {
            String stock = routeSplit[1];
            responseValue = gson.toJson(newsService.getNewsByStock(stock));
            return responseValue;
        }
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
            newsService.freeze();
        }
        return freezeFlag;
    }

    @PostMapping("/unfreeze")
    public boolean unfreeze() {
        System.out.print("checkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkk" + " " + freezeFlag );

        synchronized (lock) {
            freezeFlag = false;
            lock.notifyAll();
            newsService.unfreeze();
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
        return newsService.getNewsByStock(stock); }}
    }

    @GetMapping()
    public Object getFrontPageNews() {
        synchronized (lock) {
            if (freezeFlag) {
                // Return an error response indicating that the application is frozen
                return "App is frozen" ;
            } else {
        return newsService.getFrontPageNews(); }}

    }
    //    public List<News> getNews() {
//        return NewsService.getAllNews();
//    }


    //    @PostMapping
//    public String postMessage(@RequestBody String message){
//        return sendToQueue(message);
//    }
}
