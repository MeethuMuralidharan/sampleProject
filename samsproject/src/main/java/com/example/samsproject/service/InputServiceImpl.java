package com.example.samsproject.service;

import com.example.samsproject.dto.ParentDTO;
import com.example.samsproject.exceptions.ParentException;
import com.example.samsproject.model.Customer;
import com.example.samsproject.model.Parent;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class InputServiceImpl implements InputService{
        @Value("${spring.consumer.topic}")
        public String test_topic;

        @Autowired
        KafkaConsumerService kafkaConsumerService;

        //Output Json
        public ParentDTO getUserDetails(String request) throws ParentException, IOException {
            Parent parent = kafkaConsumerService.listenKafka(request);
            // log.info("ParentDTO_____________ "+parent);
            String parentIdKafka= parent.getParent();
            // log.info("Parent id from kafka "+parentIdKafka);
            List<Customer> custemersList = kafkaConsumerService.findAllTasks();
            ArrayList<Customer> customers = new ArrayList<>();
            for( Customer customer : custemersList){
                log.info("Customers parentId:"+customer.getParentId());
                if(customer.getParentId().equals(parentIdKafka)) {
                    customers.add(kafkaConsumerService.getByUserId(customer.userId));
                }else{
                    log.info("There is no matched record");
                }
            }
            log.info("Customers list:"+customers);
            ParentDTO parentDTO = new ParentDTO();
            parentDTO.setParent(parent.getParent());
            parentDTO.setName(parent.getName());
            parentDTO.setEmail(parent.getEmail());
            parentDTO.setAddress(parent.getAddress());
            parentDTO.setPhone(parent.getPhone());
            parentDTO.setRegisteredAt(parent.getRegisteredAt());
            parentDTO.setUser(customers);
            log.info("********FinalOutPut********"+parentDTO);

            String filePath = "src/main/resources/data.json";
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File(filePath),parentDTO);
                System.out.println("JSON file created: " +filePath);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return parentDTO;
        }

}
