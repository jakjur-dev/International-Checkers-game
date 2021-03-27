package com.checkers;

import java.io.Serializable;

public class PiecePosition implements Serializable {

    private int col;
    private int row;

    public PiecePosition(int col, int row) {
        this.col = col;
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public int getRow() {
        return row;
    }

    public boolean isValidPosition() {
        return col >= 0 && col <= 9 && row >= 0 && row <= 9;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PiecePosition that = (PiecePosition) o;

        if (col != that.col) return false;
        return row == that.row;
    }

    @Override
    public int hashCode() {
        int result = col;
        result = 31 * result + row;
        return result;
    }
}
