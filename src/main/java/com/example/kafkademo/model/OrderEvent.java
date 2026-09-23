package com.example.kafkademo.model;

public class OrderEvent {

    // Unique order identifier
    private Long orderId;

    // Customer who placed the order
    private String customerName;

    // Product purchased
    private String product;

    // Total amount of the order
    private Double amount;

    // Default constructor required for JSON serialization/deserialization
    public OrderEvent() {
    }

    // Create an order event with all required details
    public OrderEvent(Long orderId, String customerName,
                      String product, Double amount) {
        this.orderId = orderId;
        this.customerName = customerName;
        this.product = product;
        this.amount = amount;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
}