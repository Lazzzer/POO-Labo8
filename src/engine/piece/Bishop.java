package engine.piece;

import chess.PlayerColor;

public class Bishop extends Piece{
    public Bishop(PlayerColor color) {
        super(color);
    }

    @Override
    protected boolean canMoveAt(int fromX, int fromY, int toX, int toY) {
        return false;
    }
}
