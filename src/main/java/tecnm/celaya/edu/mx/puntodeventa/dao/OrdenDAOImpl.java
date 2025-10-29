package tecnm.celaya.edu.mx.puntodeventa.dao;

import tecnm.celaya.edu.mx.puntodeventa.model.Detalle;
import tecnm.celaya.edu.mx.puntodeventa.model.Orden;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.List;

public class OrdenDAOImpl {
    private final Connection connection;

    public OrdenDAOImpl(Connection connection) {
        this.connection = connection;
    }

    public Integer saveOrden(Orden orden, List<Detalle> detalles) {
        String ordenSql = "INSERT INTO Orden (id_custumer, estatus, fecha_creacion) VALUES (?, ?, ?)";
        String detalleSql = "INSERT INTO Detalle (id_orden, id_product, cantidad, precio_venta) VALUES (?, ?, ?, ?)";
        String updateStockSql = "UPDATE products SET quantity = quantity - ? WHERE id = ?";

        try {
            connection.setAutoCommit(false); // Iniciar transacción

            try (PreparedStatement ordenStmt = connection.prepareStatement(ordenSql, Statement.RETURN_GENERATED_KEYS)) {
                ordenStmt.setInt(1, orden.getId_custumer());
                ordenStmt.setString(2, orden.getEstatus());
                ordenStmt.setTimestamp(3, Timestamp.valueOf(orden.getFecha_creacion()));
                ordenStmt.executeUpdate();

                try (ResultSet generatedKeys = ordenStmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        int ordenId = generatedKeys.getInt(1);

                        try (PreparedStatement detalleStmt = connection.prepareStatement(detalleSql)) {
                            for (Detalle detalle : detalles) {
                                detalleStmt.setInt(1, ordenId);
                                detalleStmt.setInt(2, detalle.getId_product());
                                detalleStmt.setInt(3, detalle.getCantidad());
                                detalleStmt.setDouble(4, detalle.getPrecio_venta());
                                detalleStmt.addBatch();
                            }
                            detalleStmt.executeBatch();
                        }

                        try (PreparedStatement updateStockStmt = connection.prepareStatement(updateStockSql)) {
                            for (Detalle detalle : detalles) {
                                updateStockStmt.setInt(1, detalle.getCantidad());
                                updateStockStmt.setInt(2, detalle.getId_product());
                                updateStockStmt.addBatch();
                            }
                            updateStockStmt.executeBatch();
                        }

                        connection.commit(); // Confirmar transacción
                        return ordenId;
                    }
                }
            }
        } catch (SQLException e) {
            try {
                connection.rollback(); // Revertir transacción
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
