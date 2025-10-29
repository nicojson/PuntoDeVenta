package tecnm.celaya.edu.mx.puntodeventa.model;

import java.time.LocalDateTime;

public class Sale {
    private int id;
    private int customerId;
    private LocalDateTime saleDate;
    private double total;

    public Sale(int id, int customerId, LocalDateTime saleDate, double total) {
        this.id = id;
        this.customerId = customerId;
        this.saleDate = saleDate;
        this.total = total;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public LocalDateTime getSaleDate() {
        return saleDate;
    }

    public void setSaleDate(LocalDateTime saleDate) {
        this.saleDate = saleDate;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}
