package com.checkers;

import com.checkers.NormalCaptures;

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

    public static void capturePiece(Map<PiecePosition, PieceType> board, PiecePosition newPosition, PiecePosition oldPosition, NormalCaptures normalCaptures) {
        PieceType pieceType = board.get(oldPosition);

        PiecePosition kickPosition = findOppositePosition(newPosition, normalCaptures.getAllPossibleCaptures());

        BoardDrawer.addPiece(newPosition, pieceType, false);
        BoardDrawer.removePiece(oldPosition);
        BoardDrawer.removePiece(kickPosition);

        board.put(newPosition, pieceType);
        board.remove(oldPosition);
        board.remove(kickPosition);

        normalCaptures.captureCalculator(newPosition);

        if (!normalCaptures.getPositionsAfterCapturing().isEmpty() && pieceType.getPieceType().isNormal()) {
            normalCaptures.getPositionsAfterCapturing().forEach(BoardDrawer::highlightMove);

            BoardDrawer.removePiece(oldPosition);
            BoardDrawer.addPiece(newPosition, pieceType, true);
        }
    }


    public static PiecePosition findOppositePosition(PiecePosition newPosition, Set<PiecePosition> possibleNormalCaptures) {

        PiecePosition upLeft = new PiecePosition(newPosition.getCol() - 1, newPosition.getRow() - 1);
        PiecePosition downLeft = new PiecePosition(newPosition.getCol() - 1, newPosition.getRow() + 1);
        PiecePosition upRight = new PiecePosition(newPosition.getCol() + 1, newPosition.getRow() - 1);
        PiecePosition downRight = new PiecePosition(newPosition.getCol() + 1, newPosition.getRow() + 1);

        PiecePosition kickPosition = null;

        if(possibleNormalCaptures.contains(upLeft)){
            kickPosition = upLeft;
        }
        if(possibleNormalCaptures.contains(downLeft)) {
            kickPosition = downLeft;
        }
        if(possibleNormalCaptures.contains(upRight)) {
            kickPosition = upRight;
        }
        if(possibleNormalCaptures.contains(downRight)) {
            kickPosition = downRight;
        }

        return kickPosition;
    }
}
