package com.merkapp.merkapp.dtos.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductResponseDTO {
    private Long id;
    private String name;
    private String description;
    private String category;
    private String image;
    private Double price;
    private Long storeId;

    public ProductResponseDTO(Long id, String name, String description, String category, String image, Double price,
            Long storeId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.category = category;
        this.image = image;
        this.price = price;
        this.storeId = storeId;
    }
}