package com.checkers.model.board;

import com.checkers.model.colour.Colour;
import com.checkers.model.piece.*;
import com.checkers.model.player.Player;

import java.awt.Point;

/**
 * A tábla osztálya
 */
public class Board {
    private Piece[][] squares;

    /**
     * A tábla konstruktora
     * Létrehozza a 8x8-as táblát
     */
    public Board() {
        squares = new Piece[8][8];
    }

    /**
     * Eldönti, hogy a tábla adott pontján van-e bábu
     * @param p a pont, amit vizsgálunk
     * @return tábla adott pontján van-e bábu
     */
    public boolean isEmpty(Point p) { 
        return squares[p.y][p.x] == null;
    }

    /**
     * Eldönti, hogy egy pont a táblán belül van-e
     * @param p a pont, amit vizsgálunk
     * @return a pont a táblán belül van-e
     */
    public static boolean isInsideBoard(Point p) {
        return p.x >= 0 && p.x < 8 && p.y >= 0 && p.y < 8;
    }

    /**
     * A tábla adott pontjára tesz egy bábut
     * @param piece a bábu, amit el akarunk helyezni
     * @param p a pont, ahová szeretnénk a bábut helyezni
     */
    public void setPiece(Piece piece, Point p) {
        squares[p.y][p.x] = piece;
    }

    /**
     * A tábla adott pontján milyen bábu van
     * @param p a pont, amit vizsgálunk
     * @return a ponton lévő bábu
     */
    public Piece getPiece(Point p) {
        return squares[p.y][p.x];
    }

    /**
     * A pont inverzét képzi (a tábla megfordításával a táblán a pont melyik pontnak felelne meg)
     * @param p a pont amit invertálni akarunk
     * @return a pont inverze
     */
    public static Point invertPoint(Point p) {
        Point invertedPoint = new Point();
        invertedPoint.x = 7 - (int)p.getX();
        invertedPoint.y = 7 - (int)p.getY();
        return invertedPoint;
    }

    /**
     * A kezdő táblát állítja be 
     */
    public void initBoard() {
        for (int i = 1; i <= 32; i++) {
            Point p = pointFromSquareNumber(i);
            if (p.y <= 2) {
                setPiece(new Checker(Colour.WHITE), p);
            } else if (p.y >= 5) {
                setPiece(new Checker(Colour.BLACK), p);
            }
        }
    }
    
    /**
     * A táblán lévő pontból egy mezőszámot állít elő (ld. specifikáció Portable Draughts Notation) 
     * @param p a pont, amiből a mezőszámot elő akarjuk állítani
     * @return a ponthoz tartotó mezőszám
     * @throws IllegalArgumentException ha a pont érvénytelen
     */
    public static int squareNumberFromPoint(Point p) throws IllegalArgumentException {
        if ((p.x + p.y) % 2 == 0 || !isInsideBoard(p)) {
            throw new IllegalArgumentException("The position is invalid!");
        }
        int numOfBlackSquaresInLowerLines = (7 - p.y) * 4;
        int numOfBlackSquaresInThisLine = p.x / 2 + 1;
        return numOfBlackSquaresInLowerLines + numOfBlackSquaresInThisLine;
    }

    /**
     * A mezőszámből egy pontot állít elő
     * @param squareNumber a mezőszám, amiből elő akarjuk állítani a pontot
     * @return a mezőszámhoz tartozó pont
     * @throws IllegalArgumentException ha a mezőszám érvénytelen
     */
    public static Point pointFromSquareNumber(int squareNumber) throws IllegalArgumentException {
        if (squareNumber > 32 || squareNumber < 1) {
            throw new IllegalArgumentException("The position square number invalid!");
        }
        Point p = new Point();
        int y = 7 - (squareNumber - 1) / 4;

        int x = (squareNumber - 1) % 4 * 2 + (y % 2 == 0 ? 1 : 0);
        p.setLocation(x, y);
        return p;
    }
    
    /**
     * A tábla állapotát fen formátumba adja vissza (ld. specifikáció FEN)
     * @param playerToMove az éppen következő játékos
     * @return a tábla állapota fen formátumban
     */
    public String getFen(Player playerToMove) {
        StringBuilder fen = new StringBuilder();
        if (playerToMove.getColour() == Colour.BLACK) {
            fen.append("B");
        } else {
            fen.append("W");
        }

        StringBuilder blacks = new StringBuilder(":B");
        StringBuilder whites = new StringBuilder(":W");

        for (int i = 1; i <= 32; i++) {
            Point p = pointFromSquareNumber(i);

            if (!isEmpty(p)) {
                Piece piece = getPiece(p);
                if (piece.getColour() == Colour.BLACK) {
                    if (blacks.length() != 2) {
                        blacks.append(",");
                    }
                    blacks.append(i);
                    blacks.append(piece.toString());
                } else {
                    if (whites.length() != 2) {
                        whites.append(",");
                    }
                    whites.append(i);
                    whites.append(piece.toString());
                }
            }
        }
        fen.append(whites.toString());
        fen.append(blacks.toString());
        return fen.toString();
    }
}
