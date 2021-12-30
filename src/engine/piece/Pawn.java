package engine.piece;

import chess.PieceType;
import chess.PlayerColor;

public class Pawn extends SpecialPiece{

    private boolean canBeEnPassant;
    public Pawn(PlayerColor color) {
        super(PieceType.PAWN, color);
        canBeEnPassant = false;
    }

    @Override
    public boolean move(Piece[][] gameState, int fromX, int fromY, int toX, int toY) {
        return true;
    }

    public Piece promote(Piece choice) {
        return null;
    }
}
