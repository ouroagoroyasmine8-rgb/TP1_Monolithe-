package com.example.demo.order.service;

import com.example.demo.customer.service.CustomerService;
import com.example.demo.order.model.Order;
import com.example.demo.order.repository.OrderRepository;
import com.example.demo.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository repository;
    private final CustomerService customerService;
    private final ProductService productService;

    public List<Order> getAll() {
        return repository.findAll();
    }

    public Order getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Commande introuvable : " + id));
    }

    public Order save(Order order) {
        // Exercice 2 — vérifier que le client existe
        if (!customerService.customerExists(order.getCustomerId())) {
            throw new RuntimeException("Client introuvable : " + order.getCustomerId());
        }
        // Calculer le prix total
        var product = productService.getById(order.getProductId());
        order.setTotalPrice(
                product.getPrice().multiply(BigDecimal.valueOf(order.getQuantity()))
        );
        return repository.save(order);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    // Exercice 3 — historique des commandes d'un client
    public List<Order> getOrdersByCustomer(Long customerId) {
        if (!customerService.customerExists(customerId)) {
            throw new RuntimeException("Client introuvable : " + customerId);
        }
        return repository.findByCustomerId(customerId);
    }
}