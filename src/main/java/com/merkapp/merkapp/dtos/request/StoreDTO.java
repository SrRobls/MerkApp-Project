package com.merkapp.merkapp.dtos.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Schema
public class StoreDTO {

    @Schema(example = "Supermercado XYZ", description = "Nombre de la tienda")
    private String name;

    @Schema(example = "Avenida Principal 123", description = "Ubicación de la tienda")
    private String location;

    @Schema(example = "Ven y Disfruta de la tienda!", description = "Descripción de la tienda")
    private String description;

    @Schema(example = "(imagen)", description = "URL de la imagen de la tienda")
    private String image;

    @Schema(example = "1", description = "ID del propietario de la tienda (Debe ser un ADMIN)")
    private Long ownerId;

    public StoreDTO() {}

    public StoreDTO(String name, String location, String description, Long ownerId) {
        this.name = name;
        this.location = location;
        this.description = description;
        this.ownerId = ownerId;
    }
}
