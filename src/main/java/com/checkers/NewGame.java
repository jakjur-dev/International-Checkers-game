package com.checkers;

import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

public class NewGame {

    public void start(BoardCompiler boardCompiler) {

        SaveLoadGame saveLoadGame = new SaveLoadGame(boardCompiler);
        saveLoadGame.removeFile();

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("New Game");
        alert.setHeaderText(null);
        alert.setContentText("Are you sure you want to start new game?");

        newGameWindow(alert);
    }

    public static void newGameWindow(Alert alert) {
        ButtonType yes = new ButtonType("Yes");
        ButtonType no = new ButtonType("No");

        alert.getButtonTypes().setAll(yes, no);

        if (alert.showAndWait().get() == yes) {
            Platform.runLater(() -> new GameApplication().start(new Stage()));
            GameApplication.close();
        }
    }
}
