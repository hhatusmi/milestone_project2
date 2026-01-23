package edu.aitu.oop3.repository.jdbc;

import edu.aitu.oop3.model.Customer;
import edu.aitu.oop3.db.IDB;

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
}
