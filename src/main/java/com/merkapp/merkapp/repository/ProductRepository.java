package com.merkapp.merkapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.merkapp.merkapp.model.Product;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    // Agregamos consultas personalizadas si queremos gente. Pero con esto, osea con
    // JPA, ya tenemolas mas
    // Comunes o importantes por defecto
    List<Product> findByStoreId(Long storeId);
    List<Product> findByCategoryIgnoreCase(String categoryId);
}
