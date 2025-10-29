package tecnm.celaya.edu.mx.puntodeventa.dao;

import tecnm.celaya.edu.mx.puntodeventa.model.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ReportingDAOImpl implements ReportingDAO {

    private final Connection connection;

    public ReportingDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Map<String, Integer> getTop10SoldProducts() {
        Map<String, Integer> topProducts = new LinkedHashMap<>();
        String sql = "SELECT p.name, SUM(d.cantidad) AS total_sold " +
                     "FROM Detalle d " +
                     "JOIN products p ON d.id_product = p.id " +
                     "GROUP BY p.name " +
                     "ORDER BY total_sold DESC " +
                     "LIMIT 10";
        try (PreparedStatement stmt = connection.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                topProducts.put(rs.getString("name"), rs.getInt("total_sold"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return topProducts;
    }

    @Override
    public List<Product> getTop10LowStockProducts() {
        List<Product> lowStockProducts = new ArrayList<>();
        String sql = "SELECT id, name, description, price, quantity, category_id FROM products ORDER BY quantity ASC LIMIT 10";
        try (PreparedStatement stmt = connection.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                lowStockProducts.add(new Product(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("description"),
                        rs.getDouble("price"),
                        rs.getInt("quantity"),
                        rs.getObject("category_id", Integer.class)
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lowStockProducts;
    }
}
