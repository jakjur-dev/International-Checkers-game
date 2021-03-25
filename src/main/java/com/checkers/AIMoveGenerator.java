package com.checkers;

import java.util.Random;
import java.util.Set;

public class AIMoveGenerator {

    private final Random random = new Random();
    public static int difficultyLevel = 0;

    public PiecePosition selectPosition(Set<PiecePosition> positions) {
        Object[] object = positions.toArray();
        return (PiecePosition) object[random.nextInt(object.length)];
    }

    public boolean checkBlacksEnd(Set<PiecePosition> restOfBlacks) {
        return restOfBlacks.size() == 0;
    }

    public static void setDifficultyLevel(int difficultyLevel) {
        AIMoveGenerator.difficultyLevel = difficultyLevel;
    }

    public int getDifficultyLevel() {
        return difficultyLevel;
    }

}
