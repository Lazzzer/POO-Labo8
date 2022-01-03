package engine.move;

import engine.piece.Piece;

abstract class Move {
    protected boolean move(Piece[][] gameState, int fromX, int fromY, int toX, int toY) {
        return !(gameState[toY][toX] != null && gameState[fromY][fromX].getColor() == gameState[toY][toX].getColor());
    }
}
