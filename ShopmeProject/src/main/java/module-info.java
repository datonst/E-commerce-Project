module com.shopme.shopmeproject {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.shopme.shopmeproject to javafx.fxml;
    exports com.shopme.shopmeproject;
}