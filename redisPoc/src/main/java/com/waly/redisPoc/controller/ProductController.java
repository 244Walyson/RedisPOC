package com.waly.redisPoc.controller;

import com.waly.redisPoc.dto.ProductDTO;
import com.waly.redisPoc.entities.Product;
import com.waly.redisPoc.repository.ProductRepository;
import com.waly.redisPoc.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService service;

    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> findById(@PathVariable int id){
        log.info("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx " + String.valueOf(id));
        return ResponseEntity.ok().body(service.findById(id));
    }
}
