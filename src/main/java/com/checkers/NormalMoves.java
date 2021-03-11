package com.checkers;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class NormalMoves {

    private final BoardCompiler boardCompiler;

    private final Set<PiecePosition> allPossibleHumanMoves = new HashSet<>();
    private final Set<PiecePosition> allPossibleAIMoves = new HashSet<>();

    public NormalMoves(BoardCompiler boardCompiler) {
        this.boardCompiler = boardCompiler;
    }

    public void normalMoveCalculator(PiecePosition actualPosition, boolean up) {
        int direction = up ? - 1 : 1;

        PiecePosition left = new PiecePosition(actualPosition.getCol() - 1, actualPosition.getRow() + direction);
        PiecePosition right = new PiecePosition(actualPosition.getCol() + 1, actualPosition.getRow() + direction);

        if (left.isValidPosition() && boardCompiler.isFieldNull(left)) {
            allPossibleHumanMoves.add(left);
        }

        if (right.isValidPosition() && boardCompiler.isFieldNull(right)) {
            allPossibleHumanMoves.add(right);
        }
    }

    public Set<PiecePosition> getAllPossibleHumanMoves() {
        return allPossibleHumanMoves;
    }


    public void clear() {
        allPossibleHumanMoves.clear();
        allPossibleAIMoves.clear();
    }
}
