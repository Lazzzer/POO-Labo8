package engine.piece;

import chess.PieceType;
import chess.PlayerColor;

public class King extends SpecialPiece{

    public King(PlayerColor color) {
        super(PieceType.KING, color);
    }

    @Override
    public boolean move(Piece[][] gameState, int fromX, int fromY, int toX, int toY) {
        return Math.abs(toX - fromX) <= 1 && Math.abs(toY - fromY) <= 1;
    }

    public boolean castling() {
        return false;
    }
}
