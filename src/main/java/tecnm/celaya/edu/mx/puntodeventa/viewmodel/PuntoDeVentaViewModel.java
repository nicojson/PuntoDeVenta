package tecnm.celaya.edu.mx.puntodeventa.viewmodel;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import tecnm.celaya.edu.mx.puntodeventa.model.Customer;
import tecnm.celaya.edu.mx.puntodeventa.model.Detalle;
import tecnm.celaya.edu.mx.puntodeventa.model.Orden;
import tecnm.celaya.edu.mx.puntodeventa.model.Product;
import tecnm.celaya.edu.mx.puntodeventa.service.CustomerService;
import tecnm.celaya.edu.mx.puntodeventa.service.OrdenService;
import tecnm.celaya.edu.mx.puntodeventa.service.ProductService;

import java.util.List;

public class PuntoDeVentaViewModel {

    private final CustomerService customerService;
    private final ProductService productService;
    private final OrdenService ordenService;

    private final ObservableList<Customer> customers = FXCollections.observableArrayList();
    private final ObservableList<Product> products = FXCollections.observableArrayList();

    public PuntoDeVentaViewModel(CustomerService customerService, ProductService productService, OrdenService ordenService) {
        this.customerService = customerService;
        this.productService = productService;
        this.ordenService = ordenService;
        loadInitialData();
    }

    public void loadInitialData() {
        Task<Void> loadDataTask = new Task<>() {
            @Override
            protected Void call() throws Exception {
                customers.setAll(customerService.getAllCustomers());
                products.setAll(productService.getAllProducts());
                return null;
            }
        };
        new Thread(loadDataTask).start();
    }

    public ObservableList<Customer> getCustomers() {
        return customers;
    }

    public ObservableList<Product> getProducts() {
        return products;
    }

    public Integer createOrden(Orden orden, List<Detalle> detalles) {
        return ordenService.createOrden(orden, detalles);
    }
}
