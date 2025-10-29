package tecnm.celaya.edu.mx.puntodeventa.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.StackPane;
import tecnm.celaya.edu.mx.puntodeventa.model.Customer;
import tecnm.celaya.edu.mx.puntodeventa.model.Product;
import tecnm.celaya.edu.mx.puntodeventa.model.SaleDetail;
import tecnm.celaya.edu.mx.puntodeventa.viewmodel.PuntoDeVentaViewModel;

public class PuntoDeVentaController {

    @FXML
    private StackPane rootPane;
    @FXML
    private JFXComboBox<Customer> customerComboBox;
    @FXML
    private JFXComboBox<Product> productComboBox;
    @FXML
    private JFXTextField quantityTextField;
    @FXML
    private JFXButton addProductButton;
    @FXML
    private TableView<SaleDetail> saleTableView;
    @FXML
    private TableColumn<SaleDetail, String> productNameColumn;
    @FXML
    private TableColumn<SaleDetail, String> productDescriptionColumn;
    @FXML
    private TableColumn<SaleDetail, Integer> quantityColumn;
    @FXML
    private TableColumn<SaleDetail, Double> unitPriceColumn;
    @FXML
    private TableColumn<SaleDetail, Double> subtotalColumn;
    @FXML
    private JFXButton deleteProductButton;
    @FXML
    private Label subtotalLabel;
    @FXML
    private Label totalLabel;
    @FXML
    private JFXButton completeSaleButton;

    private PuntoDeVentaViewModel viewModel;
    private final ObservableList<SaleDetail> saleDetails = FXCollections.observableArrayList();

    public void initialize(PuntoDeVentaViewModel viewModel) {
        this.viewModel = viewModel;
        // Bindings
        customerComboBox.setItems(viewModel.getCustomers());
        productComboBox.setItems(viewModel.getProducts());
        saleTableView.setItems(saleDetails);

        // Cell value factories
        productNameColumn.setCellValueFactory(new PropertyValueFactory<>("productName"));
        productDescriptionColumn.setCellValueFactory(new PropertyValueFactory<>("productDescription"));
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        unitPriceColumn.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        subtotalColumn.setCellValueFactory(new PropertyValueFactory<>("subtotal"));
    }

    @FXML
    private void handleAddProduct() {
        // Lógica para añadir un producto a la tabla
    }

    @FXML
    private void handleDeleteProduct() {
        // Lógica para eliminar un producto de la tabla
    }

    @FXML
    private void handleCompleteSale() {
        // Lógica para finalizar la venta
    }

    @FXML
    private void handleShowCharts() {
        // Lógica para mostrar los gráficos
    }

    @FXML
    private void logout() {
        // Lógica para cerrar sesión
    }
}
