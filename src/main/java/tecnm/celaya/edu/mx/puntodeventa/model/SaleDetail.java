package tecnm.celaya.edu.mx.puntodeventa.model;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class SaleDetail {
    private final IntegerProperty id = new SimpleIntegerProperty();
    private final IntegerProperty saleId = new SimpleIntegerProperty();
    private final IntegerProperty productId = new SimpleIntegerProperty();
    private final StringProperty productName = new SimpleStringProperty(); // Nuevo campo
    private final IntegerProperty quantity = new SimpleIntegerProperty();
    private final DoubleProperty unitPrice = new SimpleDoubleProperty();
    private final DoubleProperty subtotal = new SimpleDoubleProperty();

    public SaleDetail(int id, int saleId, int productId, String productName, int quantity, double unitPrice, double subtotal) {
        this.id.set(id);
        this.saleId.set(saleId);
        this.productId.set(productId);
        this.productName.set(productName);
        this.quantity.set(quantity);
        this.unitPrice.set(unitPrice);
        this.subtotal.set(subtotal);
    }

    public int getId() {
        return id.get();
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public int getSaleId() {
        return saleId.get();
    }

    public IntegerProperty saleIdProperty() {
        return saleId;
    }

    public int getProductId() {
        return productId.get();
    }

    public IntegerProperty productIdProperty() {
        return productId;
    }

    public String getProductName() {
        return productName.get();
    }

    public StringProperty productNameProperty() {
        return productName;
    }

    public int getQuantity() {
        return quantity.get();
    }

    public IntegerProperty quantityProperty() {
        return quantity;
    }

    public double getUnitPrice() {
        return unitPrice.get();
    }

    public DoubleProperty unitPriceProperty() {
        return unitPrice;
    }

    public double getSubtotal() {
        return subtotal.get();
    }

    public DoubleProperty subtotalProperty() {
        return subtotal;
    }
}