package com.checkers.model.piece;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.*;

import com.checkers.model.board.Board;
import com.checkers.model.move.*;

public class testPiece {
    @Test
    public void testGetColour() {
        Piece piece = new Checker(Colour.black);
        assertEquals(Colour.black, piece.getColour());
    }

    @Test
    public void testToStringChecker() {
        Piece piece = new Checker(Colour.black);
        assertEquals("", piece.toString());
    }

    @Test
    public void testToStringKing() {
        Piece piece = new King(Colour.black);
        assertEquals("K", piece.toString());
    }

    @Test
    public void testValidNormalMovesBlackChecker() {
        Board board = new Board();
        board.setPiece(new Checker(Colour.black), Board.pointFromSquareNumber(11));
        board.setPiece(new Checker(Colour.white), Board.pointFromSquareNumber(14));

        List<Move> moves = board.getPiece(Board.pointFromSquareNumber(11)).validMoves(board, Board.pointFromSquareNumber(11));
        Move move = new NormalMove(Board.pointFromSquareNumber(11), Board.pointFromSquareNumber(15), false);

        assertEquals(move, moves.getFirst());   
    }

    @Test
    public void testValidNormalMovesWhiteChecker() {
        Board board = new Board();
        board.invert();
        board.setPiece(new Checker(Colour.white), Board.pointFromSquareNumber(11));
        board.setPiece(new Checker(Colour.black), Board.pointFromSquareNumber(14));

        List<Move> moves = board.getPiece(Board.pointFromSquareNumber(11)).validMoves(board, Board.pointFromSquareNumber(11));
        Move move = new NormalMove(Board.pointFromSquareNumber(11), Board.pointFromSquareNumber(15), true);

        assertEquals(move, moves.getFirst());   
    }

    @Test
    public void testValidCapturesBlackChecker() {
        Board board = new Board();
        board.setPiece(new Checker(Colour.black), Board.pointFromSquareNumber(11));
        board.setPiece(new Checker(Colour.white), Board.pointFromSquareNumber(14));

        List<Move> moves = board.getPiece(Board.pointFromSquareNumber(11)).validMoves(board, Board.pointFromSquareNumber(11));
        Move move = new Capture(Board.pointFromSquareNumber(11), Board.pointFromSquareNumber(18), false);

        assertEquals(move, moves.getLast());   
    }

    @Test
    public void testValidCaprturesWhiteChecker() {
        Board board = new Board();
        board.invert();
        board.setPiece(new Checker(Colour.white), Board.pointFromSquareNumber(11));
        board.setPiece(new Checker(Colour.black), Board.pointFromSquareNumber(14));

        List<Move> moves = board.getPiece(Board.pointFromSquareNumber(11)).validMoves(board, Board.pointFromSquareNumber(11));
        Move move = new Capture(Board.pointFromSquareNumber(11), Board.pointFromSquareNumber(18), true);

        assertEquals(move, moves.getLast());   
    }

    @Test
    public void testValidCapturesSequenceBlackChecker() {
        Board board = new Board();
        board.setPiece(new Checker(Colour.black), Board.pointFromSquareNumber(11));
        board.setPiece(new Checker(Colour.white), Board.pointFromSquareNumber(14));
        board.setPiece(new Checker(Colour.white), Board.pointFromSquareNumber(22));

        List<Move> moves = board.getPiece(Board.pointFromSquareNumber(11)).validMoves(board, Board.pointFromSquareNumber(11));

        List<Capture> captures = new ArrayList<>();
        captures.add(new Capture(Board.pointFromSquareNumber(11), Board.pointFromSquareNumber(18), false));
        captures.add(new Capture(Board.pointFromSquareNumber(18), Board.pointFromSquareNumber(27), false));

        Move move = new CaptureSequence(captures, false);
        assertEquals(move, moves.getLast());   
    }

    @Test
    public void testValidCapturesSequenceWhiteChecker() {
        Board board = new Board();
        board.invert();
        board.setPiece(new Checker(Colour.black), Board.pointFromSquareNumber(11));
        board.setPiece(new Checker(Colour.white), Board.pointFromSquareNumber(14));
        board.setPiece(new Checker(Colour.white), Board.pointFromSquareNumber(22));

        List<Move> moves = board.getPiece(Board.pointFromSquareNumber(11)).validMoves(board, Board.pointFromSquareNumber(11));

        List<Capture> captures = new ArrayList<>();
        captures.add(new Capture(Board.pointFromSquareNumber(11), Board.pointFromSquareNumber(18), true));
        captures.add(new Capture(Board.pointFromSquareNumber(18), Board.pointFromSquareNumber(27), true));

        Move move = new CaptureSequence(captures, true);
        assertEquals(move, moves.getLast());   
    }
}
