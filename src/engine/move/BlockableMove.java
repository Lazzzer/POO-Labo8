package engine.move;

import engine.GameState;
import engine.piece.Piece;

abstract class BlockableMove extends Move{
    @Override
    protected boolean move(GameState gameState, int fromX, int fromY, int toX, int toY) {
        return super.move(gameState, fromX, fromY, toX, toY) && checkBlockingPieces(gameState, fromX, fromY, toX, toY);
    }
    protected abstract boolean checkBlockingPieces(GameState gameState, int fromX, int fromY, int toX, int toY);
}
