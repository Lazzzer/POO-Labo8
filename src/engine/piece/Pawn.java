package engine.piece;

import chess.PieceType;
import chess.PlayerColor;

public class Pawn extends SpecialPiece{

    private boolean canBeEnPassant;

    protected Pawn(PlayerColor color) {
        super(PieceType.PAWN, color);
        canBeEnPassant = false;
    }

    @Override
    protected boolean move(int[][] gameState, int fromX, int fromY, int toX, int toY) {
        return false;
    }

    public Piece promote(Piece choice) {
        return null;
    }
}
