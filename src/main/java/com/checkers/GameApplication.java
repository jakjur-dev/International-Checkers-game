/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.checkers;

import javafx.application.Application;
import javafx.scene.*;
import javafx.scene.canvas.*;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class GameApplication extends Application {

    private static Stage primaryStage = new Stage();
    private final BoardCompiler boardCompiler = new BoardCompiler();
    private final BoardDrawer boardDrawer = new BoardDrawer(boardCompiler);
    private final MouseControl mouseControl = new MouseControl(boardCompiler);

    @Override
    public void start(Stage primaryStage) {

        GameApplication.primaryStage = primaryStage;
        Scene scene = new Scene(boardDrawer.getBorderPane(), 900, 900, Color.BLACK);
        scene.addEventFilter(MouseEvent.MOUSE_CLICKED, mouseControl.getMouseClick());

        primaryStage.setTitle("Checkers Game");
        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.setResizable(false);
    }

    public static void main (String[] args){
        launch(args);
    }

    public static void close() {
        primaryStage.close();
    }
}