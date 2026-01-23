package edu.aitu.oop3.service;

import edu.aitu.oop3.model.Customer;
import edu.aitu.oop3.model.Payment;
import edu.aitu.oop3.repository.jdbc.CustomerRepository;
import edu.aitu.oop3.repository.jdbc.PaymentRepository;

public class RentalService {
    private CustomerRepository customerRepository;
    private PaymentRepository paymentRepository;


    public RentalService(CustomerRepository customerRepository, PaymentRepository paymentRepository) {
        this.customerRepository = customerRepository;
        this.paymentRepository = paymentRepository;
    }

    public boolean createRental(int customerId, double amount, String paymentDate) {
        Customer customer = customerRepository.getCustomerById(customerId);

        if (customer != null) {
            Payment payment = new Payment(0, customerId, amount, paymentDate);
            try {
                paymentRepository.addPayment(payment);
                System.out.println("Rental created successfully for " + customer.getName());
                return true;
            } catch (Exception e) {
                System.out.println("Error while creating rental payment: " + e.getMessage());
                return false;
            }
        }

        System.out.println("Customer not found!");
        return false;
    }
}


