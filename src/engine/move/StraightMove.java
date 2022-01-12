package engine.move;

import engine.GameState;

public class StraightMove extends BlockableMove {
    public boolean move(GameState gameState, int fromX, int fromY, int toX, int toY) {
        return (toX == fromX || toY == fromY) && super.move(gameState, fromX, fromY, toX, toY);
    }

    @Override
    protected boolean checkBlockingPieces(GameState gameState, int fromX, int fromY, int toX, int toY) {
        int directionX = toX >= fromX ? 1 : -1;
        int directionY = toY >= fromY ? 1 : -1;

        if (toX == fromX) {
            int distanceY = Math.abs(toY - fromY);
            for (int i = 1; i <= distanceY - 1; ++i) {
                if (gameState.getPiece(fromY + i * directionY, fromX) != null)
                    return false;
            }
            return true;
        } else {
            int distanceX = Math.abs(toX - fromX);
            for (int i = 1; i <= distanceX - 1; ++i) {
                if (gameState.getPiece(fromY, fromX + i * directionX) != null)
                    return false;
            }
            return true;
        }
    }
}
