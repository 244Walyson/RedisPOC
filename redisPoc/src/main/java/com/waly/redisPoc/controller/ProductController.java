package com.waly.redisPoc.controller;

import com.waly.redisPoc.dto.ProductDTO;
import com.waly.redisPoc.entities.Product;
import com.waly.redisPoc.repository.ProductRepository;
import com.waly.redisPoc.service.ProductService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.antlr.v4.runtime.misc.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;
import java.time.Instant;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService service;
    private final ConcurrentHashMap<String, Pair<AtomicInteger, Instant>> requestCounts = new ConcurrentHashMap<>();


    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> findById(@PathVariable int id, HttpServletRequest request){
        String clientIp = getClientIP(request);
        log.info("IP " + clientIp);
        Pair<AtomicInteger, Instant> pair = requestCounts.computeIfAbsent(clientIp, k -> new Pair<>(new AtomicInteger(), Instant.now()));
        AtomicInteger count = pair.a;
        long sinceLastrequest = Duration.between(pair.b, Instant.now()).getSeconds();

        if(sinceLastrequest > 10){
            count.set(0);
            requestCounts.put(clientIp, new Pair<>(count, Instant.now()));
        }
        if (count.incrementAndGet() > 3){
            return ResponseEntity.badRequest().build();
        }
        log.info(String.valueOf(count.get()));
        return ResponseEntity.ok().body(service.findById(id));
    }

    private String getClientIP(HttpServletRequest request) {
        String clientIP = request.getHeader("X-Forwarded-For");

        if (clientIP == null || clientIP.isEmpty() || "unknown".equalsIgnoreCase(clientIP)) {
            clientIP = request.getHeader("Proxy-Client-IP");
        }

        if (clientIP == null || clientIP.isEmpty() || "unknown".equalsIgnoreCase(clientIP)) {
            clientIP = request.getHeader("WL-Proxy-Client-IP");
        }

        if (clientIP == null || clientIP.isEmpty() || "unknown".equalsIgnoreCase(clientIP)) {
            clientIP = request.getRemoteAddr();
        }

        return clientIP;
    }
}
