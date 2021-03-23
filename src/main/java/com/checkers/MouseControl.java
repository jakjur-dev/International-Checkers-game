package com.checkers;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

public class MouseControl {

    private final BoardCompiler boardCompiler;

    public MouseControl(BoardCompiler boardCompiler) {
        this.boardCompiler = boardCompiler;
    }

    private final EventHandler<MouseEvent> mouseClick = new EventHandler<MouseEvent>() {

        @Override
        public void handle(MouseEvent event) {

            PiecePosition clickPosition = new PiecePosition((int) ((event.getX() - 45) / 85), (int) ((event.getY() - 45) / 85));

            if(!clickPosition.isValidPosition()) {
                return;
            }

            boardCompiler.handleMove(clickPosition);
        }
    };

    public EventHandler<MouseEvent> getMouseClick() {
        return mouseClick;
    }

}