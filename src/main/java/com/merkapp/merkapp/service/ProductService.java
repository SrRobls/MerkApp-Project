package com.merkapp.merkapp.service;

import com.merkapp.merkapp.model.ProductEntity;
import com.merkapp.merkapp.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;

    }

    public List<ProductEntity> obtenerProductos(){
        return productRepository.findAll();
    }

    public Optional<ProductEntity> obtenerProducto(Long id){
        return productRepository.findById(id);
    }



    public ProductEntity crearProducto(ProductEntity productEntity){
        return productRepository.save(productEntity);
    }

    public void eliminarProducto(Long id){
        productRepository.deleteById(id);
    }
}
