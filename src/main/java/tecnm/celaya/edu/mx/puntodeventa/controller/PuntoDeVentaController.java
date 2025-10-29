package tecnm.celaya.edu.mx.puntodeventa.controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.StackPane;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import tecnm.celaya.edu.mx.puntodeventa.dao.ReportingDAO;
import tecnm.celaya.edu.mx.puntodeventa.model.Customer;
import tecnm.celaya.edu.mx.puntodeventa.model.Detalle;
import tecnm.celaya.edu.mx.puntodeventa.model.Orden;
import tecnm.celaya.edu.mx.puntodeventa.model.Product;
import tecnm.celaya.edu.mx.puntodeventa.service.ReceiptGenerator;
import tecnm.celaya.edu.mx.puntodeventa.viewmodel.PuntoDeVentaViewModel;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;

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
    private TableView<Detalle> saleTableView;
    @FXML
    private TableColumn<Detalle, String> productNameColumn;
    @FXML
    private TableColumn<Detalle, Integer> quantityColumn;
    @FXML
    private TableColumn<Detalle, Double> unitPriceColumn;
    @FXML
    private TableColumn<Detalle, Double> subtotalColumn;
    @FXML
    private Label subtotalLabel;
    @FXML
    private Label totalLabel;

    private PuntoDeVentaViewModel viewModel;
    private ReportingDAO reportingDAO;
    private final ObservableList<Detalle> saleDetails = FXCollections.observableArrayList();

    public void initialize(PuntoDeVentaViewModel viewModel, ReportingDAO reportingDAO) {
        this.viewModel = viewModel;
        this.reportingDAO = reportingDAO;
        
        setupCustomerComboBox();
        setupProductComboBox();
        
        customerComboBox.setItems(viewModel.getCustomers());
        productComboBox.setItems(viewModel.getProducts());
        saleTableView.setItems(saleDetails);

        productNameColumn.setCellValueFactory(new PropertyValueFactory<>("productName"));
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("cantidad"));
        unitPriceColumn.setCellValueFactory(new PropertyValueFactory<>("precio_venta"));
        subtotalColumn.setCellValueFactory(new PropertyValueFactory<>("subtotal"));

        saleDetails.addListener((ListChangeListener<Detalle>) c -> updateTotals());
    }

    private void setupProductComboBox() {
        productComboBox.setConverter(new StringConverter<Product>() {
            @Override
            public String toString(Product product) {
                return product == null ? "" : product.getName();
            }

            @Override
            public Product fromString(String string) {
                return null;
            }
        });
    }

    private void setupCustomerComboBox() {
        customerComboBox.setConverter(new StringConverter<Customer>() {
            @Override
            public String toString(Customer customer) {
                return customer == null ? "" : customer.getFirstName() + " " + customer.getLastName();
            }

            @Override
            public Customer fromString(String string) {
                return null;
            }
        });
    }

    @FXML
    private void handleAddProduct() {
        Product selectedProduct = productComboBox.getSelectionModel().getSelectedItem();
        String quantityText = quantityTextField.getText();

        if (selectedProduct == null || quantityText.isEmpty()) {
            showAlert("Error", "Por favor, seleccione un producto y especifique la cantidad.");
            return;
        }

        int quantity;
        try {
            quantity = Integer.parseInt(quantityText);
            if (quantity <= 0) {
                showAlert("Error", "La cantidad debe ser mayor que cero.");
                return;
            }
            if (quantity > selectedProduct.getQuantity()) { // Usar getQuantity
                showAlert("Error", "No hay suficiente stock para el producto seleccionado. Stock disponible: " + selectedProduct.getQuantity());
                return;
            }
        } catch (NumberFormatException e) {
            showAlert("Error", "La cantidad debe ser un número válido.");
            return;
        }

        Detalle detail = new Detalle(0, 0, selectedProduct.getId(), selectedProduct.getName(), quantity, selectedProduct.getPrice());
        saleDetails.add(detail);

        quantityTextField.clear();
        productComboBox.getSelectionModel().clearSelection();
    }

    @FXML
    private void handleDeleteProduct() {
        Detalle selectedDetail = saleTableView.getSelectionModel().getSelectedItem();
        if (selectedDetail != null) {
            saleDetails.remove(selectedDetail);
        }
    }

    @FXML
    private void handleCompleteSale() {
        Customer selectedCustomer = customerComboBox.getSelectionModel().getSelectedItem();
        if (selectedCustomer == null) {
            showAlert("Error", "Por favor, seleccione un cliente.");
            return;
        }

        if (saleDetails.isEmpty()) {
            showAlert("Error", "No hay productos en la venta.");
            return;
        }

        double total = saleDetails.stream().mapToDouble(Detalle::getSubtotal).sum();
        Orden orden = new Orden(0, selectedCustomer.getId(), "pagada", LocalDateTime.now(), total);

        Integer ordenId = viewModel.createOrden(orden, saleDetails);

        if (ordenId != null) {
            showAlert("Éxito", "Venta completada con éxito. ID de orden: " + ordenId);
            generatePdfReceipt(orden, selectedCustomer);
            saleDetails.clear();
            customerComboBox.getSelectionModel().clearSelection();
        } else {
            showAlert("Error", "Hubo un error al procesar la venta.");
        }
    }

    private void generatePdfReceipt(Orden orden, Customer customer) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Guardar Recibo en PDF");
        fileChooser.setInitialFileName("recibo_" + orden.getId() + ".pdf");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Archivos PDF", "*.pdf"));
        File file = fileChooser.showSaveDialog(rootPane.getScene().getWindow());

        if (file != null) {
            try {
                ReceiptGenerator.generateReceipt(orden, customer, saleDetails, file.getAbsolutePath());
                showAlert("Éxito", "El recibo en PDF se ha guardado correctamente.");
            } catch (IOException e) {
                e.printStackTrace();
                showAlert("Error", "No se pudo guardar el recibo en PDF.");
            }
        }
    }

    private void updateTotals() {
        double subtotal = saleDetails.stream().mapToDouble(Detalle::getSubtotal).sum();
        subtotalLabel.setText(String.format("Subtotal: $%.2f", subtotal));
        totalLabel.setText(String.format("Total: $%.2f", subtotal));
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
    
    @FXML
    private void handleShowCharts() {
        try {
            // --- CORRECCIÓN ---
            // Usar una ruta absoluta desde la raíz del classpath
            URL fxmlLocation = getClass().getResource("/tecnm/celaya/edu/mx/puntodeventa/Charts.fxml");
            FXMLLoader loader = new FXMLLoader(fxmlLocation);
            // --- FIN DE LA CORRECCIÓN ---

            Stage chartsStage = new Stage();
            chartsStage.setTitle("Reportes y Gráficos");
            chartsStage.setScene(new Scene(loader.load()));
            chartsStage.initModality(Modality.APPLICATION_MODAL);

            ChartsController chartsController = loader.getController();
            chartsController.initialize(reportingDAO);

            chartsStage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Error", "No se pudo cargar la ventana de gráficos. Verifique que el archivo 'Charts.fxml' existe en la ubicación correcta.");
        }
    }

    @FXML
    private void logout() {
        // Lógica para cerrar sesión
    }
}
