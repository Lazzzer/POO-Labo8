package engine.rule;

import chess.PlayerColor;
import engine.GameState;

/**
 * Classe représentant la promotion d'un pion
 * @author Alexandre Jaquier
 * @author Lazar Pavicevic
 */
public class PromotionRule {

    private PromotionRule() {}

    /**
     * Contrôle si une pièce sur la case de départ passée en paramètre peut effectuer une promotion
     * @param color
     * @param gameState
     * @param toRow
     * @return Vrai si la promotion est possible
     */
    public static boolean canPromote(PlayerColor color, GameState gameState, int toRow) {
        final int promotionLine = color == PlayerColor.WHITE ? gameState.getBoardLength() - 1 : 0;
        return toRow == promotionLine;
    }
}
