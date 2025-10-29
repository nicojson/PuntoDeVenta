package tecnm.celaya.edu.mx.puntodeventa.viewmodel;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import tecnm.celaya.edu.mx.puntodeventa.model.Customer;
import tecnm.celaya.edu.mx.puntodeventa.model.Customer;
import tecnm.celaya.edu.mx.puntodeventa.service.CustomerService;

import java.util.List;

public class CustomerViewModel {
    private final CustomerService service;
    private final ObservableList<Customer> customers = FXCollections.observableArrayList();

    public CustomerViewModel(CustomerService service) {
        this.service = service;
    }

    public ObservableList<Customer> getCustomers() {
        return customers;
    }

    public void loadCustomers() {
        Task<List<Customer>> task = service.loadCustomersTask();
        task.setOnSucceeded(e -> customers.setAll(task.getValue()));
        new Thread(task).start();
    }

    public void addCustomer(String first_name, String last_name, String email, String phone, String address, String gender) {
        Customer c = new Customer(0, first_name, last_name, email, phone, address, gender);
        Task<Void> task = service.addCustomerTask(c);
        task.setOnSucceeded(e -> loadCustomers());
        new Thread(task).start();
    }
}

