package engine.move;

import engine.GameState;

/**
 * Classe représentant un mouvement en L
 * @author Alexandre Jaquier
 * @author Lazar Pavicevic
 */
public class LMove extends Move {
    @Override
    public boolean move(GameState gameState, int fromX, int fromY, int toX, int toY) {
        return super.move(gameState, fromX, fromY, toX, toY) && (toX - fromX) * (toX - fromX) + (toY - fromY) * (toY - fromY) == 5;
    }
}
