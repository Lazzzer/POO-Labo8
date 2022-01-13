package engine.rule;

import chess.PieceType;
import chess.PlayerColor;
import engine.GameState;
import engine.piece.Pawn;

public class EnPassantRule {

    private static final int WHITE_EN_PASSANT_LINE = 4;
    private static final int BLACK_EN_PASSANT_LINE = 3;

    private EnPassantRule() {}

    public static boolean canTakeEnPassant(GameState gameState, int fromX, int fromY, int toX, int toY) {
        int validYCell = gameState.getPiece(fromY, fromX).getColor() == PlayerColor.WHITE ? WHITE_EN_PASSANT_LINE : BLACK_EN_PASSANT_LINE;
        int directionY = gameState.getPiece(fromY, fromX).getColor() == PlayerColor.WHITE ? 1 : -1;

        if (fromY != validYCell || toX == fromX || gameState.getPiece(toY, toX) != null)
            return false;

        if (gameState.getPiece(toY - directionY, toX) != null && gameState.getPiece(toY - directionY, toX).getPieceType() == PieceType.PAWN
                &&  gameState.getNbTurns() - ((Pawn) gameState.getPiece(toY - directionY, toX)).getTurnEnPassant() == 1) {
            gameState.createBoardMovement(fromY, toX,toY,toX);
            gameState.movePiece(fromY, toX,toY,toX);
            return true;
        }
        return false;
    }
}
