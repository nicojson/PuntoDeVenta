package tecnm.celaya.edu.mx.puntodeventa.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import tecnm.celaya.edu.mx.puntodeventa.model.Customer;
import tecnm.celaya.edu.mx.puntodeventa.viewmodel.CustomerViewModel;

public class CustomerController {
    @FXML private TableView<Customer> table;
    @FXML private TableColumn<Customer, Number> colId;
    @FXML private TableColumn<Customer, String> colFirstName;
    @FXML private TableColumn<Customer, String> colLastName;
    @FXML private TableColumn<Customer, String> colEmail;
    @FXML private TableColumn<Customer, String> colPhone;
    @FXML private TableColumn<Customer, String> colAddress;
    @FXML private TableColumn<Customer, String> colGender;
    @FXML private TextField txtFirstName;
    @FXML private TextField txtLastName;
    @FXML private TextField txtEmail;
    @FXML private TextField txtPhone;
    @FXML private TextField txtAddress;
    @FXML private TextField txtGender;

    private CustomerViewModel viewModel;

    public void initialize(CustomerViewModel vm) {
        this.viewModel = vm;
        table.setItems(vm.getCustomers());
        colId.setCellValueFactory(data -> data.getValue().idProperty());
        colFirstName.setCellValueFactory(data -> data.getValue().firstNameProperty());
        colLastName.setCellValueFactory(data -> data.getValue().lastNameProperty());
        colEmail.setCellValueFactory(data -> data.getValue().emailProperty());
        colPhone.setCellValueFactory(data -> data.getValue().phoneProperty());
        colAddress.setCellValueFactory(data -> data.getValue().addressProperty());
        colGender.setCellValueFactory(data -> data.getValue().genderProperty());
        vm.loadCustomers();
    }

    @FXML
    private void onAddCustomer() {
        String first_name = txtFirstName.getText();
        String last_name = txtLastName.getText();
        String email = txtEmail.getText();
        String phone = txtPhone.getText();
        String address = txtAddress.getText();
        String gender = txtGender.getText();
        if (!first_name.isBlank() && !last_name.isBlank() &&  email.contains("@") && !phone.isBlank() && !address.isBlank() && !gender.isBlank()) {
            viewModel.addCustomer(first_name, last_name, email, phone, address, gender);
            txtFirstName.clear();
            txtLastName.clear();
            txtEmail.clear();
            txtPhone.clear();
            txtAddress.clear();
            txtGender.clear();
        }
    }
}

