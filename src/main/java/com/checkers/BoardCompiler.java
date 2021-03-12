package com.checkers;

import java.util.HashMap;
import java.util.Map;

public class BoardCompiler {

    private Map<PiecePosition, PieceType> board = new HashMap<>();

    private final NormalMoves normalMoves = new NormalMoves(this);

    private final NormalCaptures normalCaptures;

    private boolean turn = true;

    private PiecePosition pickedPosition;

    public BoardCompiler() {

        PiecesInitialPositions piecesInitialPositions = new PiecesInitialPositions();
        board.putAll(piecesInitialPositions.setUpPieces());

        this.normalCaptures = new NormalCaptures(this);
    }

    public PieceType getPiece(PiecePosition position) {
        return board.get(position);
    }

    public boolean isFieldNull(PiecePosition position) {
        return board.get(position) == null;
    }

    public void handleMove(PiecePosition position) {

        if(turn) {

            normalCaptures.calculateAllPossibleCaptures(PieceType.Color.WHITE);

            if(!normalCaptures.getAllPossibleCaptures().isEmpty()) {

                if((normalCaptures.getAllPiecesWhichCanCapture().contains(position))
                        && board.get(position).getPieceColor()== PieceType.Color.WHITE) {

                    PieceMover.pickPiece(board, position, pickedPosition, true);
                    pickedPosition = position;

                    normalCaptures.getPositionsAfterCapturing().forEach(BoardDrawer::removePiece);

                    if(board.get(position).getPieceType().isNormal()) {

                        normalCaptures.captureCalculator(position);
                        normalCaptures.getPositionsAfterCapturing().forEach(BoardDrawer::highlightMove);

                    } else {

                        normalCaptures.clear();

                    }

                } else if((normalCaptures.getPositionsAfterCapturing().contains(position)
                        && board.get(pickedPosition).getPieceType().isNormal())) {

                    PieceMover.capturePiece(board, position, pickedPosition, normalCaptures);
                    pickedPosition = position;

                    if(normalCaptures.getPositionsAfterCapturing().isEmpty()) {

                        turn = false;

                        endCapture();
                    }
                }
            } else {

                if(board.get(position) != null
                        && board.get(position).getPieceColor() == PieceType.Color.WHITE) {

                    PieceMover.pickPiece(board, position, pickedPosition, true);
                    pickedPosition = position;

                    normalMoves.getAllPossibleHumanMoves().forEach(BoardDrawer::removePiece);

                    normalMoves.clear();

                    if(board.get(position).getPieceType() == PieceType.Type.NORMAL) {

                        normalMoves.normalMoveCalculator(position, true);
                        normalMoves.getAllPossibleHumanMoves().forEach(BoardDrawer::highlightMove);

                    }

                } else if(normalMoves.getAllPossibleHumanMoves().contains(position)
                        && pickedPosition != null) {

                    normalMoves.getAllPossibleHumanMoves().forEach(BoardDrawer::removePiece);

                    PieceMover.movePiece(board, position, pickedPosition);

                    turn = false;

                    endMove();

                }
            }
        } else {
            normalCaptures.calculateAllPossibleCaptures(PieceType.Color.BLACK);

            if(!normalCaptures.getAllPossibleCaptures().isEmpty()) {

                if((normalCaptures.getAllPiecesWhichCanCapture().contains(position))
                        && board.get(position).getPieceColor()== PieceType.Color.BLACK) {

                    PieceMover.pickPiece(board, position, pickedPosition, true);
                    pickedPosition = position;

                    normalCaptures.getPositionsAfterCapturing().forEach(BoardDrawer::removePiece);

                    if(board.get(position).getPieceType().isNormal()) {

                        normalCaptures.captureCalculator(position);
                        normalCaptures.getPositionsAfterCapturing().forEach(BoardDrawer::highlightMove);

                    } else {

                        normalCaptures.clear();
                    }

                } else if((normalCaptures.getPositionsAfterCapturing().contains(position)
                        && board.get(pickedPosition).getPieceType().isNormal())) {

                    PieceMover.capturePiece(board, position, pickedPosition, normalCaptures);
                    pickedPosition = position;

                    if(normalCaptures.getPositionsAfterCapturing().isEmpty()) {

                        turn = true;

                        endCapture();

                    }
                }
            } else {

                if(board.get(position) != null
                        && board.get(position).getPieceColor() == PieceType.Color.BLACK) {

                    PieceMover.pickPiece(board, position, pickedPosition, true);
                    pickedPosition = position;

                    normalMoves.getAllPossibleHumanMoves().forEach(BoardDrawer::removePiece);

                    normalMoves.clear();

                    if(board.get(position).getPieceType() == PieceType.Type.NORMAL) {

                        normalMoves.normalMoveCalculator(position, false);
                        normalMoves.getAllPossibleHumanMoves().forEach(BoardDrawer::highlightMove);

                    }

                } else if(normalMoves.getAllPossibleHumanMoves().contains(position)
                        && pickedPosition != null) {

                    normalMoves.getAllPossibleHumanMoves().forEach(BoardDrawer::removePiece);

                    PieceMover.movePiece(board, position, pickedPosition);

                    turn = true;

                    endMove();

                }
            }
        }
    }

    private void endMove() {
        pickedPosition = null;

        normalMoves.clear();
        normalCaptures.clear();
        normalCaptures.clear();
    }

    private void endCapture() {
        pickedPosition = null;

        normalCaptures.clear();
        normalCaptures.clear();
    }

    public Map<PiecePosition, PieceType> getBoard() {
        return board;
    }
}