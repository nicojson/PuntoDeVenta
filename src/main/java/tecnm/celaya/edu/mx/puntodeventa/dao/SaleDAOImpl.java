package tecnm.celaya.edu.mx.puntodeventa.dao;

import tecnm.celaya.edu.mx.puntodeventa.model.Sale;
import tecnm.celaya.edu.mx.puntodeventa.model.SaleDetail;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.List;

public class SaleDAOImpl implements SaleDAO {
    private final Connection connection;

    public SaleDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Integer saveSale(Sale sale, List<SaleDetail> details) {
        String saleSql = "INSERT INTO sales (customer_id, sale_date, total) VALUES (?, ?, ?)";
        String detailSql = "INSERT INTO sale_details (sale_id, product_id, quantity, unit_price, subtotal) VALUES (?, ?, ?, ?, ?)";
        String updateStockSql = "UPDATE products SET stock = stock - ? WHERE id = ?";

        try {
            connection.setAutoCommit(false); // Iniciar transacción

            // Insertar la venta y obtener el ID generado
            try (PreparedStatement saleStmt = connection.prepareStatement(saleSql, Statement.RETURN_GENERATED_KEYS)) {
                saleStmt.setInt(1, sale.getCustomerId());
                saleStmt.setTimestamp(2, Timestamp.valueOf(sale.getSaleDate()));
                saleStmt.setDouble(3, sale.getTotal());
                saleStmt.executeUpdate();

                try (ResultSet generatedKeys = saleStmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        int saleId = generatedKeys.getInt(1);

                        // Insertar los detalles de la venta
                        try (PreparedStatement detailStmt = connection.prepareStatement(detailSql)) {
                            for (SaleDetail detail : details) {
                                detailStmt.setInt(1, saleId);
                                detailStmt.setInt(2, detail.getProductId());
                                detailStmt.setInt(3, detail.getQuantity());
                                detailStmt.setDouble(4, detail.getUnitPrice());
                                detailStmt.setDouble(5, detail.getSubtotal());
                                detailStmt.addBatch();
                            }
                            detailStmt.executeBatch();
                        }

                        // Actualizar el stock de productos
                        try (PreparedStatement updateStockStmt = connection.prepareStatement(updateStockSql)) {
                            for (SaleDetail detail : details) {
                                updateStockStmt.setInt(1, detail.getQuantity());
                                updateStockStmt.setInt(2, detail.getProductId());
                                updateStockStmt.addBatch();
                            }
                            updateStockStmt.executeBatch();
                        }

                        connection.commit(); // Confirmar transacción
                        return saleId;
                    }
                }
            }
        } catch (SQLException e) {
            try {
                connection.rollback(); // Revertir transacción en caso de error
            } catch (SQLException rollbackEx) {
                rollbackEx.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException finalEx) {
                finalEx.printStackTrace();
            }
        }
        return null;
    }
}