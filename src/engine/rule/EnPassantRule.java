package engine.rule;

import chess.PlayerColor;
import engine.piece.Pawn;
import engine.piece.Piece;

public class EnPassantRule {

    static int WHITE_EN_PASSANT_LINE = 4;
    static int BLACK_EN_PASSANT_LINE = 3;

    private EnPassantRule() {}

    public static boolean canTakeEnPassant(Piece[][] gameState, int fromX, int fromY, int toX, int toY) {
        Piece movingPiece = gameState[fromY][fromX];
        int validYCell = movingPiece.getColor() == PlayerColor.WHITE ? WHITE_EN_PASSANT_LINE : BLACK_EN_PASSANT_LINE;
        int directionY = movingPiece.getColor() == PlayerColor.WHITE ? 1 : -1;

        if (fromY != validYCell || toX == fromX || gameState[toY][toX] != null)
            return false;

        if (gameState[toY - directionY][toX] instanceof Pawn
                && ((Pawn) gameState[toY - directionY][toX]).isTakeableEnPassant()) {
            gameState[toY - directionY][toX] = null;
            return true;
        }
        return false;
    }
}
