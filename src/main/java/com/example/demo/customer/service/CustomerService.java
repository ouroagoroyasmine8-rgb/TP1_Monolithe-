package com.example.demo.customer.service;

import com.example.demo.customer.model.Customer;
import com.example.demo.customer.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository repository;

    public List<Customer> getAll() {
        return repository.findAll();
    }

    public Customer getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Client introuvable : " + id));
    }

    public Customer save(Customer customer) {
        return repository.save(customer);
    }

    public Customer update(Long id, Customer details) {
        Customer customer = getById(id);
        customer.setFirstName(details.getFirstName());
        customer.setLastName(details.getLastName());
        customer.setEmail(details.getEmail());
        customer.setPhone(details.getPhone());
        return repository.save(customer);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    // Exercice 2 — vérifie si un client existe
    public boolean customerExists(Long id) {
        return repository.existsById(id);
    }
}