package com.checkers;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;



public class Promoter {
    private final Set<PiecePosition> possiblePromote = new HashSet<>();

    public void promote(Map<PiecePosition, PieceType> board) {
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
