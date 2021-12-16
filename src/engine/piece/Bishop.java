package engine.piece;

import chess.PieceType;
import chess.PlayerColor;

public class Bishop extends Piece{

    protected Bishop(PlayerColor color) {
        super(PieceType.BISHOP, color);
    }

    @Override
    protected boolean move(int[][] gameState, int fromX, int fromY, int toX, int toY) {
        return false;
    }
}
