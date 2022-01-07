package engine.rule;

import chess.PlayerColor;
import engine.GameState;
import engine.piece.Piece;

public class PawnTakeRule {
    private PawnTakeRule() {}

    public static boolean canTake(GameState gameState, int fromX, int fromY, int toX, int toY) {
        Piece movingPiece = gameState.getPiece(fromY, fromX);
        int direction = movingPiece.getColor() == PlayerColor.WHITE ? 1 : -1;

        return gameState.getPiece(toY, toX) != null && (toY == fromY + direction && (toX == fromX + direction || toX == fromX  - direction));
    }
}
