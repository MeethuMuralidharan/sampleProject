package com.example.samsproject.service;

import com.example.samsproject.exceptions.ParentException;
import com.example.samsproject.model.Customer;
import com.example.samsproject.model.Parent;
import com.example.samsproject.repository.SamsRepository;
import com.example.samsproject.util.CommonUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Slf4j
@Service
@Component
public class KafkaConsumerService {

    @Autowired
    CommonUtil commonutil;
    @Autowired
    SamsRepository samsRepository;
    @Value("${spring.consumer.topic}")
    public String test_topic;

    @KafkaListener(topics = "${spring.consumer.topic}" , groupId = "my-group")
    public Parent listenKafka(String message) throws ParentException, JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        Parent parentData = objectMapper.readValue(message, Parent.class);
        log.info("---------new Parent---------"+parentData);
        commonutil.validInputData(parentData);
        return parentData;
    }
    public List<Customer> findAllTasks() {
        log.info("*****Data from DB");
        List<Customer> list = samsRepository.findAll();
        log.info("full records from db"+list.toString());
        return samsRepository.findAll();
    }
    public Customer getByUserId(String id) throws IOException {
        return samsRepository.findByUserId(id);
    }
}
