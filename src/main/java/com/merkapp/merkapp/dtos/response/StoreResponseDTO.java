package com.merkapp.merkapp.dtos.response;

import com.merkapp.merkapp.dtos.response.ProductResponseDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class StoreResponseDTO {
    private Long id;
    private String name;
    private String location;
    private String description; // 🔹 Nueva descripción
    private String image; // 🔹 URL de la imagen
    private String ownerName;
    private List<ProductResponseDTO> products;

    public StoreResponseDTO(Long id, String name, String location, String description, String image, String ownerName,
                            List<ProductResponseDTO> products) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.description = description;
        this.image = image;
        this.ownerName = ownerName;
        this.products = products;
    }
}