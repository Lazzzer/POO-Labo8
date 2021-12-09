package engine.piece;

import chess.PlayerColor;

public abstract class Piece {
    protected PlayerColor color;

    protected Piece(PlayerColor color) {
        this.color = color;
    }


    protected abstract boolean canMoveAt(int fromX, int fromY, int toX, int toY);
}
