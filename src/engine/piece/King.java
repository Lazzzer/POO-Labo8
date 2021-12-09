package engine.piece;

import chess.PlayerColor;

public class King extends SpecialPiece{
    public King(PlayerColor color) {
        super(color);
    }

    @Override
    protected boolean canMoveAt(int fromX, int fromY, int toX, int toY) {
        return false;
    }
}
