package engine.rule;

import chess.PlayerColor;
import engine.piece.Piece;

public class PawnTakeRule {
    private PawnTakeRule() {}

    public static boolean canTake(Piece[][] gameState, int fromX, int fromY, int toX, int toY) {
        Piece movingPiece = gameState[fromY][fromX];
        int direction = movingPiece.getColor() == PlayerColor.WHITE ? 1 : -1;

        return gameState[toY][toX] != null && (toY == fromY + direction && (toX == fromX + direction || toX == fromX  - direction));
    }
}
