package com.checkers.menubar;

import com.checkers.BoardCompiler;
import com.checkers.GameApplication;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

public class EndGameWindow {

    public void whitesWin() {

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("End Game");
        alert.setHeaderText(null);
        alert.setContentText("White Player Win!");

        buttonsCreator(alert);
    }

    public void blacksWin() {

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("End Game");
        alert.setHeaderText(null);
        alert.setContentText("Black Player Win!");

        buttonsCreator(alert);
    }

    public void draw() {

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("End Game");
        alert.setHeaderText(null);
        alert.setContentText("Draw! No one win!");

        buttonsCreator(alert);
    }

    private void buttonsCreator(Alert alert) {
        ButtonType newGame = new ButtonType("New Game");
        ButtonType exit = new ButtonType("Exit");

        alert.getButtonTypes().setAll(newGame, exit);

        if(alert.showAndWait().get() == newGame) {
            Platform.runLater(() -> new GameApplication().start(new Stage()));
            GameApplication.close();
        } else {
            System.exit(0);
        }
    }
}
