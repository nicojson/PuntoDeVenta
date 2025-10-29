package tecnm.celaya.edu.mx.puntodeventa.model;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Detalle {
    private final IntegerProperty id = new SimpleIntegerProperty();
    private final IntegerProperty id_orden = new SimpleIntegerProperty();
    private final IntegerProperty id_product = new SimpleIntegerProperty();
    private final StringProperty productName = new SimpleStringProperty(); // Campo para la vista
    private final IntegerProperty cantidad = new SimpleIntegerProperty();
    private final DoubleProperty precio_venta = new SimpleDoubleProperty();
    private final DoubleProperty subtotal = new SimpleDoubleProperty(); // Campo para la vista

    public Detalle(int id, int id_orden, int id_product, String productName, int cantidad, double precio_venta) {
        this.id.set(id);
        this.id_orden.set(id_orden);
        this.id_product.set(id_product);
        this.productName.set(productName);
        this.cantidad.set(cantidad);
        this.precio_venta.set(precio_venta);
        this.subtotal.set(cantidad * precio_venta);
    }

    public int getId() { return id.get(); }
    public IntegerProperty idProperty() { return id; }

    public int getId_orden() { return id_orden.get(); }
    public IntegerProperty id_ordenProperty() { return id_orden; }

    public int getId_product() { return id_product.get(); }
    public IntegerProperty id_productProperty() { return id_product; }

    public String getProductName() { return productName.get(); }
    public StringProperty productNameProperty() { return productName; }

    public int getCantidad() { return cantidad.get(); }
    public IntegerProperty cantidadProperty() { return cantidad; }

    public double getPrecio_venta() { return precio_venta.get(); }
    public DoubleProperty precio_ventaProperty() { return precio_venta; }

    public double getSubtotal() { return subtotal.get(); }
    public DoubleProperty subtotalProperty() { return subtotal; }
}
