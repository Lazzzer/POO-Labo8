package engine.move;

import engine.GameState;

/**
 * Classe représentant un mouvement d'une seule case dans toutes les directions
 * @author Alexandre Jaquier
 * @author Lazar Pavicevic
 */
public class OneCellMove extends Move {
    public boolean move(GameState gameState, int fromX, int fromY, int toX, int toY) {
        return super.move(gameState, fromX, fromY, toX, toY) && Math.abs(toX - fromX) <= 1 && Math.abs(toY - fromY) <= 1;
    }
}
