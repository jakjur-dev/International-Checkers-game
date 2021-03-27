package com.checkers;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class SaveLoadGame implements Serializable {

    private final File file = new File("board.list");
    private Map<PiecePosition, PieceType> loadBoard = new HashMap<>();
    private final BoardCompiler board;

    public SaveLoadGame(BoardCompiler board) {
        this.board = board;
    }

    public void saveGame() {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
            oos.writeObject(board.getBoard());
            oos.close();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void loadGame() {
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
            Object readBoard = ois.readObject();
            if(readBoard instanceof HashMap) {
                loadBoard = (HashMap<PiecePosition, PieceType>) readBoard;
            }
            ois.close();
            board.setBoard(loadBoard);
        } catch (Exception e) {
            e.getMessage();
        }
    }

    public void removeFile() {
        new File("board.list").delete();
    }

    public boolean fileExists() {
        return new File("board.list").exists();
    }
}
