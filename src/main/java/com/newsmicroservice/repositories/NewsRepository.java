package com.newsmicroservice.repositories;

import com.newsmicroservice.collections.News;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Pageable;

import java.util.List;

@Repository
public interface NewsRepository extends MongoRepository<News, String> {
    @Query(value = "{'stock': ?0 }", sort = "{'date': -1}")
    List<News> findByStock(String stock, Pageable pageable);

    @Query(value = "{}", sort = "{'date': -1}")
    List<News> findLatestNews(Pageable pageable);
}
