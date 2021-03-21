package com.checkers;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class QueenCaptures {

    private final BoardCompiler boardCompiler;

    private final Set<PiecePosition> positionsAfterCapturing = new HashSet<>();
    private final Set<PiecePosition> allPossibleQueenCaptures = new HashSet<>();
    private final Set<PiecePosition> allQueensWhichCanCapture = new HashSet<>();

    public QueenCaptures(BoardCompiler boardCompiler) {
        this.boardCompiler = boardCompiler;
    }

    public void calculateAllPossibleQueenCaptures(PieceType.Color color) {

        for (Map.Entry<PiecePosition, PieceType> piece : boardCompiler.getBoard().entrySet()) {
            if (piece.getValue().getPieceColor() == color && piece.getValue().getPieceType().isQueen()) {

                for (int i = 1; i < 10; i++) {
                    PiecePosition upLeft = new PiecePosition(piece.getKey().getCol() - i, piece.getKey().getRow() - i);

                    if (queenCapturePossibilities(piece.getKey(), upLeft, -1, -1)) {
                        break;
                    }
                }

                for (int i = 1; i < 10; i++) {
                    PiecePosition downLeft = new PiecePosition(piece.getKey().getCol() - i, piece.getKey().getRow() + i);

                    if (queenCapturePossibilities(piece.getKey(), downLeft, -1, +1)) {
                        break;
                    }
                }

                for (int i = 1; i < 10; i++) {
                    PiecePosition upRight = new PiecePosition(piece.getKey().getCol() + i, piece.getKey().getRow() - i);

                    if (queenCapturePossibilities(piece.getKey(), upRight, +1, -1)) {
                        break;
                    }
                }

                for (int i = 1; i < 10; i++) {
                    PiecePosition downRight = new PiecePosition(piece.getKey().getCol() + i, piece.getKey().getRow() + i);

                    if (queenCapturePossibilities(piece.getKey(), downRight, +1, +1)) {
                        break;
                    }
                }
            }
        }
    }

    public void queenPositionsAfterCaptureCalculator(PiecePosition position) {
        positionsAfterCapturing.clear();
        allPossibleQueenCaptures.clear();

        for(int i = 1; i < 10; i++) {

            PiecePosition upLeft = new PiecePosition(position.getCol() - i, position.getRow() - i);

            if(queenCapturePossibility(position, upLeft, -1, -1)) {
                break;
            }
        }

        for(int i = 1; i < 10; i++) {

            PiecePosition downLeft = new PiecePosition(position.getCol() - i, position.getRow() + i);

            if(queenCapturePossibility(position, downLeft, -1, +1)) {
                break;
            }
        }

        for(int i = 1; i < 10; i++) {

            PiecePosition upRight = new PiecePosition(position.getCol() + i, position.getRow() - i);

            if(queenCapturePossibility(position, upRight, +1, -1)) {
                break;
            }
        }

        for(int i = 1; i < 10; i++) {

            PiecePosition downRight = new PiecePosition(position.getCol() + i, position.getRow() + i);

            if(queenCapturePossibility(position, downRight, +1, +1)) {
                break;

            }
        }
    }

    private boolean queenCapturePossibility(PiecePosition actualPosition, PiecePosition checkPosition, int col, int row) {
        if(!checkPosition.isValidPosition()
                || !new PiecePosition(checkPosition.getCol() + col, checkPosition.getRow() + row).isValidPosition()) {
            return true;
        } else if (!boardCompiler.isFieldNull(checkPosition)) {
            if(boardCompiler.getPiece(actualPosition).getPieceColor() != boardCompiler.getPiece(checkPosition).getPieceColor()
                    && boardCompiler.isFieldNull(new PiecePosition(checkPosition.getCol() + col, checkPosition.getRow() + row))) {
                allPossibleQueenCaptures.add(checkPosition);
                positionsAfterCapturing.add(new PiecePosition(checkPosition.getCol() + col, checkPosition.getRow() + row));
            }
            return true;
        } else {
            return false;
        }
    }

    private boolean queenCapturePossibilities(PiecePosition actualPosition, PiecePosition checkPosition, int col, int row) {
        if(!checkPosition.isValidPosition()
                || !new PiecePosition(checkPosition.getCol() + col, checkPosition.getRow() + row).isValidPosition()) {
            return true;
        } else if(!boardCompiler.isFieldNull(checkPosition)) {
            if(boardCompiler.getPiece(actualPosition).getPieceColor() != boardCompiler.getPiece(checkPosition).getPieceColor()
                    && boardCompiler.isFieldNull(new PiecePosition(checkPosition.getCol() + col, checkPosition.getRow() + row))) {
                allPossibleQueenCaptures.add(checkPosition);
                allQueensWhichCanCapture.add(actualPosition);
            }
            return true;
        } else {
            return false;
        }
    }

    public Set<PiecePosition> getPositionsAfterCapturing() {
        return positionsAfterCapturing;
    }

    public Set<PiecePosition> getAllPossibleQueenCaptures() {
        return allPossibleQueenCaptures;
    }

    public Set<PiecePosition> getAllQueensWhichCanCapture() {
        return allQueensWhichCanCapture;
    }

    public void clear() {
        positionsAfterCapturing.clear();
        allPossibleQueenCaptures.clear();
        allQueensWhichCanCapture.clear();
    }
}
