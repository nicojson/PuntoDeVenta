package tecnm.celaya.edu.mx.puntodeventa.dao;

import tecnm.celaya.edu.mx.puntodeventa.model.Product;

import java.util.List;
import java.util.Map;

public interface ReportingDAO {
    Map<String, Integer> getTop10SoldProducts();
    List<Product> getTop10LowStockProducts();
}
