package com.checkers;


import com.checkers.menubar.EndGameWindow;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class EndConditions {

    private final BoardCompiler boardCompiler;
    private final Set<PiecePosition> restOfWhites = new HashSet<>();
    private final Set<PiecePosition> restOfBlacks = new HashSet<>();
    private final Set<PiecePosition> whitePieceMoves = new HashSet<>();
    private final Set<PiecePosition> blackPieceMoves = new HashSet<>();

    public EndConditions(BoardCompiler boardCompiler) {
        this.boardCompiler = boardCompiler;
    }


    public void checkEndGame(Set<PiecePosition> positions) {
        calculatePieces(positions);

        if(restOfWhites.size() == 0 || whitePieceMoves.size() == 0) {
            new EndGameWindow().blacksWin();
        }

        if(restOfBlacks.size() == 0 || blackPieceMoves.size() == 0) {
            new EndGameWindow().whitesWin();
        }

        if(restOfWhites.size() == 1 && restOfBlacks.size() == 1) {
            new EndGameWindow().draw();
        }
    }

    public void calculatePieces(Set<PiecePosition> positions) {
        restOfWhites.clear();
        restOfBlacks.clear();
        blackPieceMoves.clear();
        whitePieceMoves.clear();

        Set<PiecePosition> whites = positions.stream()
                .filter(position -> boardCompiler.getPiece(position).getPieceColor().isWhite())
                .collect(Collectors.toSet());

        Set<PiecePosition> blacks = positions.stream()
                .filter(position -> boardCompiler.getPiece(position).getPieceColor().isBlack())
                .collect(Collectors.toSet());

        for (PiecePosition pp : blacks) {
            PiecePosition left = new PiecePosition(pp.getCol() - 1, pp.getRow() + 1);
            PiecePosition right = new PiecePosition(pp.getCol() + 1, pp.getRow() + 1);

            if (left.isValidPosition() && boardCompiler.isFieldNull(left)) {
                blackPieceMoves.add(left);
            }

            if (right.isValidPosition() && boardCompiler.isFieldNull(right)) {
                blackPieceMoves.add(right);
            }
        }

        for (PiecePosition pp : whites) {
            PiecePosition left = new PiecePosition(pp.getCol() - 1, pp.getRow() - 1);
            PiecePosition right = new PiecePosition(pp.getCol() + 1, pp.getRow() - 1);

            if (left.isValidPosition() && boardCompiler.isFieldNull(left)) {
                whitePieceMoves.add(left);
            }

            if (right.isValidPosition() && boardCompiler.isFieldNull(right)) {
                whitePieceMoves.add(right);
            }
        }

        restOfWhites.addAll(whites);
        restOfBlacks.addAll(blacks);
    }

    public Set<PiecePosition> getRestOfBlacks() {
        return restOfBlacks;
    }
}
