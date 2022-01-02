package engine.rule;

import chess.PlayerColor;
import engine.piece.Pawn;
import engine.piece.Piece;

public class PromotionRule {
    public static boolean canPromote(PlayerColor color, Piece[][] gameState) {
        int promotionLine = color == PlayerColor.WHITE ? gameState.length - 1 : 0;

        for (int i = 0; i < gameState.length; ++i) {
            if (gameState[promotionLine][i] instanceof Pawn)
                return true;
        }
        return false;
    }
}
