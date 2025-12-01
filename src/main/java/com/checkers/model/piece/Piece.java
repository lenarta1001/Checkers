package com.checkers.model.piece;

import java.awt.Point;
import java.util.List;

import com.checkers.model.move.*;
import com.checkers.view.GamePanel;
import com.checkers.model.board.Board;
import com.checkers.model.colour.Colour;


/**
 * A bábu abstract osztálya
 */
public abstract class Piece {
    private Colour colour;
    protected Point direction;

    /**
     * A bábu konstruktora
     * @param colour a bábu színe
     */
    protected Piece(Colour colour) {
        this.colour = colour;
        this.direction = colour == Colour.BLACK ? new Point(0, -1) : new Point(0, 1);
    }

    /**
     * A babu színének gettere
     * @return a bábu színe
     */
    public Colour getColour() {
        return colour;
    }

    /**
     * Egy tábla adott pontjáról elvégezhető összes lépés a bábuval
     * @param table a tábla, amin vizsgáljuk a lépéseket
     * @param point a pont, ahonnan a lépeseket el akarjuk végezni
     * @return az elvégezhető összes lépés
     */
    public abstract List<Move> validMoves(Board table, Point point);

    /**
     * Kirajzolja a játékpanelre a bábut
     * @param gp a játék panel, amire ki akarjuk rajzolni a bábut
     * @param x a kirajzolás helyének x koordinátája
     * @param y a kirajzolás helyének y koordinátája
     */
    public abstract void draw(GamePanel gp, int x, int y);  
}
