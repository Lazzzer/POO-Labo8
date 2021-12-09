package engine.piece;

import chess.PlayerColor;

public class Pawn extends SpecialPiece{

    public Pawn(PlayerColor color) {
        super(color);
    }

    @Override
    protected boolean canMoveAt(int fromX, int fromY, int toX, int toY) {
        return false;
    }
}
