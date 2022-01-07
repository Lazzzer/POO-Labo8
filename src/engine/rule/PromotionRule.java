package engine.rule;

import chess.PlayerColor;
import engine.GameState;
import engine.piece.Piece;

public class PromotionRule {

    private PromotionRule() {}

    public static boolean canPromote(PlayerColor color, GameState gameState, int toY) {
        final int promotionLine = color == PlayerColor.WHITE ? gameState.getBoard().length - 1 : 0;
        return toY == promotionLine;
    }
}
