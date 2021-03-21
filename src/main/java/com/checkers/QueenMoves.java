package com.checkers;

import java.util.HashSet;
import java.util.Set;

public class QueenMoves {

    private final BoardCompiler boardCompiler;

    private final Set<PiecePosition> possibleQueenMoves = new HashSet<>();

    public QueenMoves(BoardCompiler boardCompiler) {
        this.boardCompiler = boardCompiler;
    }

    public void normalQueenMoveCalculator(PiecePosition position) {

        possibleQueenMoves.clear();

        for(int i = 1; i < 8; i ++) {
            PiecePosition upLeft = new PiecePosition(position.getCol() - i, position.getRow() - i);

            if(queenNormalMove(upLeft)) {
                possibleQueenMoves.add(upLeft);
            } else {
                break;
            }
        }

        for(int i = 1; i < 8; i ++) {
            PiecePosition downLeft = new PiecePosition(position.getCol() - i, position.getRow() + i);

            if(queenNormalMove(downLeft)) {
                possibleQueenMoves.add(downLeft);
            } else {
                break;
            }
        }

        for(int i = 1; i < 8; i ++) {
            PiecePosition upRight = new PiecePosition(position.getCol() + i, position.getRow() - i);

            if(queenNormalMove(upRight)) {
                possibleQueenMoves.add(upRight);
            } else {
                break;
            }
        }

        for(int i = 1; i < 8; i ++) {
            PiecePosition downRight = new PiecePosition(position.getCol() + i, position.getRow() + i);

            if(queenNormalMove(downRight)) {
                possibleQueenMoves.add(downRight);
            } else {
                break;
            }
        }
    }

    private boolean queenNormalMove(PiecePosition position) {
        return position.isValidPosition() && boardCompiler.isFieldNull(position);
    }

    public Set<PiecePosition> getPossibleQueenMoves() {
        return possibleQueenMoves;
    }
}
