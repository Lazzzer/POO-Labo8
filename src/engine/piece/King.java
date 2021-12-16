package engine.piece;

import chess.PieceType;
import chess.PlayerColor;

public class King extends SpecialPiece{

    public King(PlayerColor color) {
        super(PieceType.KING, color);
    }

    @Override
    protected boolean move(int[][] gameState, int fromX, int fromY, int toX, int toY) {
        return false;
    }

    public boolean castling() {
        return false;
    }
}
