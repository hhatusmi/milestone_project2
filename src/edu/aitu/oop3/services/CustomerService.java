package edu.aitu.oop3.services;

import edu.aitu.oop3.entities.Customer;

import java.sql.SQLException;
import java.util.List;

public class CustomerService {

    private final edu.aitu.oop3.services.CustomerRepository customerRepository;

    public CustomerService() {
        this.customerRepository = new CustomerRepository() {
            @Override
            public Customer create(Customer customer) {
                return null;
            }

            @Override
            public Customer findById(int id) {
                return null;
            }

            @Override
            public List<Customer> findAll() {
                return List.of();
            }

            @Override
            public void update(Customer customer) {

            }

            @Override
            public void delete(int id) {

            }
        };
    }

    public Customer createCustomer(String name, String email, String phoneNumber) throws SQLException {
        Customer customer = new Customer(name, email, phoneNumber);
        return customerRepository.create(customer);
    }

    public Customer findCustomerById(int id) throws SQLException {
        return customerRepository.findById(id);
    }

    public List<Customer> listCustomers() throws SQLException {
        return customerRepository.findAll();
    }

    public void updateCustomer(Customer customer) throws SQLException {
        customerRepository.update(customer);
    }

    public void deleteCustomer(int id) throws SQLException {
        customerRepository.delete(id);
    }
}