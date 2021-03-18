package io.github.JFileRenamer;

import io.github.JFileRenamer.Controller.LauchViewController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }


    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Resources/fxml/LauchView.fxml"));
        primaryStage.setScene(new Scene(loader.load()));
        primaryStage.setTitle("JFileRenamer");
        primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("Resources/icons/Java.png")));
        LauchViewController controller = loader.getController();
        controller.init();

        primaryStage.setMinHeight(500);
        primaryStage.setMinWidth(620);
        primaryStage.setMaxHeight(600);
        primaryStage.setMaxWidth(800);

        primaryStage.show();
    }
}
