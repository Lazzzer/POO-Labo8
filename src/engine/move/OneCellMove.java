package engine.move;

import engine.piece.Piece;

public class OneCellMove extends Move {
    @Override
    public boolean move(Piece[][] gameState, int fromX, int fromY, int toX, int toY) {
        return super.move(gameState, fromX, fromY, toX, toY) && Math.abs(toX - fromX) <= 1 && Math.abs(toY - fromY) <= 1;
    }
}
