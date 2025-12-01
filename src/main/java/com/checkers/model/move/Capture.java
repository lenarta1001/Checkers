package com.checkers.model.move;

import java.awt.Point;

import com.checkers.model.board.*;
import com.checkers.model.game.Game;
import com.checkers.view.GamePanel;

/**
 * Az ütés osztálya
 */
public class Capture extends Move {
    /**
     * Az ütés konstruktora
     * @param from a kezdő pozíció
     * @param to a végpozíció
     */
    public Capture(Point from, Point to) {
        super(from, to);
    }

    /**
     * Az karakterlánccá konvertálja az ütést (ld. specifikáció Portable Draughts Notation)
     * @return az ütés karakterlánc formátumban
     */
    public String toString() {
        return Board.squareNumberFromPoint(from) + "x" + Board.squareNumberFromPoint(to);
    }

    /**
     * @return az ütést kötelező-e elvégezni
     */
    public boolean isMandatory() {
        return true;
    }
    
    /**
     * Elvégzi az ütést a játék tábláján
     * @param game a játék aminek a tábláján az ütést végezzük
     */
    public void execute(Game game) {
        game.executeCapture(this);
    }

    /**
     * Eldönti, hogy egy másik objektummal tartalmilag azonos-e az ütés
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

        Capture other = (Capture) o;
        return from.equals(other.from) && to.equals(other.to);
    }

    /** 
     * Kirajzolja az ütés kiemelését a táblán (double dispatch)
     * @param gp a játék panel ahol meg akarjuk jeleníteni az ütést
     */
    public void draw(GamePanel gp) {
        gp.drawCapture(this);
    }
    
}
