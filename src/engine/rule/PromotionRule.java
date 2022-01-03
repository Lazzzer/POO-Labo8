package engine.rule;

import chess.PlayerColor;
import engine.piece.Piece;

public class PromotionRule {

    private PromotionRule() {}

    public static boolean canPromote(PlayerColor color, Piece[][] gameState, int toY) {
        final int promotionLine = color == PlayerColor.WHITE ? gameState.length - 1 : 0;
        return toY == promotionLine;
    }
}
