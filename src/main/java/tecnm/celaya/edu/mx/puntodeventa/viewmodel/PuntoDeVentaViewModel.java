package tecnm.celaya.edu.mx.puntodeventa.viewmodel;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import tecnm.celaya.edu.mx.puntodeventa.model.Customer;
import tecnm.celaya.edu.mx.puntodeventa.model.Product;
import tecnm.celaya.edu.mx.puntodeventa.service.CustomerService;
import tecnm.celaya.edu.mx.puntodeventa.service.ProductService;

public class PuntoDeVentaViewModel {

    private final CustomerService customerService;
    private final ProductService productService;

    private final ObservableList<Customer> customers;
    private final ObservableList<Product> products;

    public PuntoDeVentaViewModel(CustomerService customerService, ProductService productService) {
        this.customerService = customerService;
        this.productService = productService;
        customers = FXCollections.observableArrayList(customerService.getAllCustomers());
        products = FXCollections.observableArrayList(productService.getAllProducts());
    }

    public ObservableList<Customer> getCustomers() {
        return customers;
    }

    public ObservableList<Product> getProducts() {
        return products;
    }
}
