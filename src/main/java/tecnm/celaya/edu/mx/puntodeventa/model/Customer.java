package tecnm.celaya.edu.mx.puntodeventa.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Customer {
    private final IntegerProperty id = new SimpleIntegerProperty();
    private final StringProperty first_name = new SimpleStringProperty();
    private final StringProperty last_name = new SimpleStringProperty();
    private final StringProperty email = new SimpleStringProperty();
    private final StringProperty phone = new SimpleStringProperty();
    private final StringProperty address = new SimpleStringProperty();
    private final StringProperty gender = new SimpleStringProperty();

    public Customer(int id, String first_name, String last_name, String email, String phone, String address, String gender) {
        this.id.set(id);
        this.first_name.set(first_name);
        this.last_name.set(last_name);
        this.email.set(email);
        this.phone.set(phone);
        this.address.set(address);
        this.gender.set(gender);
    }

    public int getId() { return id.get(); }
    public void setId(int id) { this.id.set(id); }
    public IntegerProperty idProperty() { return id; }

    public String getFirstName() { return first_name.get(); }
    public void setFirstName(String first_name) { this.first_name.set(first_name); }
    public StringProperty firstNameProperty() { return first_name; }

    public String getLastName() { return last_name.get(); }
    public void setName(String last_name) { this.last_name.set(last_name); }
    public StringProperty lastNameProperty() { return last_name; }

    public String getEmail() { return email.get(); }
    public void setEmail(String email) { this.email.set(email); }
    public StringProperty emailProperty() { return email; }

    public String getPhone() { return phone.get(); }
    public void setPhone(String phone) { this.phone.set(phone); }
    public StringProperty phoneProperty() { return phone; }

    public String getAddress() { return address.get(); }
    public void setAddress(String address) { this.address.set(address); }
    public StringProperty addressProperty() { return address; }

    public String getGender() { return gender.get(); }
    public void setGender(String gender) { this.gender.set(gender); }
    public StringProperty genderProperty() { return gender; }

}

