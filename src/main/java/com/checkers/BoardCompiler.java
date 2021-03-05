package com.checkers;

import java.util.HashMap;
import java.util.Map;

public class BoardCompiler {

    private final Map<PiecePosition, PieceType> board = new HashMap<>();

    private BlackPiecePositions blackPieces = new BlackPiecePositions();
    private WhitePiecePositions whitePieces = new WhitePiecePositions();

    public BoardCompiler() {
        board.putAll(whitePieces.setUpPieces());
        board.putAll(blackPieces.setUpPieces());
    }

    public Map<PiecePosition, PieceType> getBoard() {
        return board;
    }
}
