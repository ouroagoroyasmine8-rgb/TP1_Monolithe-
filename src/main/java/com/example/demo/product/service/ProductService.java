package com.example.demo.product.service;

import com.example.demo.product.model.Product;
import com.example.demo.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor // Génère automatiquement le constructeur pour injecter le repository
@Transactional
public class ProductService {

    private final ProductRepository repository;

    // Récupérer tous les produits
    @Transactional(readOnly = true)
    public List<Product> getAll() {
        return repository.findAll();
    }

    // Récupérer un produit par son ID
    @Transactional(readOnly = true)
    public Product getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produit non trouvé avec l'id : " + id));
    }

    // Créer ou mettre à jour un produit
    public Product save(Product product) {
        return repository.save(product);
    }

    // Supprimer un produit
    public void delete(Long id) {
        repository.deleteById(id);
    }
}