package engine.piece;

import chess.PieceType;
import chess.PlayerColor;

public class Rook extends SpecialPiece{

    public Rook(PlayerColor color) {
        super(PieceType.ROOK, color);
    }

    @Override
    protected boolean move(int[][] gameState, int fromX, int fromY, int toX, int toY) {
        return false;
    }
}
