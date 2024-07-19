package com.example.samsproject.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    public String _id;
    public String name;
    public String email;
    public String country;
    @JsonProperty("ParentId")
    public String parentId;
    @JsonProperty("Parent")
    public ParentDTO parent;
    public AddressDTO address;
    public String phone;
    public String registeredAt;
    public ArrayList<PurchaseDTO> purchases;
}
