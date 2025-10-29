package tecnm.celaya.edu.mx.puntodeventa.service;

import tecnm.celaya.edu.mx.puntodeventa.dao.SaleDAO;
import tecnm.celaya.edu.mx.puntodeventa.model.Sale;
import tecnm.celaya.edu.mx.puntodeventa.model.SaleDetail;

import java.util.List;

public class SaleService {
    private final SaleDAO saleDAO;

    public SaleService(SaleDAO saleDAO) {
        this.saleDAO = saleDAO;
    }

    public Integer createSale(Sale sale, List<SaleDetail> details) {
        // Aquí se podría añadir lógica de negocio adicional antes de guardar la venta
        return saleDAO.saveSale(sale, details);
    }
}
