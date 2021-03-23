package com.checkers;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

import java.util.Map;
import java.util.Objects;

public class BoardDrawer {

    private final BorderPane borderPane = new BorderPane();
    private static GridPane grid = new GridPane();
    Image boardImage = new Image(ResourceFinder.getPath("board.png"));
    private static final Image highlight = new Image(ResourceFinder.getPath("highlight.png"));

    public BoardDrawer(BoardCompiler boardCompiler) {

        //board background
        BackgroundSize backgroundSize = new BackgroundSize(900, 900, false, false, true, false);
        BackgroundImage backgroundImage = new BackgroundImage(boardImage, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
        new Background(backgroundImage);

        //board layout
        grid = new GridPane();
        grid.setPadding(new Insets(50,50,50,50));
        grid.setBackground(new Background(backgroundImage));

        for(int i = 0; i < 10; i++) {
            ColumnConstraints columnConstraints = new ColumnConstraints();
            columnConstraints.setPrefWidth(800);
            columnConstraints.setHalignment(HPos.CENTER);
            grid.getColumnConstraints().add(columnConstraints);

            RowConstraints rowConstraints = new RowConstraints();
            rowConstraints.setPrefHeight(800);
            rowConstraints.setValignment(VPos.CENTER);
            grid.getRowConstraints().add(rowConstraints);
        }
        grid.setGridLinesVisible(false);

        //create pieces
        for(Map.Entry<PiecePosition, PieceType> pieces : boardCompiler.getBoard().entrySet()){
            addPiece(pieces.getKey(), pieces.getValue(), false);
        }

        //create menu
        borderPane.setCenter(grid);

        MenuDesign menuDesign = new MenuDesign();
        borderPane.setTop(menuDesign.getMenuBar());

        menuDesign.getNewGame().setOnAction(e -> new NewGame().start(boardCompiler));
    }

    protected static void addPiece(PiecePosition position, PieceType pieceType, boolean highlight) {
        ImageView image = new ImageView(ResourceFinder.generateImagePath(pieceType, highlight));
        image.setFitHeight(70);
        image.setFitWidth(70);
        grid.add(image, position.getCol(), position.getRow());
    }

    protected static void removePiece(PiecePosition position) {
        grid.getChildren().removeIf(node -> node instanceof ImageView && Objects.equals(GridPane.getColumnIndex(node), position.getCol())
                && Objects.equals(GridPane.getRowIndex(node), position.getRow()));
    }

    protected static void highlightMove(PiecePosition position) {
        grid.add(new ImageView(highlight), position.getCol(), position.getRow());
    }

    public BorderPane getBorderPane() {
        return borderPane;
    }
}
