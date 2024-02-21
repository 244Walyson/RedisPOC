package com.waly.redisPoc.repository;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.waly.redisPoc.entities.Product;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
public class ProductRepository {

    private Map<Integer, Product> addProductsInList(){
        Map<Integer, Product> map = new HashMap<Integer, Product>();

        Product product1 = new Product(1, "product1", 223.00 ,"produto baratinho 1");
        Product product2 = new Product(2, "product2", 220.00 ,"produto baratinho 2");
        Product product3 = new Product(3, "product3", 200.00 ,"produto baratinho 3");
        Product product4 = new Product(4, "product4", 225.00 ,"produto baratinho 4");

        map.put(1, product1);
        map.put(2, product2);
        map.put(3, product3);
        map.put(4, product4);
        return map;
    }
    Map<Integer, Product> map = addProductsInList();
    @Cacheable("products")
    public Product findById(int id) {
        try {
            Thread.sleep(1000);
            return map.get(id);
        } catch (Exception e){
            throw new RuntimeException("quebro");
        }
    }

}
