module com.example.testofcliantandserverjavafx {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.testofcliantandserverjavafx to javafx.fxml;
    exports com.example.testofcliantandserverjavafx;
}