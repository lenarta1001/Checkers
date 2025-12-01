package com.checkers.model.move;

import java.awt.Point;

import com.checkers.model.board.*;
import com.checkers.model.game.Game;
import com.checkers.view.GamePanel;

/**
 * A normál lépés osztálya
 */
public class NormalMove extends Move {
    /**
     * A normál lépés konstruktora
     * @param from a kezdő pozíció
     * @param to a végpozíció
     */
    public NormalMove(Point from, Point to) {
        super(from, to);
    }

    /**
     * A normál lépés karakterlánccá konvertálja (ld. specifikáció Portable Draughts Notation)
     * @return a normál lépés karakterlánc formátumban
     */
    public String toString() {
        return Board.squareNumberFromPoint(from) + "-" + Board.squareNumberFromPoint(to);
    }

    /**
     * @return a normál lépést kötelező-e elvégezni
     */
    public boolean isMandatory() {
        return false;
    }

    /**
     * Elvégzi a normál lépést a játék tábláján
     * @param game a játék aminek a tábláján a normál lépést végezzük
     */
    public void execute(Game game) {
        game.executeNormalMove(this);
    }

    /**
     * Eldönti, hogy egy másik objektummal tartalmilag azonos-e a normál lépés
     * @param o az objektum, amivel összehasonlítjuk
     * @return tartalmilag azonos-e az objektummal
     */
    public boolean equals(Object o) {
        if (this == o) { 
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        NormalMove other = (NormalMove) o;
        return from.equals(other.from) && to.equals(other.to);
    }

    /** 
     * Kirajzolja az egyszerű lépés kiemelését a táblán (double dispatch)
     * @param gp a játék panel ahol meg akarjuk jeleníteni a normál lépést
     */
    public void draw(GamePanel gp) {
        gp.drawNormalMove(this);
    }
}
