package com.checkers.model;

import java.io.IOException;

/**
 * Fájlformátum kivétel osztály
 * A szerializáláshoz szükséges
 */
public class FileFormatException extends IOException {
    /**
     * Fájlformátum kivétel konstruktora
     * @param message a kivétel üzenete
     */
    public FileFormatException(String message) {
        super(message);
    }

    /**
     * Fájlformátum kivétel konstruktora
     * @param message a kivétel üzenete
     * @param 
     */
    public FileFormatException(String message, Throwable cause) {
        super(message, cause);
    }
}
