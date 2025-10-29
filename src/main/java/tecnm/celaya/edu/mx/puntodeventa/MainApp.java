package tecnm.celaya.edu.mx.puntodeventa;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import tecnm.celaya.edu.mx.puntodeventa.config.DatabaseConnection;
import tecnm.celaya.edu.mx.puntodeventa.controller.CustomerController;
import tecnm.celaya.edu.mx.puntodeventa.dao.CustomerDAOImpl;
import tecnm.celaya.edu.mx.puntodeventa.service.CustomerService;
import tecnm.celaya.edu.mx.puntodeventa.viewmodel.CustomerViewModel;

import java.sql.Connection;

public class MainApp extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        Connection conn = DatabaseConnection.getConnection();
        var dao = new CustomerDAOImpl(conn);
        var service = new CustomerService(dao);
        var viewModel = new CustomerViewModel(service);

        FXMLLoader loader = new FXMLLoader(getClass().getResource("customers.fxml"));
        Scene scene = new Scene(loader.load());
        CustomerController controller = loader.getController();
        controller.initialize(viewModel);

        stage.setTitle("Customer Manager");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}

