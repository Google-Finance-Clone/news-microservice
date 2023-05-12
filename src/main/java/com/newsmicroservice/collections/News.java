package com.newsmicroservice.collections;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
@NoArgsConstructor
@ToString
public class News {
    @Id
    private String id;
    private String headline;
    private String url;
    private String publisher;
    private String date;
    private String stock;

    public News(String name, String url, String date, String stock, String publisher) {
        this.headline = name;
        this.url = url;
        this.date = date;
        this.stock = stock;
        this.publisher = publisher;
    }
}
