package com.checkers.model.player;


import java.util.List;
import java.util.Random;

import com.checkers.model.colour.Colour;
import com.checkers.model.game.Game;
import com.checkers.model.move.Move;

/**
 * A bot játékos osztálya
 */
public class ComputerPlayer extends Player {

    /**
     * A bot játékos konstruktora
     * @param colour a bot játékos színe
     */
    public ComputerPlayer(Colour colour) {
        super(colour);
    }

    /**
     * A bot játékos egy lépését végzi el 
     * @param game a játék, amiben a bot játékos játszik
     * @param move a lépés amit a bot játékos választott
     */
    public void handleTurn(Game game, Move move) {
        game.swapPlayers(); // Az ütéssorozat megjelenítésénél ne forduljon meg a tábla
        game.updateCounters(move);
        move.execute(game);
        game.updateFenAndMoves(move);
    }
    
    /**
     * A bot játékos ellenfele lépése utáni teendőket végzi el
     * @param game a játék, amiben a bot játékos játszik
     */
    public void onOpponentTurnCompleted(Game game) {
        game.swapPlayers();
        game.checkIsGameOverOrDraw();
        List<Move> moves = validMoves(game);
        if (moves.isEmpty()) {
            return;
        }
        Move chosen = moves.get(new Random().nextInt(moves.size()));
        handleTurn(game, chosen);
        game.getSupport().firePropertyChange("boardChange", null, null);
        game.checkIsGameOverOrDraw();
    }

    /**
     * A bot játékos első lépése előtti teendőket végzi el
     * @param game a játék, amiben a bot játékos játszik
     */
    public void firstTurn(Game game) {
        game.swapPlayers();
        game.getSupport().firePropertyChange("boardChange", null, null);
        new Thread(() -> {
            try {
                Thread.sleep(1000);
                onOpponentTurnCompleted(game);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }).start();
    }
}   
