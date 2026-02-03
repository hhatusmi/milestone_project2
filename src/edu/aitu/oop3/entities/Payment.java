package edu.aitu.oop3.entities;

import java.time.LocalDateTime;

public class Payment implements Identifiable {

    private long id;
    private long rentalId;
    private double amount;
    private LocalDateTime paidAt;

    public Payment() {}

    public Payment(long rentalId, double amount, LocalDateTime paidAt) {
        this.rentalId = rentalId;
        this.amount = amount;
        this.paidAt = paidAt;
    }

    @Override
    public long getId() { return id; }

    @Override
    public void setId(long id) { this.id = id; }

    public long getRentalId() { return rentalId; }
    public void setRentalId(long rentalId) { this.rentalId = rentalId; }

    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }

    public LocalDateTime getPaidAt() { return paidAt; }
    public void setPaidAt(LocalDateTime paidAt) { this.paidAt = paidAt; }

    @Override
    public String toString() {
        return "Payment{id=" + id + ", rentalId=" + rentalId + ", amount=" + amount + ", paidAt=" + paidAt + "}";
    }
}