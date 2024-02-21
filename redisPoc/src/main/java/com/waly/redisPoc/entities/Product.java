package com.waly.redisPoc.entities;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Product implements Serializable {

    private int id;
    private String name;
    private Double price;
    private String description;

}
