module com.example._2022410_2022572_stickherogame {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;


    opens com.example._2022410_2022572_stickherogame to javafx.fxml;
    exports com.example._2022410_2022572_stickherogame;
}