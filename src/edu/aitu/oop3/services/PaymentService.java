package edu.aitu.oop3.services;

import edu.aitu.oop3.entities.Payment;
import edu.aitu.oop3.repository.PaymentRepository;

import java.sql.SQLException;
import java.time.LocalDateTime;

public class PaymentService {

    private final PaymentRepository paymentRepository = new PaymentRepository();

    public Payment processPayment(long rentalId, double amount) throws SQLException {
        Payment payment = new Payment(rentalId, amount, LocalDateTime.now());
        return paymentRepository.create(payment);
    }
}