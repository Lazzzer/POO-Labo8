package engine.piece;

import chess.PlayerColor;

public class Queen extends Piece{
    public Queen(PlayerColor color) {
        super(color);
    }

    @Override
    protected boolean canMoveAt(int fromX, int fromY, int toX, int toY) {
        return false;
    }
}
