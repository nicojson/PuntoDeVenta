package tecnm.celaya.edu.mx.puntodeventa.model;

import java.time.LocalDateTime;

public class Orden {
    private int id;
    private int id_custumer; // Coincide con el script
    private String estatus; // Coincide con el script
    private LocalDateTime fecha_creacion; // Coincide con el script
    private double total; // El total no está en la tabla Orden, pero es útil tenerlo en el objeto

    public Orden(int id, int id_custumer, String estatus, LocalDateTime fecha_creacion, double total) {
        this.id = id;
        this.id_custumer = id_custumer;
        this.estatus = estatus;
        this.fecha_creacion = fecha_creacion;
        this.total = total;
    }

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getId_custumer() { return id_custumer; }
    public void setId_custumer(int id_custumer) { this.id_custumer = id_custumer; }

    public String getEstatus() { return estatus; }
    public void setEstatus(String estatus) { this.estatus = estatus; }

    public LocalDateTime getFecha_creacion() { return fecha_creacion; }
    public void setFecha_creacion(LocalDateTime fecha_creacion) { this.fecha_creacion = fecha_creacion; }

    public double getTotal() { return total; }
    public void setTotal(double total) { this.total = total; }
}
