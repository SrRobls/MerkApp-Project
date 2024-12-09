package com.merkapp.merkapp.controller;

import com.merkapp.merkapp.model.ProductEntity;
import com.merkapp.merkapp.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public List<ProductEntity> obtenerProductos() {
        return productService.obtenerProductos();
    }

    @GetMapping("/{id}")
    public ProductEntity obtenerProduct(@PathVariable Long id) {
        Optional<ProductEntity> productEntity = productService.obtenerProducto(id);
        return productEntity.orElse(null);
    }


    // TO DO: Hacer crear, actualizar y crear

}
