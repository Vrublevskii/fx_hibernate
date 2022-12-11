package com.example.fx_hibernate.gui;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.HBox;


public class ValuesList {

    private final ObservableList<String> values = FXCollections.observableArrayList();

    private static ValuesList valuesList;
    private static int counter = 1;
    private static int maxSize;

    public static ObservableList<String> getObservableList() {
        if (valuesList == null) {
            valuesList = new ValuesList();
            valuesList.values.add("id");
            valuesList.values.add("name");
            valuesList.values.add("last name");
            valuesList.values.add("id driver");
            maxSize = valuesList.values.size();
        }
        return valuesList.values;
    }

    public static void removeFromList(String value) {
        if (valuesList.values.size() > 1) {
            valuesList.values.remove(valuesList.values.get(valuesList.values.indexOf(value)));
        } else {
            valuesList.values.add(null);
            valuesList.values.remove(valuesList.values.get(valuesList.values.indexOf(value)));
        }
    }

    public static void addToList(Button button) {
        ValuesList.downCounter();
        @SuppressWarnings("unchecked")
        String value = ((ChoiceBox<String>) ((HBox) button.getParent()).getChildren().get(0)).getValue();
        if (value != null) valuesList.values.add(value);
        valuesList.values.remove(null);
    }


    public static int getCounter() {
        return counter;
    }

    public static void upCounter() {
        counter++;
    }

    public static void downCounter() {
        counter--;
    }

    public static int getMaxSize() {
        return maxSize;
    }
}
