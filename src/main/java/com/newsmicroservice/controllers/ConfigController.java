package com.newsmicroservice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.PropertySource;
import org.springframework.web.bind.annotation.*;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/config")
public class ConfigController {

    @Autowired
    private ConfigurableEnvironment environment;
    @Autowired
    private ConfigurableApplicationContext context;

    @PostMapping("/max-threads/{maxThreads}")
    public String updateMaxThreads(@PathVariable int maxThreads) {

        MutablePropertySources propertySources = environment.getPropertySources();
        Map<String, Object> map = new HashMap<>();
        map.put("server.tomcat.max-threads", maxThreads);
        map.put("spring.data.mongodb.uri", "mongodb+srv://imostafa1000:88cUr4F8Rwv59ijU@cluster0.sgvmouc.mongodb.net/StocksDB?retryWrites=true&w=majority");
        map.put("spring.rabbitmq.port", 5672);
        map.put("spring.rabbitmq.username", "guest");
        map.put("spring.rabbitmq.password", "guest");
        map.put("spring.rabbitmq.host", "localhost");
        try
        {
            propertySources
                    .replace("applicationConfig: [classpath:/application.properties]", new MapPropertySource("newmap", map));
        }
        catch (Exception e)
        {
            propertySources
                    .addFirst(new MapPropertySource("newmap", map));
            System.out.println(e.getMessage());
            System.out.println("Added new Property Source As Map Instead of Replacing");
        }
        return "Max threads updated to " + maxThreads;
    }

    @PostMapping("/max-database-connections/{maxDatabaseConnections}")
    public String updateMaxDatabaseConnections(@PathVariable int maxDatabaseConnections) {

        MutablePropertySources propertySources = environment.getPropertySources();
        Map<String, Object> map = new HashMap<>();
        map.put("spring.data.mongodb.connectionPool.maxSize", maxDatabaseConnections);
        map.put("spring.data.mongodb.uri", "mongodb+srv://imostafa1000:88cUr4F8Rwv59ijU@cluster0.sgvmouc.mongodb.net/StocksDB?retryWrites=true&w=majority");
        map.put("spring.rabbitmq.port", 5672);
        map.put("spring.rabbitmq.username", "guest");
        map.put("spring.rabbitmq.password", "guest");
        map.put("spring.rabbitmq.host", "localhost");
        try
        {
            propertySources
                    .replace("applicationConfig: [classpath:/application.properties]", new MapPropertySource("newmap", map));
        }
        catch (Exception e)
        {
            propertySources
                    .addFirst(new MapPropertySource("newmap", map));
            System.out.println(e.getMessage());
            System.out.println("Added new Property Source As Map Instead of Replacing");
        }
        return "Max database connections updated to " + maxDatabaseConnections;
    }


    @PostMapping("/MQAddress/{ip}/{port}")
    public String setRabbitMQ(@PathVariable int port, @PathVariable String ip) {

        MutablePropertySources propertySources = environment.getPropertySources();
        Map<String, Object> map = new HashMap<>();
        map.put("spring.rabbitmq.port", port);
        map.put("spring.rabbitmq.host", ip);
        map.put("spring.data.mongodb.uri", "mongodb+srv://imostafa1000:88cUr4F8Rwv59ijU@cluster0.sgvmouc.mongodb.net/StocksDB?retryWrites=true&w=majority");
        map.put("spring.rabbitmq.port", 5672);
        map.put("spring.rabbitmq.username", "guest");
        map.put("spring.rabbitmq.password", "guest");
        map.put("spring.rabbitmq.host", "localhost");
        try
        {
            propertySources
                    .replace("applicationConfig: [classpath:/application.properties]", new MapPropertySource("newmap", map));
        }
        catch (Exception e)
        {
            propertySources
                    .addFirst(new MapPropertySource("newmap", map));
            System.out.println(e.getMessage());
            System.out.println("Added new Property Source As Map Instead of Replacing");
        }
        return "Rabbit mq port set to: " + port + " and ip set to: " + ip;
    }

//    @PostMapping("/freeze")
//    public String shutdown() {
//        SpringApplication.exit(context, () -> 0);
//        return "Application is shutting down...";
//    }


}
