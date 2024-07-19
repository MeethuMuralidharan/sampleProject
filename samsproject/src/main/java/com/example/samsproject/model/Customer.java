package com.example.samsproject.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.ArrayList;
import java.util.Date;

@Document(collection = "customerData")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customer {

    @Id
    public String _id;
    public String userId;
    public String name;
    public String email;
    public String country;
    @JsonProperty("ParentId")
    public String parentId;
    public Address address;
    public String phone;
    public String registeredAt;
    public ArrayList<Purchase> purchases;

}
