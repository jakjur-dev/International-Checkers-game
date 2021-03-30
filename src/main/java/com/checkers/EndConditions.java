package com.checkers;


import com.checkers.menubar.EndGameWindow;
import com.checkers.menubar.RankingWindow;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class EndConditions {

    private final BoardCompiler boardCompiler;
    private final Set<PiecePosition> remainingWhitePieces = new HashSet<>();
    private final Set<PiecePosition> remainingBlackPieces = new HashSet<>();
    private final Set<PiecePosition> whitePieceMoves = new HashSet<>();
    private final Set<PiecePosition> blackPieceMoves = new HashSet<>();
    private final RankingWindow rankingWindow = new RankingWindow();

    public EndConditions(BoardCompiler boardCompiler) {
        this.boardCompiler = boardCompiler;
    }


    public void checkEndGame(Set<PiecePosition> positions) {
        calculatePieces(positions);

        if(remainingWhitePieces.size() == 0 || whitePieceMoves.size() == 0) {
            rankingWindow.addBlackWins();
            new EndGameWindow().blacksWin();
        }

        if(remainingBlackPieces.size() == 0 || blackPieceMoves.size() == 0) {
            rankingWindow.addWhiteWins();
            new EndGameWindow().whitesWin();
        }

        if(remainingWhitePieces.size() == 1 && remainingBlackPieces.size() == 1) {
            rankingWindow.addDraws();
            new EndGameWindow().draw();
        }
    }

    public void calculatePieces(Set<PiecePosition> positions) {
        remainingWhitePieces.clear();
        remainingBlackPieces.clear();
        blackPieceMoves.clear();
        whitePieceMoves.clear();

        Set<PiecePosition> whitePieces = positions.stream()
                .filter(position -> boardCompiler.getPiece(position).getPieceColor().isWhite())
                .collect(Collectors.toSet());

        Set<PiecePosition> blackPieces = positions.stream()
                .filter(position -> boardCompiler.getPiece(position).getPieceColor().isBlack())
                .collect(Collectors.toSet());

        for (PiecePosition pp : blackPieces) {
            PiecePosition left = new PiecePosition(pp.getCol() - 1, pp.getRow() + 1);
            PiecePosition right = new PiecePosition(pp.getCol() + 1, pp.getRow() + 1);

            if (left.isValidPosition() && boardCompiler.isFieldNull(left)) {
                blackPieceMoves.add(left);
            }

            if (right.isValidPosition() && boardCompiler.isFieldNull(right)) {
                blackPieceMoves.add(right);
            }
        }

        for (PiecePosition pp : whitePieces) {
            PiecePosition left = new PiecePosition(pp.getCol() - 1, pp.getRow() - 1);
            PiecePosition right = new PiecePosition(pp.getCol() + 1, pp.getRow() - 1);

            if (left.isValidPosition() && boardCompiler.isFieldNull(left)) {
                whitePieceMoves.add(left);
            }

            if (right.isValidPosition() && boardCompiler.isFieldNull(right)) {
                whitePieceMoves.add(right);
            }
        }

        remainingWhitePieces.addAll(whitePieces);
        remainingBlackPieces.addAll(blackPieces);
    }

    public Set<PiecePosition> getRemainingBlackPieces() {
        return remainingBlackPieces;
    }
}
