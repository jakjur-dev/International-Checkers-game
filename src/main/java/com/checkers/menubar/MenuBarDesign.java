package com.checkers.menubar;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;

public class MenuBarDesign {

    private final MenuBar menuBar = new MenuBar();

    private final Menu game = new Menu("Game");
    private final MenuItem newGame = new MenuItem("New game");
    private final MenuItem difficulty = new MenuItem("Difficulty");
    private final MenuItem saveLoad = new MenuItem("Save/Load");
    private final MenuItem ranking = new MenuItem("Ranking");

    public MenuBarDesign() {
        menuBar.getMenus().add(game);
        game.getItems().add(newGame);
        game.getItems().add(difficulty);
        game.getItems().add(saveLoad);
        game.getItems().add(ranking);
        ranking.setOnAction(e -> new RankingWindow().showRanking());
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

    public MenuItem getSaveLoad() {
        return saveLoad;
    }
}
