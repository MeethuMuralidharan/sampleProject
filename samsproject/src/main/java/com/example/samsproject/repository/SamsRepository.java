package com.example.samsproject.repository;

import com.example.samsproject.model.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SamsRepository extends MongoRepository<Customer,String> {
    public Customer findByUserId(String id);
}
