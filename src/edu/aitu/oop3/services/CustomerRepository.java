package edu.aitu.oop3.services;

import edu.aitu.oop3.entities.Customer;

import java.util.List;

public interface CustomerRepository {
    Customer create(Customer customer);

    Customer findById(int id);

    List<Customer> findAll();

    void update(Customer customer);

    void delete(int id);
}
