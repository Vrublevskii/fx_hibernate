package com.example.fx_hibernate;

import com.example.fx_hibernate.gui.SceneCreator;
import javafx.application.Application;
import javafx.stage.Stage;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) {
//        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
//        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
//        stage.setTitle("DB search");
//        stage.setScene(scene);
//        stage.show();
        stage.setWidth(1000);
        stage.setHeight(700);
        stage.setScene(new SceneCreator().getScene());
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}