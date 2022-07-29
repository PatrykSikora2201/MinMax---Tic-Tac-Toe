module CircleAndCross {
    requires javafx.fxml;
    requires javafx.controls;
    requires javafx.graphics;

    opens main to javafx.fxml;
    exports main;
}