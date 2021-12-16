package engine.piece;

import chess.PieceType;
import chess.PlayerColor;

public class Pawn extends SpecialPiece{

    protected Pawn(PlayerColor color) {
        super(PieceType.PAWN, color);
    }

    @Override
    protected boolean move(int[][] gameState, int fromX, int fromY, int toX, int toY) {
        return false;
    }
}
