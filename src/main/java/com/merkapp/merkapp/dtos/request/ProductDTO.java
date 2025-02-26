package com.merkapp.merkapp.dtos.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Schema(description = "DTO para representar un Producto")
public class ProductDTO {

    @JsonProperty("name")
    @Schema(example = "Papa", description = "Nombre del producto")
    private String name;

    @JsonProperty("description")
    @Schema(example = "Deliciosa Papa", description = "Descripción del producto")
    private String description;

    @JsonProperty("category")
    @Schema(example = "Verduras", description = "Categoría del producto")
    private String category;

    @JsonProperty("price")
    @Schema(example = "5000", description = "Precio en COP")
    private Double price;

    @JsonProperty("storeId")
    @Schema(example = "1", description = "ID de la tienda donde se vende el producto")
    private Long storeId;

    public ProductDTO() {}

    public ProductDTO(String name, String description, String category, Double price, Long storeId) {
        this.name = name;
        this.description = description;
        this.category = category;
        this.price = price;
        this.storeId = storeId;
    }
}
