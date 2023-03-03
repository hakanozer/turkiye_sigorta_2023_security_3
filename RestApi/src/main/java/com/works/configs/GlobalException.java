package com.works.configs;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class GlobalException {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity methodArgumentNotValid( MethodArgumentNotValidException ex ) {
        Map<String, Object> hm = new LinkedHashMap<>();
        hm.put("status", false);
        hm.put("errors", parseError(ex.getFieldErrors()) );
        return new ResponseEntity(hm, HttpStatus.BAD_REQUEST);
    }

    private List parseError(List<FieldError> errors) {
        List list = new ArrayList();
        for( FieldError item : errors) {
            Map map = new LinkedHashMap();
            map.put("field", item.getField());
            map.put("message", item.getDefaultMessage());
            map.put("rejectedValue", item.getRejectedValue());
            list.add(map);
        }
        return list;
    }

}
