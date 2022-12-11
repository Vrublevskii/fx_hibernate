package com.example.fx_hibernate.gui;

import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Control;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class SceneCreator {

    private VBox vBox;
    private Map<String, String> result;
//
    public Scene getScene() {
        AnchorPane anchorPane = new AnchorPane();
        ObservableList<String> values = ValuesList.getObservableList();

        vBox = new VBox();
        vBox.getChildren().add(getHBboxComponent(getChoiceBox(values), getTextField(), getDeleteButton()));

        Button addButton = new Button();
        addButton.setText("add");
        addButton.setOnAction(event -> {
                    if (!Objects.equals(values.get(0), null) && ValuesList.getCounter() < ValuesList.getMaxSize()) {
                        ValuesList.upCounter();
                        vBox.getChildren().add(getHBboxComponent(getChoiceBox(values), getTextField(), getDeleteButton()));
                    }
                }
        );

        Button searchButton = new Button();
        searchButton.setText("search");
        searchButton.setOnAction(event -> {
                    result = new HashMap<>();
                    for (Node child : vBox.getChildren()) {
                        @SuppressWarnings("unchecked")
                        String key = ((ChoiceBox<String>) ((HBox) child).getChildren().get(0)).getValue();
                        String value = ((TextField) (((HBox) child).getChildren().get(1))).getText();
                        result.put(key, value);
                    }
                }
        );

        anchorPane.getChildren().addAll(vBox, addButton, searchButton);
        AnchorPane.setTopAnchor(addButton, 5d);
        AnchorPane.setTopAnchor(searchButton, 5d);
        AnchorPane.setLeftAnchor(addButton, 390d);
        AnchorPane.setLeftAnchor(searchButton, 435d);
        return new Scene(anchorPane);
    }

    private HBox getHBboxComponent(Control... controls) {
        HBox hBox = new HBox();
        hBox.getChildren().addAll(controls);
        hBox.setPadding(new Insets(5, 10, 5, 10));
        hBox.setSpacing(10);

        return hBox;
    }

    private ChoiceBox<String> getChoiceBox(ObservableList<String> values) {
        ChoiceBox<String> choiceBox = new ChoiceBox<>(values);
        choiceBox.setMinSize(100, 10);
        choiceBox.setOnAction(actionEvent -> ValuesList.removeFromList(choiceBox.getValue())

        );
        return choiceBox;
    }

    private TextField getTextField() {
        TextField textField = new TextField();
        textField.setMinSize(200, 10);
        textField.setPromptText("value");
        return textField;
    }

    private Button getDeleteButton() {
        Button deleteButton = new Button();
        deleteButton.setText("delete");
        deleteButton.setOnAction(
                event -> {
                    if (ValuesList.getCounter() != 1) {
                        ValuesList.addToList(deleteButton);
                        vBox.getChildren().remove(deleteButton.getParent());
                    }
                }
        );

        return deleteButton;
    }

    public Map<String, String> getResult() {
        return result;
    }
}
