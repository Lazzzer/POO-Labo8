package engine.piece;

import chess.PieceType;
import chess.PlayerColor;

public abstract class Piece {
    protected PieceType type;
    protected PlayerColor color;

    protected Piece(PieceType type, PlayerColor color) {
        this.type = type;
        this.color = color;
    }
    protected abstract boolean move(int[][] gameState, int fromX, int fromY, int toX, int toY);
}