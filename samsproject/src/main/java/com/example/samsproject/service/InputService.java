package com.example.samsproject.service;

import com.example.samsproject.dto.ParentDTO;
import com.example.samsproject.exceptions.ParentException;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public interface InputService {
    ParentDTO getUserDetails(String request) throws ParentException, IOException;
}
