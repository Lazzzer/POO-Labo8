package engine.move;

import chess.PlayerColor;
import engine.GameState;

public class ForwardMove extends BlockableMove{
    public boolean move(GameState gameState, int fromX, int fromY, int toX, int toY, int nbCells) {
        boolean isValid;
        if (gameState.getPiece(fromY, fromX).getColor() == PlayerColor.WHITE) {
            isValid = (toY - fromY) >= 1;
        } else {
            isValid = (toY - fromY) <= -1;
        }
        return isValid && Math.abs(toY - fromY) <= nbCells && super.move(gameState, fromX, fromY, toX, toY);
    }

    @Override
    protected boolean checkBlockingPieces(GameState gameState, int fromX, int fromY, int toX, int toY) {
        int directionY = toY >= fromY ? 1 : -1;
        for (int i = 1; i <= Math.abs(toY - fromY); ++i) {
            if (gameState.getPiece(fromY + i * directionY, toX) != null)
                return false;
        }
        return true;
    }
}
