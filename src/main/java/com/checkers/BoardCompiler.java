package com.checkers;

import com.checkers.menubar.SaveLoadWindow;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class BoardCompiler {

    private Map<PiecePosition, PieceType> board = new HashMap<>();

    private final SaveLoadGame saveLoadGame = new SaveLoadGame(this);

    private final Promoter promoter = new Promoter();

    private final NormalMoves normalMoves = new NormalMoves(this);
    private final NormalCaptures normalCaptures;

    private final QueenMoves queenMoves = new QueenMoves(this);
    private final QueenCaptures queenCaptures;

    private final EndConditions endConditions = new EndConditions(this);

    private boolean playerTurn = true;
    private boolean capturing = false;

    private final AIMoveGenerator AIMoveGenerator;

    private PiecePosition pickedPosition;

    public BoardCompiler() {
        if(saveLoadGame.fileExists()) {
            saveLoadGame.loadGame();
            saveLoadGame.removeFile();
            SaveLoadWindow.loadInfo();
        } else {
            PiecesInitialPositions piecesInitialPositions = new PiecesInitialPositions();
            board.putAll(piecesInitialPositions.setUpPieces());
        }

        this.queenCaptures = new QueenCaptures(this);
        this.normalCaptures = new NormalCaptures(this);
        this.AIMoveGenerator = new AIMoveGenerator();
    }

    public PieceType getPiece(PiecePosition position) {
        return board.get(position);
    }

    public boolean isFieldNull(PiecePosition position) {
        return board.get(position) == null;
    }

    public void handleMove(PiecePosition position) {

        if (playerTurn) {

            normalCaptures.calculateAllPossibleCaptures(PieceType.Color.WHITE);
            queenCaptures.calculateAllPossibleQueenCaptures(PieceType.Color.WHITE);

            if (!normalCaptures.getAllPossibleCaptures().isEmpty() || !queenCaptures.getAllPossibleQueenCaptures().isEmpty()) {

                if ((normalCaptures.getAllPiecesWhichCanCapture().contains(position)
                        || queenCaptures.getAllQueensWhichCanCapture().contains(position))
                        && board.get(position).getPieceColor() == PieceType.Color.WHITE
                        && !capturing) {

                    PieceMover.pickPiece(board, position, pickedPosition);
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

                } else {

                    if(normalCaptures.getPositionsAfterCapturing().contains(position)
                        && getPiece(pickedPosition).getPieceType().isNormal()) {

                        normalCaptures.getPositionsAfterCapturing().forEach(BoardDrawer::removePiece);

                        PieceMover.capturePiece(board, position, pickedPosition, normalCaptures, queenCaptures);
                        pickedPosition = position;

                        if(normalCaptures.getPositionsAfterCapturing().isEmpty()) {

                            playerTurn = false;

                            capturing = false;

                            endMove();

                        } else {

                            capturing = true;

                        }

                    } else if(queenCaptures.getPositionsAfterCapturing().contains(position)
                            && getPiece(pickedPosition).getPieceType().isQueen()) {

                        queenCaptures.getPositionsAfterCapturing().forEach(BoardDrawer::removePiece);

                        PieceMover.capturePiece(board, position, pickedPosition, normalCaptures, queenCaptures);
                        pickedPosition = position;

                        if(queenCaptures.getPositionsAfterCapturing().isEmpty()) {

                            playerTurn = false;

                            capturing = false;

                            endMove();;

                        } else {

                            capturing = true;

                        }
                    }
                }
            } else {

                if (board.get(position) != null
                        && board.get(position).getPieceColor() == PieceType.Color.WHITE) {

                    PieceMover.pickPiece(board, position, pickedPosition);
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

                    playerTurn = false;

                } else if (queenMoves.getPossibleQueenMoves().contains(position)
                        && pickedPosition != null) {

                    queenMoves.getPossibleQueenMoves().forEach(BoardDrawer::removePiece);

                    PieceMover.movePiece(board, position, pickedPosition);

                    playerTurn = false;
                }
            }
        } else {

            endMove();
            computerMove();

        }
    }

    private void computerMove() {

        do {

            if(AIMoveGenerator.checkBlacksEnd(endConditions.getRemainingBlackPieces())) {
                break;
            }

            normalCaptures.calculateAllPossibleCaptures(PieceType.Color.BLACK);
            queenCaptures.calculateAllPossibleQueenCaptures(PieceType.Color.BLACK);

            if (!normalCaptures.getAllPossibleCaptures().isEmpty() || !queenCaptures.getAllPossibleQueenCaptures().isEmpty()) {

                Set<PiecePosition> allBlacks = new HashSet<>();

                allBlacks.addAll(normalCaptures.getAllPiecesWhichCanCapture());
                allBlacks.addAll(queenCaptures.getAllQueensWhichCanCapture());

                PiecePosition computerCapture = AIMoveGenerator.selectPosition(allBlacks);

                pickedPosition = computerCapture;

                if (board.get(pickedPosition).getPieceType().isNormal()) {

                    queenCaptures.clear();
                    normalCaptures.clear();

                    normalCaptures.positionsAfterCaptureCalculator(pickedPosition);

                    if (!normalCaptures.getPositionsAfterCapturing().isEmpty()) {

                        computerCapture = AIMoveGenerator.selectPosition(normalCaptures.getPositionsAfterCapturing());

                        PieceMover.capturePiece(board, computerCapture, pickedPosition, normalCaptures, queenCaptures);

                        if (normalCaptures.getPositionsAfterCapturing().isEmpty()) {

                            endMove();

                            playerTurn = true;
                        }
                    }

                } else {

                    queenCaptures.clear();
                    normalCaptures.clear();

                    queenCaptures.queenPositionsAfterCaptureCalculator(pickedPosition);

                    if (!queenCaptures.getPositionsAfterCapturing().isEmpty()) {

                        computerCapture = AIMoveGenerator.selectPosition(queenCaptures.getPositionsAfterCapturing());

                        PieceMover.capturePiece(board, computerCapture, pickedPosition, normalCaptures, queenCaptures);

                        if (queenCaptures.getPositionsAfterCapturing().isEmpty()) {

                            endMove();

                            playerTurn = true;
                        }
                    }
                }

            } else {

                if(AIMoveGenerator.getDifficultyLevel() == 1) {

                    normalMoves.normalDifficultyAIMovesCalculator();

                    if(normalMoves.getAllPossibleAIMoves().isEmpty()) {

                        normalMoves.allPossibleAIMovesCalculator();
                    }

                } else {

                    normalMoves.allPossibleAIMovesCalculator();

                }


                PiecePosition computerMove = AIMoveGenerator.selectPosition(normalMoves.getAllPossibleAIMoves());

                pickedPosition = computerMove;

                if (board.get(computerMove).getPieceType().isNormal()) {

                    normalMoves.clear();

                    normalMoves.normalMoveCalculator(computerMove, false);

                    computerMove = AIMoveGenerator.selectPosition(normalMoves.getAllPossiblePieceMoves());

                } else {

                    queenMoves.getPossibleQueenMoves().clear();

                    queenMoves.normalQueenMoveCalculator(computerMove);

                    computerMove = AIMoveGenerator.selectPosition(queenMoves.getPossibleQueenMoves());

                }
                PieceMover.movePiece(board, computerMove, pickedPosition);
                endMove();
                playerTurn = true;
            }

        } while(!playerTurn);

    }

    private void endMove() {
        pickedPosition = null;

        promoter.promote(board);
        endConditions.checkEndGame(getBoard().keySet());

        normalMoves.clear();
        normalCaptures.clear();
        queenMoves.getPossibleQueenMoves().clear();
        queenCaptures.clear();

    }

    public Map<PiecePosition, PieceType> getBoard() {
        return board;
    }

    public void setBoard(Map<PiecePosition, PieceType> board) {
        this.board = board;
    }

    public SaveLoadGame getSaveLoadGame() {
        return saveLoadGame;
    }
}