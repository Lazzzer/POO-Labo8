package engine.piece;

import chess.PieceType;
import chess.PlayerColor;

public class King extends SpecialPiece{

    public King(PlayerColor color) {
        super(PieceType.KING, color);
    }

    @Override
    public boolean move(Piece[][] gameState, int fromX, int fromY, int toX, int toY) {
        return true;
    }

    public boolean castling() {
        return false;
    }
}
