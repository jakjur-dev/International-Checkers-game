package com.checkers;

import java.util.HashMap;
import java.util.Map;

public class BlackPiecePositions {

    private final Map<PiecePosition, PieceType> blackPieces = new HashMap<>();

    public Map<PiecePosition, PieceType> setUpPieces() {

        blackPieces.put(new PiecePosition(1,0), new PieceType(PieceType.Color.BLACK, PieceType.Type.NORMAL));
        blackPieces.put(new PiecePosition(3,0), new PieceType(PieceType.Color.BLACK, PieceType.Type.NORMAL));
        blackPieces.put(new PiecePosition(5,0), new PieceType(PieceType.Color.BLACK, PieceType.Type.NORMAL));
        blackPieces.put(new PiecePosition(7,0), new PieceType(PieceType.Color.BLACK, PieceType.Type.NORMAL));
        blackPieces.put(new PiecePosition(9,0), new PieceType(PieceType.Color.BLACK, PieceType.Type.NORMAL));

        blackPieces.put(new PiecePosition(0,1), new PieceType(PieceType.Color.BLACK, PieceType.Type.NORMAL));
        blackPieces.put(new PiecePosition(2,1), new PieceType(PieceType.Color.BLACK, PieceType.Type.NORMAL));
        blackPieces.put(new PiecePosition(4,1), new PieceType(PieceType.Color.BLACK, PieceType.Type.NORMAL));
        blackPieces.put(new PiecePosition(6,1), new PieceType(PieceType.Color.BLACK, PieceType.Type.NORMAL));
        blackPieces.put(new PiecePosition(8,1), new PieceType(PieceType.Color.BLACK, PieceType.Type.NORMAL));

        blackPieces.put(new PiecePosition(1,2), new PieceType(PieceType.Color.BLACK, PieceType.Type.NORMAL));
        blackPieces.put(new PiecePosition(3,2), new PieceType(PieceType.Color.BLACK, PieceType.Type.NORMAL));
        blackPieces.put(new PiecePosition(5,2), new PieceType(PieceType.Color.BLACK, PieceType.Type.NORMAL));
        blackPieces.put(new PiecePosition(7,2), new PieceType(PieceType.Color.BLACK, PieceType.Type.NORMAL));
        blackPieces.put(new PiecePosition(9,2), new PieceType(PieceType.Color.BLACK, PieceType.Type.NORMAL));

        blackPieces.put(new PiecePosition(0,3), new PieceType(PieceType.Color.BLACK, PieceType.Type.NORMAL));
        blackPieces.put(new PiecePosition(2,3), new PieceType(PieceType.Color.BLACK, PieceType.Type.NORMAL));
        blackPieces.put(new PiecePosition(4,3), new PieceType(PieceType.Color.BLACK, PieceType.Type.NORMAL));
        blackPieces.put(new PiecePosition(6,3), new PieceType(PieceType.Color.BLACK, PieceType.Type.NORMAL));
        blackPieces.put(new PiecePosition(8,3), new PieceType(PieceType.Color.BLACK, PieceType.Type.NORMAL));

        return blackPieces;
    }

    public Map<PiecePosition, PieceType> getBlackPieces() {
        return blackPieces;
    }
}
