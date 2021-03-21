package com.checkers;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class Promoter {

    static void promote(Map<PiecePosition, PieceType> board, Set<PiecePosition> possiblePromote) {
        possiblePromote.clear();

        Set<PiecePosition> whites = board.keySet().stream()
                .filter(position -> position.getRow() == 0)
                .filter(position -> board.get(position).getPieceColor() == PieceType.Color.WHITE)
                .collect(Collectors.toSet());

        Set<PiecePosition> blacks = board.keySet().stream()
                .filter(position -> position.getRow() == 9)
                .filter(position -> board.get(position).getPieceColor() == PieceType.Color.BLACK)
                .collect(Collectors.toSet());

        possiblePromote.addAll(whites);
        possiblePromote.addAll(blacks);

        for(PiecePosition position : possiblePromote) {
            PieceType pieceType = board.get(position);

            if(pieceType.getPieceColor().isWhite() && pieceType.getPieceType().isNormal()) {
                BoardDrawer.removePiece(position);
                BoardDrawer.addPiece(position, new PieceType(pieceType.getPieceColor(), PieceType.Type.QUEEN), false);

                board.remove(position);
                board.put(position, new PieceType(pieceType.getPieceColor(), PieceType.Type.QUEEN));
            }

            if(pieceType.getPieceColor().isBlack() && pieceType.getPieceType().isNormal()) {
                BoardDrawer.removePiece(position);
                BoardDrawer.addPiece(position, new PieceType(pieceType.getPieceColor(), PieceType.Type.QUEEN), false);

                board.remove(position);
                board.put(position, new PieceType(pieceType.getPieceColor(), PieceType.Type.QUEEN));
            }
        }
    }
}
