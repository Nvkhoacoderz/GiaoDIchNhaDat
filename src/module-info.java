module GiaoDichNhadat {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens com.giaodichnhadat.presentation to javafx.fxml;
    opens com.giaodichnhadat.presentation.GiaoDichListView to javafx.base;
    
    exports com.giaodichnhadat.presentation;
    exports com.giaodichnhadat.presistence;
}