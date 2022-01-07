package engine.move;

import engine.GameState;

public class OneCellMove extends Move {
    public boolean move(GameState gameState, int fromX, int fromY, int toX, int toY) {
        return super.move(gameState, fromX, fromY, toX, toY) && Math.abs(toX - fromX) <= 1 && Math.abs(toY - fromY) <= 1;
    }
}
