package edu.aitu.oop3.repository.jdbc;

import edu.aitu.oop3.db.IDB;
import edu.aitu.oop3.model.Payment;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PaymentRepository {
    private static IDB db = null;

    public PaymentRepository(IDB db) {
        this.db = db;
    }

    public static void addPayment(Payment payment) {
        db.create(payment);
    }

    public Payment getPaymentById(int id) {
        return (Payment) db.read(id);
    }

    public void updatePayment(Payment payment) {
        db.update(payment);
    }

    public void deletePayment(int id) {
        db.delete(id);
    }

}
