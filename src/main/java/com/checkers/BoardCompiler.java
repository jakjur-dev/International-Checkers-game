package com.checkers;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class BoardCompiler {

    private final Map<PiecePosition, PieceType> board = new HashMap<>();
    private final Set<PiecePosition> possiblePromote = new HashSet<>();

    private final NormalMoves normalMoves = new NormalMoves(this);
    private final NormalCaptures normalCaptures;

    private final QueenMoves queenMoves = new QueenMoves(this);
    private final QueenCaptures queenCaptures;

    private final EndGame endGame = new EndGame(this);

    private boolean turn = true;
    private boolean isKick = false;

    private PiecePosition pickedPosition;

    public BoardCompiler() {

        PiecesInitialPositions piecesInitialPositions = new PiecesInitialPositions();
        board.putAll(piecesInitialPositions.setUpPieces());

        this.queenCaptures = new QueenCaptures(this);
        this.normalCaptures = new NormalCaptures(this);
    }

    public PieceType getPiece(PiecePosition position) {
        return board.get(position);
    }

    public boolean isFieldNull(PiecePosition position) {
        return board.get(position) == null;
    }

    public void handleMove(PiecePosition position) {

        if (turn) {

            normalCaptures.calculateAllPossibleCaptures(PieceType.Color.WHITE);
            queenCaptures.calculateAllPossibleQueenCaptures(PieceType.Color.WHITE);

            if (!normalCaptures.getAllPossibleCaptures().isEmpty() || !queenCaptures.getAllPossibleQueenCaptures().isEmpty()) {

                if ((normalCaptures.getAllPiecesWhichCanCapture().contains(position)
                        || queenCaptures.getAllQueensWhichCanCapture().contains(position))
                        && board.get(position).getPieceColor().isWhite()
                        && !isKick) {

                    PieceMover.pickPiece(board, position, pickedPosition, true);
                    pickedPosition = position;

                    normalCaptures.getPositionsAfterCapturing().forEach(BoardDrawer::removePiece);
                    queenCaptures.getPositionsAfterCapturing().forEach(BoardDrawer::removePiece);

                    if (board.get(position).getPieceType().isNormal()) {

                        queenCaptures.clear();

                        normalCaptures.positionsAfterCaptureCalculator(position);
                        normalCaptures.getPositionsAfterCapturing().forEach(BoardDrawer::highlightMove);

                    } else {

                        normalCaptures.clear();

                        queenCaptures.queenPositionsAfterCaptureCalculator(position);
                        queenCaptures.getPositionsAfterCapturing().forEach(BoardDrawer::highlightMove);

                    }

                } else if ((queenCaptures.getPositionsAfterCapturing().contains(position)
                        && board.get(pickedPosition).getPieceType().isQueen()) || (normalCaptures.getPositionsAfterCapturing().contains(position)
                        && board.get(pickedPosition).getPieceType().isNormal())) {

                    queenCaptures.getPositionsAfterCapturing().forEach(BoardDrawer::removePiece);

                    PieceMover.capturePiece(board, position, pickedPosition, normalCaptures, queenCaptures);
                    pickedPosition = position;

                    if (queenCaptures.getPositionsAfterCapturing().isEmpty()) {

                        turn = false;

                        isKick = false;

                        endCapture();

                    } else {

                        isKick = true;

                    }
                }
            } else {

                if (board.get(position) != null
                        && board.get(position).getPieceColor() == PieceType.Color.WHITE) {

                    PieceMover.pickPiece(board, position, pickedPosition, true);
                    pickedPosition = position;

                    normalMoves.getAllPossiblePieceMoves().forEach(BoardDrawer::removePiece);
                    queenMoves.getPossibleQueenMoves().forEach(BoardDrawer::removePiece);

                    normalMoves.clear();

                    if (board.get(position).getPieceType() == PieceType.Type.NORMAL) {

                        normalMoves.normalMoveCalculator(position, true);
                        normalMoves.getAllPossiblePieceMoves().forEach(BoardDrawer::highlightMove);

                    } else {

                        queenMoves.normalQueenMoveCalculator(position);
                        queenMoves.getPossibleQueenMoves().forEach(BoardDrawer::highlightMove);

                    }

                } else if (normalMoves.getAllPossiblePieceMoves().contains(position)
                        && pickedPosition != null) {

                    normalMoves.getAllPossiblePieceMoves().forEach(BoardDrawer::removePiece);

                    PieceMover.movePiece(board, position, pickedPosition);

                    turn = false;

                    endMove();

                } else if (queenMoves.getPossibleQueenMoves().contains(position)
                        && pickedPosition != null) {

                    queenMoves.getPossibleQueenMoves().forEach(BoardDrawer::removePiece);

                    PieceMover.movePiece(board, position, pickedPosition);

                    turn = false;

                    endMove();
                }
            }
        } else {
            normalCaptures.calculateAllPossibleCaptures(PieceType.Color.BLACK);
            queenCaptures.calculateAllPossibleQueenCaptures(PieceType.Color.BLACK);

            if (!normalCaptures.getAllPossibleCaptures().isEmpty() || !queenCaptures.getAllPossibleQueenCaptures().isEmpty()) {

                if ((normalCaptures.getAllPiecesWhichCanCapture().contains(position)
                        || queenCaptures.getAllQueensWhichCanCapture().contains(position))
                        && board.get(position).getPieceColor() == PieceType.Color.BLACK
                        && !isKick) {

                    PieceMover.pickPiece(board, position, pickedPosition, true);
                    pickedPosition = position;

                    normalCaptures.getPositionsAfterCapturing().forEach(BoardDrawer::removePiece);
                    queenCaptures.getPositionsAfterCapturing().forEach(BoardDrawer::removePiece);

                    if (board.get(position).getPieceType().isNormal()) {

                        queenCaptures.clear();

                        normalCaptures.positionsAfterCaptureCalculator(position);
                        normalCaptures.getPositionsAfterCapturing().forEach(BoardDrawer::highlightMove);

                    } else {

                        normalCaptures.clear();

                        queenCaptures.queenPositionsAfterCaptureCalculator(position);
                        queenCaptures.getPositionsAfterCapturing().forEach(BoardDrawer::highlightMove);

                    }

                } else if ((queenCaptures.getPositionsAfterCapturing().contains(position)
                        && board.get(pickedPosition).getPieceType().isQueen()) || (normalCaptures.getPositionsAfterCapturing().contains(position)
                        && board.get(pickedPosition).getPieceType().isNormal())) {

                    queenCaptures.getPositionsAfterCapturing().forEach(BoardDrawer::removePiece);

                    PieceMover.capturePiece(board, position, pickedPosition, normalCaptures, queenCaptures);
                    pickedPosition = position;

                    if (queenCaptures.getPositionsAfterCapturing().isEmpty()) {

                        turn = true;

                        isKick = false;

                        endCapture();

                    } else {

                        isKick = true;

                    }
                }
            } else {

                if (board.get(position) != null
                        && board.get(position).getPieceColor() == PieceType.Color.BLACK) {

                    PieceMover.pickPiece(board, position, pickedPosition, true);
                    pickedPosition = position;

                    normalMoves.getAllPossiblePieceMoves().forEach(BoardDrawer::removePiece);
                    queenMoves.getPossibleQueenMoves().forEach(BoardDrawer::removePiece);

                    normalMoves.clear();

                    if (board.get(position).getPieceType() == PieceType.Type.NORMAL) {

                        normalMoves.normalMoveCalculator(position, false);
                        normalMoves.getAllPossiblePieceMoves().forEach(BoardDrawer::highlightMove);

                    } else {

                        queenMoves.normalQueenMoveCalculator(position);
                        queenMoves.getPossibleQueenMoves().forEach(BoardDrawer::highlightMove);

                    }

                } else if (normalMoves.getAllPossiblePieceMoves().contains(position)
                        && pickedPosition != null) {

                    normalMoves.getAllPossiblePieceMoves().forEach(BoardDrawer::removePiece);

                    PieceMover.movePiece(board, position, pickedPosition);

                    turn = true;

                    endMove();

                } else if (queenMoves.getPossibleQueenMoves().contains(position)
                        && pickedPosition != null) {

                    queenMoves.getPossibleQueenMoves().forEach(BoardDrawer::removePiece);

                    PieceMover.movePiece(board, position, pickedPosition);

                    turn = true;

                    endMove();
                }
            }

        }
    }


    private void endMove() {
        pickedPosition = null;

        endGame.checkEndGame(getBoard().keySet());
        Promoter.promote(board, possiblePromote);

        normalMoves.clear();
        normalCaptures.clear();
        queenMoves.getPossibleQueenMoves().clear();
        queenCaptures.clear();
    }

    private void endCapture() {
        pickedPosition = null;

        endGame.checkEndGame(getBoard().keySet());
        Promoter.promote(board, possiblePromote);

        normalCaptures.clear();
        queenCaptures.clear();
        normalCaptures.clear();
    }

    public Map<PiecePosition, PieceType> getBoard() {
        return board;
    }
}