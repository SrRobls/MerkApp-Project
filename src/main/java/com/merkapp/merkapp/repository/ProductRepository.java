package com.merkapp.merkapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.merkapp.merkapp.model.ProductEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {

    // Agregamos consultas personalizadas si queremos gente. Pero con esto, osea con JPA, ya tenemolas mas
    //Comunes o importantes por defecto
}
