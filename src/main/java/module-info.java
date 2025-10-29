module tecnm.celaya.edu.mx.puntodeventa {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.ikonli.javafx;
    requires com.jfoenix;
    requires kernel;
    requires layout;

    opens tecnm.celaya.edu.mx.puntodeventa to javafx.fxml;
    exports tecnm.celaya.edu.mx.puntodeventa;

    opens tecnm.celaya.edu.mx.puntodeventa.controller to javafx.fxml;
    exports tecnm.celaya.edu.mx.puntodeventa.controller;

    opens tecnm.celaya.edu.mx.puntodeventa.dao to javafx.fxml;
    exports tecnm.celaya.edu.mx.puntodeventa.dao;

    opens tecnm.celaya.edu.mx.puntodeventa.model to javafx.fxml;
    exports tecnm.celaya.edu.mx.puntodeventa.model;



}