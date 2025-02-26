package com.merkapp.merkapp.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private String description;
    private String category;
    private String image;
    private Double price;

    @ManyToOne
    @JoinColumn(name = "store_id", nullable = false)
    @JsonBackReference // Evita la serialización recursiva infinita
    private Store store;

    public Product() {}

    public Product(String name, String description, String category, String image, Double price, Store store) {
        this.name = name;
        this.description = description;
        this.category = category;
        this.image = image;
        this.price = price;
        this.store = store;
    }
}
