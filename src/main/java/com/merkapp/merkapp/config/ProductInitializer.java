package com.merkapp.merkapp.config;

import com.merkapp.merkapp.model.Product;
import com.merkapp.merkapp.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

//Hacemos con este decordador que Spring boot se encargue de gestionar este modulo, esto para que spring boot lo inicialice
@Component
public class ProductInitializer implements CommandLineRunner {

    private final ProductRepository productRepository;

    public ProductInitializer(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    // Usamos esta funcion para crear 10 registros de produtco por defecto si no hay
    // regsitros, estamos
    // Sobrescribiendo la funcion de la clase padre para hacer esto
    @Override
    public void run(String... args) throws Exception {
        if (productRepository.count() == 0) {
            for (int i = 0; i < 10; i++) {
                Product product = new Product();
                product.setName("Product " + i);
                product.setPrice(0.0 + i);
                product.setDescription("Product Description " + i);
                product.setCategory("Test Category " + i);
                productRepository.save(product);
            }
        }
    }

}
