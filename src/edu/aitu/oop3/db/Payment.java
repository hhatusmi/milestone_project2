package edu.aitu.oop3.db;

public class Payment {
    private final int id;
    private final int customerId;
    private double amount;
    private String date;

    public Payment(int id, int customerId, double amount, String date) {
        this.id = id;
        this.customerId = customerId;
        this.amount = amount;
        this.date = date;
    }

    public int getId() { return id; }
    public int getCustomerId() { return customerId; }
    public double getAmount() { return amount; }
    public String getDate() { return date; }

    public void setAmount(double amount) { this.amount = amount; }
    public void setDate(String date) { this.date = date; }
}
