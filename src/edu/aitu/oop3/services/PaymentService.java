package edu.aitu.oop3.services;

import edu.aitu.oop3.entities.Payment;
import edu.aitu.oop3.repository.PaymentRepository;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class PaymentService {

    private final PaymentRepository paymentRepository;

    public PaymentService() {
        this.paymentRepository = new PaymentRepository();
    }

    public Payment processPayment(int rentalId, double amount) throws SQLException {
        Payment payment = new Payment(rentalId, amount, LocalDate.now(), "PAID");
        return paymentRepository.create(payment);
    }

    public Payment findPaymentById(int id) throws SQLException {
        return paymentRepository.findById(id);
    }

    public List<Payment> listPayments() throws SQLException {
        return paymentRepository.findAll();
    }

    public void updatePayment(Payment payment) throws SQLException {
        paymentRepository.update(payment);
    }

    public void deletePayment(int id) throws SQLException {
        paymentRepository.delete(id);
    }
}
