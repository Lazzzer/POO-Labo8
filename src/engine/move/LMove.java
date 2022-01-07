package engine.move;

import engine.GameState;
import engine.piece.Piece;

public class LMove extends Move {
    @Override
    public boolean move(GameState gameState, int fromX, int fromY, int toX, int toY) {
        return super.move(gameState, fromX, fromY, toX, toY) && (toX - fromX) * (toX - fromX) + (toY - fromY) * (toY - fromY) == 5;
    }
}
