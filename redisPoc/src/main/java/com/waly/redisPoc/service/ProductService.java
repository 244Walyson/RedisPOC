package com.waly.redisPoc.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.waly.redisPoc.dto.ProductDTO;
import com.waly.redisPoc.entities.Product;
import com.waly.redisPoc.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Slf4j
@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    public ProductDTO findById(int id) {
        Instant initialize = Instant.now();
        Product product = repository.findById(id);
        log.info("aqui");
        Instant end = Instant.now();
        ProductDTO dto = new ProductDTO().of(product, initialize, end);
        return dto;
    }
}
