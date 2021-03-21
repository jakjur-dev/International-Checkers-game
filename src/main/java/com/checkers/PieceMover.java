package com.checkers;

import java.util.Map;
import java.util.Set;

public class PieceMover {

    public static void pickPiece(Map<PiecePosition, PieceType> board, PiecePosition position, PiecePosition oldPosition, boolean light) {
        PieceType pieceTypeNew = board.get(position);
        PieceType pieceTypeOld = board.get(oldPosition);

        if(oldPosition != null) {
            BoardDrawer.removePiece(oldPosition);
            BoardDrawer.addPiece(oldPosition, pieceTypeOld, !light);
        }

        BoardDrawer.removePiece(position);
        BoardDrawer.addPiece(position, pieceTypeNew, light);
    }

    public static void movePiece(Map<PiecePosition, PieceType> board, PiecePosition newPosition, PiecePosition oldPosition) {

        PieceType pieceType = board.get(oldPosition);

        BoardDrawer.addPiece(newPosition, pieceType, false);
        BoardDrawer.removePiece(oldPosition);

        board.remove(oldPosition);
        board.put(newPosition, pieceType);
    }

    public static void capturePiece(Map<PiecePosition, PieceType> board, PiecePosition newPosition, PiecePosition oldPosition, NormalCaptures normalCaptures, QueenCaptures queenCaptures) {
        PieceType pieceType = board.get(oldPosition);

        PiecePosition kickPosition = findOppositePosition(newPosition, normalCaptures.getAllPossibleCaptures(), queenCaptures.getAllPossibleQueenCaptures());

        BoardDrawer.addPiece(newPosition, pieceType, false);
        BoardDrawer.removePiece(oldPosition);
        BoardDrawer.removePiece(kickPosition);

        board.put(newPosition, pieceType);
        board.remove(oldPosition);
        board.remove(kickPosition);

        normalCaptures.positionsAfterCaptureCalculator(newPosition);
        queenCaptures.queenPositionsAfterCaptureCalculator(newPosition);

        if(!normalCaptures.getPositionsAfterCapturing().isEmpty() && pieceType.getPieceType().isNormal()) {
            normalCaptures.getPositionsAfterCapturing().forEach(BoardDrawer::highlightMove);

            BoardDrawer.removePiece(oldPosition);
            BoardDrawer.addPiece(newPosition, pieceType, true);
        }

        if(!queenCaptures.getPositionsAfterCapturing().isEmpty() && pieceType.getPieceType().isQueen()) {
            queenCaptures.getPositionsAfterCapturing().forEach(BoardDrawer::highlightMove);

            BoardDrawer.removePiece(oldPosition);
            BoardDrawer.addPiece(newPosition, pieceType, true);
        }
    }

    public static PiecePosition findOppositePosition(PiecePosition newPosition, Set<PiecePosition> possibleNormalCaptures, Set<PiecePosition> PossibleQueenCaptures){

        PiecePosition upLeft = new PiecePosition(newPosition.getCol() - 1, newPosition.getRow() - 1);
        PiecePosition downLeft = new PiecePosition(newPosition.getCol() - 1, newPosition.getRow() + 1);
        PiecePosition upRight = new PiecePosition(newPosition.getCol() + 1, newPosition.getRow() - 1);
        PiecePosition downRight = new PiecePosition(newPosition.getCol() + 1, newPosition.getRow() + 1);

        PiecePosition kickPosition = null;

        if(possibleNormalCaptures.contains(upLeft) || PossibleQueenCaptures.contains(upLeft)) {
            kickPosition = upLeft;
        }
        if(possibleNormalCaptures.contains(downLeft) || PossibleQueenCaptures.contains(downLeft)) {
            kickPosition = downLeft;
        }
        if(possibleNormalCaptures.contains(upRight) || PossibleQueenCaptures.contains(upRight)) {
            kickPosition = upRight;
        }
        if(possibleNormalCaptures.contains(downRight) || PossibleQueenCaptures.contains(downRight)) {
            kickPosition = downRight;
        }

        return kickPosition;
    }
}