package engine.piece;

import chess.PieceType;
import chess.PlayerColor;

public class Pawn extends SpecialPiece{

    private boolean takeableEnPassant;
    public Pawn(PlayerColor color) {
        super(PieceType.PAWN, color);
        takeableEnPassant = false;
    }

    @Override
    public boolean move(Piece[][] gameState, int fromX, int fromY, int toX, int toY) {
        int nbCases = hasMoved ? 1 : 2;
        boolean isValid;
        if (color == PlayerColor.WHITE) {
            isValid = toX == fromX && (toY - fromY <= nbCases && toY - fromY >= 1);
        } else {
            isValid = toX == fromX && (toY - fromY >= -nbCases && toY - fromY <= -1);
        }
        if (isValid) {
            // A refactor pour pas faire l'affectation à chaque fois
            hasMoved = true;
            takeableEnPassant = nbCases == 2;
        }
        return isValid;
    }

    public Piece promote(Piece choice) {
        return null;
    }
}
