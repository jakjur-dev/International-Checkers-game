package com.checkers;

import java.util.HashSet;
import java.util.Set;

public class NormalMoves {


    private final BoardCompiler boardCompiler;

    private final Set<PiecePosition> allPossiblePieceMoves = new HashSet<>();
    private final Set<PiecePosition> allPossibleAIMoves = new HashSet<>();

    public NormalMoves(BoardCompiler boardCompiler) {
        this.boardCompiler = boardCompiler;
    }

    public void normalMoveCalculator(PiecePosition actualPosition, boolean up) {
        int direction = up ? - 1 : 1;

        PiecePosition left = new PiecePosition(actualPosition.getCol() - 1, actualPosition.getRow() + direction);
        PiecePosition right = new PiecePosition(actualPosition.getCol() + 1, actualPosition.getRow() + direction);

        if (left.isValidPosition() && boardCompiler.isFieldNull(left)) {
            allPossiblePieceMoves.add(left);
        }

        if (right.isValidPosition() && boardCompiler.isFieldNull(right)) {
            allPossiblePieceMoves.add(right);
        }
    }

    public Set<PiecePosition> getAllPossiblePieceMoves() {
        return allPossiblePieceMoves;
    }

    public Set<PiecePosition> getAllPossibleAIMoves() {
        return allPossibleAIMoves;
    }

    public void clear() {
        allPossiblePieceMoves.clear();
        allPossibleAIMoves.clear();
    }
}
