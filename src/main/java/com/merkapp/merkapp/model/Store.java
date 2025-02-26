package com.merkapp.merkapp.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@Entity
public class Store {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private String location;

    @Column(nullable = false)
    private String description; // 🔹 Nueva descripción de la tienda

    @Column(nullable = false)
    private String image; // 🔹 URL de la imagen subida a S3

    @ManyToOne
    @JoinColumn(name = "owner_id", nullable = false)
    private User owner;

    @OneToMany(mappedBy = "store", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonBackReference // 🔹 Evita la serialización infinita
    private List<Product> products;

    public Store() {}

    public Store(String name, String location, String description, String image, User owner) {
        this.name = name;
        this.location = location;
        this.description = description;
        this.image = image;
        this.owner = owner;
    }
}
