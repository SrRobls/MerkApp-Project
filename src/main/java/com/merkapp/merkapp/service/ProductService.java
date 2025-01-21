package com.merkapp.merkapp.service;

import com.merkapp.merkapp.model.Product;
import com.merkapp.merkapp.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    public ProductRepository productRepository;

    public List<Product> obtenerProductos() {
        return productRepository.findAll();
    }

    public Optional<Product> obtenerProducto(Long id) {
        return productRepository.findById(id);
    }

    public Product crearProducto(Product productEntity) {
        return productRepository.save(productEntity);
    }

    public void eliminarProducto(Long id) {
        productRepository.deleteById(id);
    }
}
