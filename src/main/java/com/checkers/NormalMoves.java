package com.checkers;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class NormalMoves {


    private final BoardCompiler boardCompiler;

    private final Set<PiecePosition> allPossiblePieceMoves = new HashSet<>();
    private final Set<PiecePosition> allPossibleAIMoves = new HashSet<>();

    public NormalMoves(BoardCompiler boardCompiler) {
        this.boardCompiler = boardCompiler;
    }

    public void normalMoveCalculator(PiecePosition actualPosition, boolean up) {

        allPossiblePieceMoves.clear();

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

    public void allPossibleAIMovesCalculator() {
        allPossibleAIMoves.clear();

        for(Map.Entry<PiecePosition, PieceType> blacks : boardCompiler.getBoard().entrySet()) {
            if(blacks.getValue().getPieceColor().isWhite()) {
                continue;
            }

            allPossiblePieceMoves.clear();

            if(blacks.getValue().getPieceType().isNormal()) {
                normalMoveCalculator(blacks.getKey(), false);
                for(PiecePosition position : allPossiblePieceMoves){
                    if(position != null && position.isValidPosition()) {
                        allPossibleAIMoves.add(blacks.getKey());
                    }
                }
            } else {
                normalMoveCalculator(blacks.getKey(), true);
                normalMoveCalculator(blacks.getKey(), false);
                for(PiecePosition position : allPossiblePieceMoves){
                    if(position != null && position.isValidPosition()) {
                        allPossibleAIMoves.add(blacks.getKey());
                    }
                }
            }
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
