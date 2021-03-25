package com.checkers.menubar;

import com.checkers.AIMoveGenerator;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class DifficultyWindow {

    public DifficultyWindow() {

        VBox layout = new VBox();
        Scene scene = new Scene(layout, 350, 140);

        Stage primaryStage = new Stage();
        primaryStage.setTitle("Difficulty level");
        primaryStage.setScene(scene);
        primaryStage.show();

        Label label = new Label("Please choose difficulty level:");

        RadioButton radio1 = new RadioButton("Easy");
        RadioButton radio2 = new RadioButton("Normal");

        ToggleGroup radioGroup = new ToggleGroup();
        radio1.setToggleGroup(radioGroup);
        radio2.setToggleGroup(radioGroup);

        Button button = new Button("Confirm");
        if (AIMoveGenerator.difficultyLevel == 0) {
            radio1.setSelected(true);
        } else {
            radio2.setSelected(true);
        }

        button.setOnAction(e -> {
            RadioButton temp = (RadioButton) radioGroup.getSelectedToggle();
            if (temp.getText().equals("Easy")) {
                AIMoveGenerator.setDifficultyLevel(0);
            } else {
                AIMoveGenerator.setDifficultyLevel(1);
            }
            primaryStage.close();
        });

        VBox.setMargin(label, new Insets(15, 20, 10, 40));
        VBox.setMargin(radio1, new Insets(0, 20, 10, 40));
        VBox.setMargin(radio2, new Insets(0, 20, 10, 40));
        VBox.setMargin(button, new Insets(0, 20, 0, 270));

        layout.getChildren().addAll(label, radio1, radio2, button);
    }
}
