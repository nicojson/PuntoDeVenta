package tecnm.celaya.edu.mx.puntodeventa.service;

import javafx.concurrent.Task;
import tecnm.celaya.edu.mx.puntodeventa.model.Customer;
import tecnm.celaya.edu.mx.puntodeventa.dao.CustomerDAO;

import java.util.List;

public class CustomerService {
    private final CustomerDAO dao;

    public CustomerService(CustomerDAO dao) {
        this.dao = dao;
    }

    public List<Customer> getAllCustomers() {
        return dao.getAll();
    }

    public Task<List<Customer>> loadCustomersTask() {
        return new Task<>() {
            @Override
            protected List<Customer> call() {
                return dao.getAll();
            }
        };
    }

    public Task<Void> addCustomerTask(Customer c) {
        return new Task<>() {
            @Override
            protected Void call() {
                dao.insert(c);
                return null;
            }
        };
    }
}
