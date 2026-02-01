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

    public class paymentRepository implements Repository<Payment> {

        private List<Payment> payments = new ArrayList<>();

        @Override
        public void save(Payment payment) {
            payments.add(payment);
            System.out.println("Saving payment for customerId: " + payment.getCustomerId() + ", amount: " + payment.getAmount());
        }

        @Override
        public List<Payment> findAll() {
            System.out.println("Returning all payments");
            return payments;
        }

        @Override
        public Optional<Payment> findById(int id) {
            return payments.stream()
                    .filter(p -> p.getId() == id)
                    .findFirst();
        }

        @Override
        public void delete(int id) {
            payments.removeIf(p -> p.getId() == id);
            System.out.println("Deleted payment with id: " + id);
        }
    }
}
