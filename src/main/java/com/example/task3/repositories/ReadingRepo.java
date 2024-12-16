package com.example.task3.repositories;

import com.example.task3.entities.Reading;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReadingRepo extends MongoRepository<Reading, String> {

}