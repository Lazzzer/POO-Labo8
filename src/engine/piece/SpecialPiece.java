package engine.piece;

import chess.PlayerColor;

public abstract class SpecialPiece extends Piece{

    protected SpecialPiece(PlayerColor color) {
        super(color);
    }

    protected abstract boolean canMoveAt(int fromX, int fromY, int toX, int toY);
}
