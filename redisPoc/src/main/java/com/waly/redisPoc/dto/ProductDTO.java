package com.waly.redisPoc.dto;

import com.waly.redisPoc.entities.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.beans.Beans;
import java.time.Duration;
import java.time.Instant;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductDTO {

    private int id;
    private String name;
    private Double price;
    private String description;
    private Instant initialize;
    private Instant end;
    private Duration timeSpend;

    public ProductDTO of(Product entity, Instant initialize, Instant end){
        ProductDTO dto = new ProductDTO();
        BeanUtils.copyProperties(entity, dto);
        dto.setInitialize(initialize);
        dto.setEnd(end);
        Duration duration = Duration.between(initialize, end);
        dto.setTimeSpend(duration);
        return dto;
    }
}
