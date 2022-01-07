package engine.piece;

import chess.PieceType;
import chess.PlayerColor;
import engine.GameState;

abstract class SpecialPiece extends Piece{
    protected boolean hasMoved;

    protected SpecialPiece(PieceType type, PlayerColor color) {
        super(type, color);
        hasMoved = false;
    }

    public boolean hasMoved() {
        return hasMoved;
    }

    public abstract boolean move(GameState gameState, int fromX, int fromY, int toX, int toY);
}
