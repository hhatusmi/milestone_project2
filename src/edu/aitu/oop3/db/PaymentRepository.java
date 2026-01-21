package edu.aitu.oop3.db;

public class PaymentRepository {
    private final IDB db;

    public PaymentRepository(IDB db) {
        this.db = db;
    }

    public void addPayment(Payment payment) {
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
