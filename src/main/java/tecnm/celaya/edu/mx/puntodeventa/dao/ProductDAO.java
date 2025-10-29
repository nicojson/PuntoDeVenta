package tecnm.celaya.edu.mx.puntodeventa.dao;

import tecnm.celaya.edu.mx.puntodeventa.model.Product;

import java.util.List;

public interface ProductDAO {
    Product getProduct(int id);
    List<Product> getAllProducts();
    boolean addProduct(Product product);
    boolean updateProduct(Product product);
    boolean deleteProduct(int id);
    boolean updateStock(int productId, int newStock);
}
