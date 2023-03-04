package com.works.services;

import com.works.entities.Product;
import com.works.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ProductService {

    final ProductRepository repository;
    final CacheManager cacheManager;

    public ResponseEntity save(Product product) {
        Map<String, Object> hm = new LinkedHashMap<>();
        repository.save(product);
        hm.put("status", true);
        hm.put("result", product);
        cacheManager.getCache("product").clear();
        return new ResponseEntity(hm, HttpStatus.OK);
    }

    @Cacheable("product")
    public ResponseEntity list() {
        Map<String, Object> hm = new LinkedHashMap<>();
        hm.put("status", true);
        hm.put("result", repository.findAll());
        return new ResponseEntity(hm, HttpStatus.OK);
    }

}
