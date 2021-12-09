package engine.piece;

import chess.PlayerColor;

public class Rook extends SpecialPiece{
    public Rook(PlayerColor color) {
        super(color);
    }

    @Override
    protected boolean canMoveAt(int fromX, int fromY, int toX, int toY) {
        return false;
    }
}
