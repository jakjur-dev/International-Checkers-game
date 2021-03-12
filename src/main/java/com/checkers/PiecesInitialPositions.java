package com.checkers;

import java.util.HashMap;
import java.util.Map;

public class PiecesInitialPositions {
    private final Map<PiecePosition, PieceType> PieceMap = new HashMap<>();

    public Map<PiecePosition, PieceType> setUpPieces() {

        PieceMap.put(new PiecePosition(1,6), new PieceType(PieceType.Color.WHITE, PieceType.Type.NORMAL));
        PieceMap.put(new PiecePosition(3,6), new PieceType(PieceType.Color.WHITE, PieceType.Type.NORMAL));
        PieceMap.put(new PiecePosition(5,6), new PieceType(PieceType.Color.WHITE, PieceType.Type.NORMAL));
        PieceMap.put(new PiecePosition(7,6), new PieceType(PieceType.Color.WHITE, PieceType.Type.NORMAL));
        PieceMap.put(new PiecePosition(9,6), new PieceType(PieceType.Color.WHITE, PieceType.Type.NORMAL));

        PieceMap.put(new PiecePosition(0,7), new PieceType(PieceType.Color.WHITE, PieceType.Type.NORMAL));
        PieceMap.put(new PiecePosition(2,7), new PieceType(PieceType.Color.WHITE, PieceType.Type.NORMAL));
        PieceMap.put(new PiecePosition(4,7), new PieceType(PieceType.Color.WHITE, PieceType.Type.NORMAL));
        PieceMap.put(new PiecePosition(6,7), new PieceType(PieceType.Color.WHITE, PieceType.Type.NORMAL));
        PieceMap.put(new PiecePosition(8,7), new PieceType(PieceType.Color.WHITE, PieceType.Type.NORMAL));

        PieceMap.put(new PiecePosition(1,8), new PieceType(PieceType.Color.WHITE, PieceType.Type.NORMAL));
        PieceMap.put(new PiecePosition(3,8), new PieceType(PieceType.Color.WHITE, PieceType.Type.NORMAL));
        PieceMap.put(new PiecePosition(5,8), new PieceType(PieceType.Color.WHITE, PieceType.Type.NORMAL));
        PieceMap.put(new PiecePosition(7,8), new PieceType(PieceType.Color.WHITE, PieceType.Type.NORMAL));
        PieceMap.put(new PiecePosition(9,8), new PieceType(PieceType.Color.WHITE, PieceType.Type.NORMAL));

        PieceMap.put(new PiecePosition(0,9), new PieceType(PieceType.Color.WHITE, PieceType.Type.NORMAL));
        PieceMap.put(new PiecePosition(2,9), new PieceType(PieceType.Color.WHITE, PieceType.Type.NORMAL));
        PieceMap.put(new PiecePosition(4,9), new PieceType(PieceType.Color.WHITE, PieceType.Type.NORMAL));
        PieceMap.put(new PiecePosition(6,9), new PieceType(PieceType.Color.WHITE, PieceType.Type.NORMAL));
        PieceMap.put(new PiecePosition(8,9), new PieceType(PieceType.Color.WHITE, PieceType.Type.NORMAL));

        PieceMap.put(new PiecePosition(1,0), new PieceType(PieceType.Color.BLACK, PieceType.Type.NORMAL));
        PieceMap.put(new PiecePosition(3,0), new PieceType(PieceType.Color.BLACK, PieceType.Type.NORMAL));
        PieceMap.put(new PiecePosition(5,0), new PieceType(PieceType.Color.BLACK, PieceType.Type.NORMAL));
        PieceMap.put(new PiecePosition(7,0), new PieceType(PieceType.Color.BLACK, PieceType.Type.NORMAL));
        PieceMap.put(new PiecePosition(9,0), new PieceType(PieceType.Color.BLACK, PieceType.Type.NORMAL));

        PieceMap.put(new PiecePosition(0,1), new PieceType(PieceType.Color.BLACK, PieceType.Type.NORMAL));
        PieceMap.put(new PiecePosition(2,1), new PieceType(PieceType.Color.BLACK, PieceType.Type.NORMAL));
        PieceMap.put(new PiecePosition(4,1), new PieceType(PieceType.Color.BLACK, PieceType.Type.NORMAL));
        PieceMap.put(new PiecePosition(6,1), new PieceType(PieceType.Color.BLACK, PieceType.Type.NORMAL));
        PieceMap.put(new PiecePosition(8,1), new PieceType(PieceType.Color.BLACK, PieceType.Type.NORMAL));

        PieceMap.put(new PiecePosition(1,2), new PieceType(PieceType.Color.BLACK, PieceType.Type.NORMAL));
        PieceMap.put(new PiecePosition(3,2), new PieceType(PieceType.Color.BLACK, PieceType.Type.NORMAL));
        PieceMap.put(new PiecePosition(5,2), new PieceType(PieceType.Color.BLACK, PieceType.Type.NORMAL));
        PieceMap.put(new PiecePosition(7,2), new PieceType(PieceType.Color.BLACK, PieceType.Type.NORMAL));
        PieceMap.put(new PiecePosition(9,2), new PieceType(PieceType.Color.BLACK, PieceType.Type.NORMAL));

        PieceMap.put(new PiecePosition(0,3), new PieceType(PieceType.Color.BLACK, PieceType.Type.NORMAL));
        PieceMap.put(new PiecePosition(2,3), new PieceType(PieceType.Color.BLACK, PieceType.Type.NORMAL));
        PieceMap.put(new PiecePosition(4,3), new PieceType(PieceType.Color.BLACK, PieceType.Type.NORMAL));
        PieceMap.put(new PiecePosition(6,3), new PieceType(PieceType.Color.BLACK, PieceType.Type.NORMAL));
        PieceMap.put(new PiecePosition(8,3), new PieceType(PieceType.Color.BLACK, PieceType.Type.NORMAL));

        return PieceMap;
    }
}

