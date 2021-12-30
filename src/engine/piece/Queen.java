package engine.piece;

import chess.PieceType;
import chess.PlayerColor;

public class Queen extends Piece{

    public Queen(PlayerColor color) {
        super(PieceType.QUEEN, color);
    }

    @Override
    public boolean move(Piece[][] gameState, int fromX, int fromY, int toX, int toY) {
        return true;
    }
}
