package com.checkers;

import javafx.scene.image.Image;

import java.net.URL;

public class ResourceFinder {

    public static String getPath(String fileName) {
        ClassLoader classLoader = ResourceFinder.class.getClassLoader();

        URL resource = classLoader.getResource(fileName);

        if (resource == null) throw new AssertionError();
        return resource.getProtocol() + ":" + resource.getPath();
    }

    public static Image generateImagePath(PieceType piece) {

            return new Image(ResourceFinder.getPath(piece.getPieceColor() + "-" + piece.getPieceType() + ".png"));
        }
    }
