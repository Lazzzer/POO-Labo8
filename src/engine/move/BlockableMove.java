package engine.move;

import engine.piece.Piece;

abstract class BlockableMove extends Move{
    @Override
    protected boolean move(Piece[][] gameState, int fromX, int fromY, int toX, int toY) {
        return super.move(gameState, fromX, fromY, toX, toY) && checkBlockingPieces(gameState, fromX, fromY, toX, toY);
    }
    protected abstract boolean checkBlockingPieces(Piece[][] gameState, int fromX, int fromY, int toX, int toY);
}
