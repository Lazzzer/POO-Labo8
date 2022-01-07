package engine.rule;

import chess.PlayerColor;
import engine.GameState;
import engine.piece.Pawn;
import engine.piece.Piece;

public class EnPassantRule {

    private static final int WHITE_EN_PASSANT_LINE = 4;
    private static final int BLACK_EN_PASSANT_LINE = 3;

    private EnPassantRule() {}

    public static boolean canTakeEnPassant(GameState gameState, int fromX, int fromY, int toX, int toY) {
        Piece movingPiece = gameState.getPiece(fromY, fromX);
        int validYCell = movingPiece.getColor() == PlayerColor.WHITE ? WHITE_EN_PASSANT_LINE : BLACK_EN_PASSANT_LINE;
        int directionY = movingPiece.getColor() == PlayerColor.WHITE ? 1 : -1;

        if (fromY != validYCell || toX == fromX || gameState.getPiece(toY, toX) != null)
            return false;

        if (gameState.getPiece(toY - directionY, toX) instanceof Pawn
                &&  gameState.getNbTurns() - ((Pawn) gameState.getPiece(toY - directionY, toX)).getTurnEnPassant() == 1) {
            gameState.setPiece(null, toY - directionY, toX);
            return true;
        }
        return false;
    }
}
