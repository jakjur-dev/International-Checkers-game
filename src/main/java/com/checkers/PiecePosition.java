package com.checkers;

public class PiecePosition  {

    private int column;
    private int row;

    public PiecePosition(int column, int row) {
        this.column = column;
        this.row = row;
    }

    public int getCol() {
        return column;
    }

    public int getRow() {
        return row;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PiecePosition that = (PiecePosition) o;

        if (column != that.column) return false;
        return row == that.row;
    }

    @Override
    public int hashCode() {
        int result = column;
        result = 31 * result + row;
        return result;
    }
}
