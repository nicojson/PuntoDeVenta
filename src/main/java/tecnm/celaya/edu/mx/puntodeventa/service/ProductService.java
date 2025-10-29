package tecnm.celaya.edu.mx.puntodeventa.service;

import tecnm.celaya.edu.mx.puntodeventa.dao.ProductDAO;
import tecnm.celaya.edu.mx.puntodeventa.model.Product;

import java.util.List;

public class ProductService {
    private final ProductDAO productDAO;

    public ProductService(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }

    public List<Product> getAllProducts() {
        return productDAO.getAllProducts();
    }

    public List<Product> getLowStockProducts() {
        // Delegamos la lógica de negocio al DAO, pero podría haber lógica adicional aquí
        // Por ejemplo, filtrar productos con stock por debajo de un umbral
        return productDAO.getAllProducts().stream()
                .filter(p -> p.getStock() < 20)
                .toList();
    }

    public boolean restockProduct(int productId, int quantity) {
        Product product = productDAO.getProduct(productId);
        if (product != null) {
            int newStock = product.getStock() + quantity;
            return productDAO.updateStock(productId, newStock);
        }
        return false;
    }
}
