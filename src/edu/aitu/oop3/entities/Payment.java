package edu.aitu.oop3.entities;

import java.time.LocalDate;

public class Payment {
    private int id;
    private int rentalId;
    private double amount;
    private LocalDate paymentDate;
    private String status;

    public Payment() {}

    public Payment(int id, int rentalId, double amount, LocalDate paymentDate, String status) {
        this.id = id;
        this.rentalId = rentalId;
        this.amount = amount;
        this.paymentDate = paymentDate;
        this.status = status;
    }

    public Payment(int rentalId, double amount, LocalDate paymentDate, String status) {
        this.rentalId = rentalId;
        this.amount = amount;
        this.paymentDate = paymentDate;
        this.status = status;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getRentalId() { return rentalId; }
    public void setRentalId(int rentalId) { this.rentalId = rentalId; }

    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }

    public LocalDate getPaymentDate() { return paymentDate; }
    public void setPaymentDate(LocalDate paymentDate) { this.paymentDate = paymentDate; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    @Override
    public String toString() {
        return "Payment{id=" + id +
                ", rentalId=" + rentalId +
                ", amount=" + amount +
                ", paymentDate=" + paymentDate +
                ", status='" + status + '\'' + '}';
    }
}

