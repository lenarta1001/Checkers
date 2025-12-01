package com.checkers.model;

/**
 * Érvénytelen lépés kivétel osztály
 * A szerializáláshoz szükséges
 */
public class InvalidMoveException extends Exception {
    public InvalidMoveException(String message) {
        super(message);
    }

    public InvalidMoveException(String message, Throwable cause) {
        super(message, cause);
    }
}
