package com.checkers;

import com.checkers.BoardCompiler;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class NormalCaptures {

    private final BoardCompiler boardCompiler;

    private final Set<PiecePosition> allPossibleCaptures = new HashSet<>();
    private final Set<PiecePosition> allPiecesWhichCanCapture = new HashSet<>();
    private final Set<PiecePosition> positionsAfterCapturing = new HashSet<>();

    public NormalCaptures(BoardCompiler boardCompiler) {
        this.boardCompiler = boardCompiler;
    }

    public void calculateAllPossibleCaptures(PieceType.Color color) {
        allPossibleCaptures.clear();
        allPiecesWhichCanCapture.clear();

        for (Map.Entry<PiecePosition, PieceType> piece : boardCompiler.getBoard().entrySet()) {
            if(piece.getValue().getPieceColor() == color && piece.getValue().getPieceType().isNormal()){

                if(capturePossibility(piece.getKey(), 1, 1)){
                    allPiecesWhichCanCapture.add(piece.getKey());
                }
                if(capturePossibility(piece.getKey(), -1, -1)){
                    allPiecesWhichCanCapture.add(piece.getKey());
                }

                if(capturePossibility(piece.getKey(), -1, 1)){
                    allPiecesWhichCanCapture.add(piece.getKey());
                }
                if(capturePossibility(piece.getKey(), 1, -1)){
                    allPiecesWhichCanCapture.add(piece.getKey());
                }
            }
        }
    }

    public void positionsAfterCaptureCalculator(PiecePosition position) {
        positionsAfterCapturing.clear();
        allPossibleCaptures.clear();

        if(capturePossibility(position, 1, 1)) {
            positionsAfterCapturing.add(new PiecePosition(position.getCol() + 2, position.getRow() + 2));
        }

        if(capturePossibility(position, - 1, - 1)) {
            positionsAfterCapturing.add(new PiecePosition(position.getCol() - 2, position.getRow() - 2));
        }

        if(capturePossibility(position, 1, - 1)) {
            positionsAfterCapturing.add(new PiecePosition(position.getCol() + 2, position.getRow() - 2));
        }

        if(capturePossibility(position, - 1, 1)) {
            positionsAfterCapturing.add(new PiecePosition(position.getCol() - 2, position.getRow() + 2));
        }

    }

    private boolean capturePossibility(PiecePosition actualPosition, int col, int row) {
        if (new PiecePosition(actualPosition.getCol() + col, actualPosition.getRow() + row).isValidPosition()
                && new PiecePosition(actualPosition.getCol() + (col * 2), actualPosition.getRow() + (row * 2)).isValidPosition()
                && !boardCompiler.isFieldNull(new PiecePosition(actualPosition.getCol() + col, actualPosition.getRow() + row))
                && boardCompiler.isFieldNull(new PiecePosition(actualPosition.getCol() + (col * 2), actualPosition.getRow() + (row * 2)))
                && (boardCompiler.getPiece(actualPosition).getPieceColor() != boardCompiler.getPiece(new PiecePosition(actualPosition.getCol() + col, actualPosition.getRow() + row)).getPieceColor())){
            allPossibleCaptures.add(new PiecePosition(actualPosition.getCol() + col, actualPosition.getRow() + row));
            return true;
        } else {
            return false;
        }
    }

    public void clear() {
        allPossibleCaptures.clear();
        allPiecesWhichCanCapture.clear();
        positionsAfterCapturing.clear();
    }

    public Set<PiecePosition> getAllPossibleCaptures() {
        return allPossibleCaptures;
    }

    public Set<PiecePosition> getAllPiecesWhichCanCapture() {
        return allPiecesWhichCanCapture;
    }

    public Set<PiecePosition> getPositionsAfterCapturing() {
        return positionsAfterCapturing;
    }

}