package engine.piece;

import chess.PieceType;
import chess.PlayerColor;

public class Queen extends Piece{

    public Queen(PlayerColor color) {
        super(PieceType.QUEEN, color);
    }

    @Override
    protected boolean move(int[][] gameState, int fromX, int fromY, int toX, int toY) {
        return false;
    }
}
