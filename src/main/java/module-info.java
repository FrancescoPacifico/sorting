module it.francesco.sorting {

    requires javafx.controls;
    requires javafx.fxml;

    exports it.francesco.sorting.app;
    opens it.francesco.sorting.app to javafx.fxml;

}