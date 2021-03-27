package com.checkers.menubar;

import com.checkers.BoardCompiler;
import com.checkers.NewGame;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class SaveLoadWindow {

    private final BoardCompiler board;

    public SaveLoadWindow(BoardCompiler board) {

        this.board = board;

        HBox top = new HBox();
        HBox bottom = new HBox();

        Stage primaryStage = new Stage();

        Label label = new Label("Save/Load the game?");

        Button button1 = new Button("Save");
        button1.setPrefSize(120,20);

        Button button2 = new Button("Load");
        button2.setPrefSize(120,20);

        button1.setOnAction(e -> {
            save();
            primaryStage.close();
        });

        button2.setOnAction(e -> {
            load();
            primaryStage.close();
        });

        HBox.setMargin(label, new Insets(15, 20, 0, 70));
        HBox.setMargin(button1, new Insets(10, 50, 20, 25));
        HBox.setMargin(button2, new Insets(10, 20, 20, 0));

        top.getChildren().addAll(label);
        bottom.getChildren().addAll(button1, button2);

        BorderPane borderPane = new BorderPane();
        borderPane.setTop(top);
        borderPane.setBottom(bottom);
        Scene scene = new Scene(borderPane, 250, 100);

        primaryStage.setTitle("Save/Load");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void save() {

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Save Game");
        alert.setHeaderText(null);
        alert.setContentText("Game Saved!");

        board.getSaveLoadGame().saveGame();

        alert.showAndWait();
    }

    public void load() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Load Game");
        alert.setHeaderText(null);
        alert.setContentText("Are you sure?" +
                "\n\nIf there is no game saved, a new game will start!");

        NewGame.newGameWindow(alert);
    }

    public static void loadInfo() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Load Game");
        alert.setHeaderText(null);
        alert.setContentText("The last saved game has been Loaded!" +
                "\n\nIf you want to play new game, click New Game button in Game Menu.");
        alert.showAndWait();
    }
}
