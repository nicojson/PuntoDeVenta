package tecnm.celaya.edu.mx.puntodeventa.model;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Product {
    private final IntegerProperty id = new SimpleIntegerProperty();
    private final StringProperty name = new SimpleStringProperty();
    private final StringProperty description = new SimpleStringProperty();
    private final DoubleProperty price = new SimpleDoubleProperty();
    private final IntegerProperty quantity = new SimpleIntegerProperty(); // El campo principal es quantity
    private final IntegerProperty categoryId = new SimpleIntegerProperty();

    public Product(int id, String name, String description, double price, int quantity, Integer categoryId) {
        this.id.set(id);
        this.name.set(name);
        this.description.set(description);
        this.price.set(price);
        this.quantity.set(quantity);
        if (categoryId != null) {
            this.categoryId.set(categoryId);
        }
    }

    public int getId() { return id.get(); }
    public IntegerProperty idProperty() { return id; }

    public String getName() { return name.get(); }
    public StringProperty nameProperty() { return name; }

    public String getDescription() { return description.get(); }
    public StringProperty descriptionProperty() { return description; }

    public double getPrice() { return price.get(); }
    public DoubleProperty priceProperty() { return price; }

    // Métodos para quantity (el nombre correcto según la BD)
    public int getQuantity() { return quantity.get(); }
    public void setQuantity(int quantity) { this.quantity.set(quantity); }
    public IntegerProperty quantityProperty() { return quantity; }

    // --- LÓGICA AÑADIDA ---
    // Métodos alias para "stock" para mantener compatibilidad.
    // Devuelven el valor de "quantity".
    public int getStock() {
        return getQuantity();
    }

    public void setStock(int stock) {
        setQuantity(stock);
    }

    public IntegerProperty stockProperty() {
        return quantityProperty();
    }
    // --- FIN DE LA LÓGICA AÑADIDA ---

    // Métodos para categoryId
    public int getCategoryId() { return categoryId.get(); }
    public IntegerProperty categoryIdProperty() { return categoryId; }
}
