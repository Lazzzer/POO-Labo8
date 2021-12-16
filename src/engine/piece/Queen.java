package engine.piece;

import chess.PieceType;
import chess.PlayerColor;

public class Queen extends Piece{

    protected Queen(PieceType type, PlayerColor color) {
        super(type, color);
    }

    @Override
    protected boolean move(int[][] gameState, int fromX, int fromY, int toX, int toY) {
        return false;
    }
}
