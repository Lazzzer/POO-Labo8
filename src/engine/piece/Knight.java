package engine.piece;

import chess.PlayerColor;

public class Knight extends Piece{
    public Knight(PlayerColor color) {
        super(color);
    }

    @Override
    protected boolean canMoveAt(int fromX, int fromY, int toX, int toY) {
        return false;
    }
}
