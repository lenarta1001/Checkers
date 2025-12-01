package com.checkers.model.player;

import com.checkers.model.colour.Colour;
import com.checkers.model.game.Game;
import com.checkers.model.move.Move;

/**
 * Az ember játékos osztálya
 */
public class HumanPlayer extends Player {

    /**
     * Az ember játékos konstruktora
     * @param colour az ember játékos színe
     */
    public HumanPlayer(Colour colour) {
        super(colour);
    }

    /**
     * Az ember játékos egy lépését végzi el 
     * @param game a játék, amiben az ember játékos játszik
     * @param move a lépés amit az ember játékos választott
     */
    public void handleTurn(Game game, Move move) {
        game.updateCounters(move);
        move.execute(game);
        game.updateFenAndMoves(move);
        game.getSupport().firePropertyChange("boardChange", null, null);
    }
    
    /**
     * Az ember játékos ellenfele lépése utáni teendőket végzi el
     * @param game a játék, amiben az ember játékos játszik
     */
    public void onOpponentTurnCompleted(Game game) { 
        game.swapPlayers();
        game.getSupport().firePropertyChange("boardChange", null, null);
        game.checkIsGameOverOrDraw();
    }

    /**
     * Az ember játékos első lépése előtti teendőket végzi el
     * @param game a játék, amiben az ember játékos játszik
     */
    public void firstTurn(Game game) {
        game.getSupport().firePropertyChange("boardChange", null, null);
        new Thread(() -> {
            try {
                Thread.sleep(1000);
                game.checkIsGameOverOrDraw();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }).start();
    }
}
