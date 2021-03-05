package com.checkers;

import java.util.HashMap;
import java.util.Map;

public class WhitePiecePositions {

        private final Map<PiecePosition, PieceType> whitePieces = new HashMap<>();


        public Map<PiecePosition, PieceType> setUpPieces() {

            whitePieces.put(new PiecePosition(1,6), new PieceType(PieceType.Color.WHITE, PieceType.Type.NORMAL));
            whitePieces.put(new PiecePosition(3,6), new PieceType(PieceType.Color.WHITE, PieceType.Type.NORMAL));
            whitePieces.put(new PiecePosition(5,6), new PieceType(PieceType.Color.WHITE, PieceType.Type.NORMAL));
            whitePieces.put(new PiecePosition(7,6), new PieceType(PieceType.Color.WHITE, PieceType.Type.NORMAL));
            whitePieces.put(new PiecePosition(9,6), new PieceType(PieceType.Color.WHITE, PieceType.Type.NORMAL));

            whitePieces.put(new PiecePosition(0,7), new PieceType(PieceType.Color.WHITE, PieceType.Type.NORMAL));
            whitePieces.put(new PiecePosition(2,7), new PieceType(PieceType.Color.WHITE, PieceType.Type.NORMAL));
            whitePieces.put(new PiecePosition(4,7), new PieceType(PieceType.Color.WHITE, PieceType.Type.NORMAL));
            whitePieces.put(new PiecePosition(6,7), new PieceType(PieceType.Color.WHITE, PieceType.Type.NORMAL));
            whitePieces.put(new PiecePosition(8,7), new PieceType(PieceType.Color.WHITE, PieceType.Type.NORMAL));

            whitePieces.put(new PiecePosition(1,8), new PieceType(PieceType.Color.WHITE, PieceType.Type.NORMAL));
            whitePieces.put(new PiecePosition(3,8), new PieceType(PieceType.Color.WHITE, PieceType.Type.NORMAL));
            whitePieces.put(new PiecePosition(5,8), new PieceType(PieceType.Color.WHITE, PieceType.Type.NORMAL));
            whitePieces.put(new PiecePosition(7,8), new PieceType(PieceType.Color.WHITE, PieceType.Type.NORMAL));
            whitePieces.put(new PiecePosition(9,8), new PieceType(PieceType.Color.WHITE, PieceType.Type.NORMAL));

            whitePieces.put(new PiecePosition(0,9), new PieceType(PieceType.Color.WHITE, PieceType.Type.NORMAL));
            whitePieces.put(new PiecePosition(2,9), new PieceType(PieceType.Color.WHITE, PieceType.Type.NORMAL));
            whitePieces.put(new PiecePosition(4,9), new PieceType(PieceType.Color.WHITE, PieceType.Type.NORMAL));
            whitePieces.put(new PiecePosition(6,9), new PieceType(PieceType.Color.WHITE, PieceType.Type.NORMAL));
            whitePieces.put(new PiecePosition(8,9), new PieceType(PieceType.Color.WHITE, PieceType.Type.NORMAL));

            return whitePieces;
        }

        public Map<PiecePosition, PieceType> getWhitePiecesMap() {
            return whitePieces;
        }
    }

