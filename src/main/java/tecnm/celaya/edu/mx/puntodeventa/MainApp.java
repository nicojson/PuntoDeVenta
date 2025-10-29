package tecnm.celaya.edu.mx.puntodeventa;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import tecnm.celaya.edu.mx.puntodeventa.config.DatabaseConnection;
import tecnm.celaya.edu.mx.puntodeventa.controller.PuntoDeVentaController;
import tecnm.celaya.edu.mx.puntodeventa.dao.CustomerDAOImpl;
import tecnm.celaya.edu.mx.puntodeventa.dao.OrdenDAOImpl;
import tecnm.celaya.edu.mx.puntodeventa.dao.ProductDAOImpl;
import tecnm.celaya.edu.mx.puntodeventa.dao.ReportingDAOImpl;
import tecnm.celaya.edu.mx.puntodeventa.service.CustomerService;
import tecnm.celaya.edu.mx.puntodeventa.service.OrdenService;
import tecnm.celaya.edu.mx.puntodeventa.service.ProductService;
import tecnm.celaya.edu.mx.puntodeventa.service.StockMonitor;
import tecnm.celaya.edu.mx.puntodeventa.viewmodel.PuntoDeVentaViewModel;

import java.sql.Connection;

public class MainApp extends Application {
    private StockMonitor stockMonitor;

    @Override
    public void start(Stage stage) throws Exception {
        Connection conn = DatabaseConnection.getConnection();
        
        // DAOs
        var customerDAO = new CustomerDAOImpl(conn);
        var productDAO = new ProductDAOImpl(conn);
        var ordenDAO = new OrdenDAOImpl(conn);
        var reportingDAO = new ReportingDAOImpl(conn);
        
        // Services
        var customerService = new CustomerService(customerDAO);
        var productService = new ProductService(productDAO);
        var ordenService = new OrdenService(ordenDAO);
        
        // ViewModel
        var viewModel = new PuntoDeVentaViewModel(customerService, productService, ordenService);

        FXMLLoader loader = new FXMLLoader(getClass().getResource("main-view.fxml"));
        Scene scene = new Scene(loader.load());
        PuntoDeVentaController controller = loader.getController();
        controller.initialize(viewModel, reportingDAO);

        // Stock Monitor
        stockMonitor = new StockMonitor(productService);
        stockMonitor.startMonitoring();

        stage.setTitle("Punto de Venta");
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void stop() throws Exception {
        if (stockMonitor != null) {
            stockMonitor.stopMonitoring();
        }
        super.stop();
    }

    public static void main(String[] args) {
        launch();
    }
}
