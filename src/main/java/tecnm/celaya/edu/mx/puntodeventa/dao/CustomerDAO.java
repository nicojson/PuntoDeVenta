package tecnm.celaya.edu.mx.puntodeventa.dao;


import tecnm.celaya.edu.mx.puntodeventa.model.Customer;
import tecnm.celaya.edu.mx.puntodeventa.model.Customer;

import java.util.List;

public interface CustomerDAO {
    List<Customer> getAll();
    void insert(Customer c);
}

