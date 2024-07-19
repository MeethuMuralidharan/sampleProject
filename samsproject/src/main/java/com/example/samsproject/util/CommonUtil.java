package com.example.samsproject.util;

import com.example.samsproject.dto.ParentDTO;
import com.example.samsproject.exceptions.ParentException;
import com.example.samsproject.model.Parent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CommonUtil {

    public void validInputData(Parent parent)throws ParentException {
        log.info("Inside validInput data");
        if(parent.getParent() == null){
            throw new ParentException("ParentCode can't be null or empty");
        }else if (parent.getName() == null){
            throw new ParentException("Name can't be null or empty");
        }
    }
}
