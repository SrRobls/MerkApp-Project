package com.merkapp.merkapp.controller;

import com.merkapp.merkapp.model.Product;
import com.merkapp.merkapp.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/product")
public class ProductController {

    @Autowired
    public ProductService productService;

    @GetMapping
    public List<Product> obtenerProductos() {
        return productService.obtenerProductos();
    }

    @GetMapping("/{id}")
    public Product obtenerProduct(@PathVariable Long id) {
        Optional<Product> productEntity = productService.obtenerProducto(id);
        return productEntity.orElse(null);
    }

    // TO DO: Hacer crear, actualizar y crear

}
