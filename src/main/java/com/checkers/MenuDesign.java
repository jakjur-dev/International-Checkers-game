package com.checkers;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;

public class MenuDesign {

    private MenuBar menuBar = new MenuBar();

    private Menu game = new Menu("Game");
    private MenuItem newGame = new MenuItem("New game");

    public MenuDesign() {
        createMenu();
    }

    private void createMenu() {
        menuBar.getMenus().add(game);
        game.getItems().add(newGame);
    }
    public MenuBar getMenuBar() {
        return menuBar;
    }


    public MenuItem getNewGame() {
        return newGame;
    }

}
