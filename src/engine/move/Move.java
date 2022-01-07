package engine.move;

import engine.GameState;

abstract class Move {
    protected boolean move(GameState gameState, int fromX, int fromY, int toX, int toY) {
        return !(gameState.getPiece(toY, toX) != null && gameState.getPiece(fromY, fromX).getColor() == gameState.getPiece(toY,toX).getColor());
    }
}
