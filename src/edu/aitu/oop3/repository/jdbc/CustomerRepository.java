package edu.aitu.oop3.repository.jdbc;

import edu.aitu.oop3.model.Customer;
import edu.aitu.oop3.db.IDB;

import java.util.List;
import java.util.Optional;

public class CustomerRepository {
    private IDB db;

    public CustomerRepository(IDB db) {
        this.db = db;
    }

    public void addCustomer(Customer customer) {
        db.create(customer);
    }

    public Customer getCustomerById(int id) {
        return (Customer) db.read(id);
    }

    public void updateCustomer(Customer customer) {
        db.update(customer);
    }

    public void deleteCustomer(int id) {
        db.delete(id);
    }

    public static Customer getcustomerById(int customerId){ return null; }

    public class customerRepository implements Repository<Customer> {

        @Override
        public void save(Customer customer) {
            System.out.println("Saving customer: " + customer.getName());
        }

        @Override
        public List<Customer> findAll() {
            System.out.println("Returning all customers");
            return null;
        }

        @Override
        public Optional<Customer> findById(int id) {
            System.out.println("Finding customer by id: " + id);
            return Optional.empty();
        }

        @Override
        public void delete(int id) {
            System.out.println("Deleting customer with id: " + id);
        }
    }
}
