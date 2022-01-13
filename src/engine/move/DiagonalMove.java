package engine.move;

import engine.GameState;
/**
 * Classe représentant un mouvement en diagonal sans limites et blockable
 * @author Alexandre Jaquier
 * @author Lazar Pavicevic
 */
public class DiagonalMove extends BlockableMove {
    
    public boolean move(GameState gameState, int fromX, int fromY, int toX, int toY) {
        return Math.abs(toX - fromX) == Math.abs(toY - fromY) && super.move(gameState, fromX, fromY, toX, toY);
    }
    
    @Override
    protected boolean checkBlockingPieces(GameState gameState, int fromX, int fromY, int toX, int toY) {
        int distanceX = Math.abs(toX - fromX);
        int directionX = toX > fromX ? 1 : -1;
        int directionY = toY > fromY ? 1 : -1;

        for (int i = 1; i <= distanceX - 1; ++i) {
            if (gameState.getPiece(fromY + i * directionY, fromX + i * directionX) != null)
                return false;
        }
        return true;
    }
}
