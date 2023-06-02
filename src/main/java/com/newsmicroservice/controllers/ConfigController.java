package com.newsmicroservice.controllers;
import com.mongodb.MongoClientSettings;
import io.netty.handler.logging.LogLevel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.PropertySource;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.bind.annotation.*;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.context.ApplicationContext;

import java.util.HashMap;
import java.util.Map;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("stocks1/config")
public class ConfigController {

    @Autowired
    private ConfigurableEnvironment environment;
    @Autowired
    private ConfigurableApplicationContext context;

    @Autowired
    @Qualifier("taskExecutor2")
    private ThreadPoolTaskExecutor taskExecutor2;



    @Autowired
    private ApplicationContext applicationContext;


    private static LogLevel errorReportingLevel = LogLevel.ERROR; // Default error reporting level


    @PostMapping("/set-error-reporting-level")
    public ResponseEntity<String> setErrorReportingLevel(@RequestParam LogLevel level) {
        errorReportingLevel = level;
        return ResponseEntity.ok("Error reporting level set to: " + level);
    }

    @PostMapping("/max-threads/{maxThreads}")
    public String setMaxThreads(@PathVariable int maxThreads) {
        taskExecutor2.setMaxPoolSize(maxThreads);

        return "max threads set to"+taskExecutor2.getMaxPoolSize();

    }



}
