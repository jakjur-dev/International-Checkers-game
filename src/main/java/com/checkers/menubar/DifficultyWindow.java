package com.checkers;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import java.util.Optional;

public class DifficultyWindow {

    public DifficultyWindow() {
        Alert alert = new Alert(Alert.AlertType.NONE);
        alert.setTitle("Difficulty");
        alert.setContentText("Set difficulty level");

        ButtonType easy = new ButtonType("Easy");
        ButtonType normal = new ButtonType("Normal");

        alert.getButtonTypes().setAll(easy, normal);

        Optional<ButtonType> result = alert.showAndWait();

        if (result.get() == easy) {
            AIMoveGenerator.setDifficultyLevel(0);
        } else {
            AIMoveGenerator.setDifficultyLevel(1);
        }
    }
}
