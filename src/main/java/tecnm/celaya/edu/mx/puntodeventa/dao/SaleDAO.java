package tecnm.celaya.edu.mx.puntodeventa.dao;

import tecnm.celaya.edu.mx.puntodeventa.model.Sale;
import tecnm.celaya.edu.mx.puntodeventa.model.SaleDetail;

import java.util.List;

public interface SaleDAO {
    Integer saveSale(Sale sale, List<SaleDetail> details);
}
