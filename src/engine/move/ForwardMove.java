package engine.move;

import engine.piece.Piece;

public class ForwardMove extends BlockableMove{
    public boolean move(Piece[][] gameState, int fromX, int fromY, int toX, int toY, int nbCells) {
        return fromX == toX && Math.abs(toY - fromY) <= nbCells && Math.abs(toY - fromY) >= 1
                && super.move(gameState, fromX, fromY, toX, toY);
    }

    @Override
    protected boolean checkBlockingPieces(Piece[][] gameState, int fromX, int fromY, int toX, int toY) {
        int directionY = toY >= fromY ? 1 : -1;
        for (int i = 1; i <= Math.abs(toY - fromY); ++i) {
            if (gameState[fromY + i * directionY][toX] != null)
                return false;
        }
        return true;
    }
}
