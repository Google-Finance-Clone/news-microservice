package com.newsmicroservice.services;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration

public class ThreadConfig {
    @Bean(name = "taskExecutor2")
    public ThreadPoolTaskExecutor taskExecutor2() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        // Configure other properties of the executor as needed
        executor.setCorePoolSize(10); // Set the initial core pool size
        executor.setMaxPoolSize(20); // Set the initial max pool size
        executor.setQueueCapacity(100); // Set the queue capacity
        executor.initialize();
//        System.out.println("Max threads set to: " + executor.getMaxPoolSize());
        return executor;
    }
}
