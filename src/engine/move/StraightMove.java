package engine.move;

import engine.piece.Piece;

public class StraightMove extends Move{
    // à refactor
    @Override
    public boolean move(int fromX, int fromY, int toX, int toY) {
        return toX == fromX || toY == fromY;
    }

    public boolean move(Piece[][] gameState, int fromX, int fromY, int toX, int toY) {
        return (toX == fromX || toY == fromY) && checkBlockingPieces(gameState, fromX, fromY, toX, toY);
    }

    private boolean checkBlockingPieces(Piece[][] gameState, int fromX, int fromY, int toX, int toY) {

        int directionX = toX >= fromX ? 1 : -1;
        int directionY = toY >= fromY ? 1 : -1;

        if (toX == fromX) {
            int distanceY = Math.abs(toY - fromY);
            for (int i = 1; i <= distanceY - 1; ++i) {
                if (gameState[fromY + i * directionY][fromX] != null)
                    return false;
            }
            return true;
        } else {
            int distanceX = Math.abs(toX - fromX);
            for (int i = 1; i <= distanceX - 1; ++i) {
                if (gameState[fromY][fromX + i * directionX] != null)
                    return false;
            }
            return true;
        }
    }
}
