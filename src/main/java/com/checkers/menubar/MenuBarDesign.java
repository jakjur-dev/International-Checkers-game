package com.checkers.menubar;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;

public class MenuBarDesign {

    private MenuBar menuBar = new MenuBar();

    private Menu game = new Menu("Game");
    private MenuItem newGame = new MenuItem("New game");
    private MenuItem difficulty = new MenuItem("Difficulty");

    public MenuBarDesign() {
        createMenu();
    }

    private void createMenu() {
        menuBar.getMenus().add(game);
        game.getItems().add(newGame);
        game.getItems().add(difficulty);
    }
    public MenuBar getMenuBar() {
        return menuBar;
    }

    public MenuItem getNewGame() {
        return newGame;
    }

    public MenuItem getDifficulty() {
        return difficulty;
    }
}
