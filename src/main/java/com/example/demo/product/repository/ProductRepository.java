package com.example.demo.product.repository;

import com.example.demo.product.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    // Grâce à JpaRepository, Spring Boot crée automatiquement
    // les méthodes save(), findAll(), findById(), delete(), etc.
}