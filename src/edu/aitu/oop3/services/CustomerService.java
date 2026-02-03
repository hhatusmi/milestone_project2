package edu.aitu.oop3.services;

import edu.aitu.oop3.entities.Customer;
import edu.aitu.oop3.repositories.CustomerRepository;

import java.sql.SQLException;
import java.util.List;

public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService() {
        this.customerRepository = new CustomerRepository();
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
