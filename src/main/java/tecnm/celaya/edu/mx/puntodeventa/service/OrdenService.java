package tecnm.celaya.edu.mx.puntodeventa.service;

import tecnm.celaya.edu.mx.puntodeventa.dao.OrdenDAOImpl;
import tecnm.celaya.edu.mx.puntodeventa.model.Detalle;
import tecnm.celaya.edu.mx.puntodeventa.model.Orden;

import java.util.List;

public class OrdenService {
    private final OrdenDAOImpl ordenDAO;

    public OrdenService(OrdenDAOImpl ordenDAO) {
        this.ordenDAO = ordenDAO;
    }

    public Integer createOrden(Orden orden, List<Detalle> detalles) {
        return ordenDAO.saveOrden(orden, detalles);
    }
}
