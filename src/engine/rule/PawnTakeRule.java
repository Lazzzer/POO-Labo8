package engine.rule;

import chess.PlayerColor;
import engine.GameState;
/**
 * Classe représentant l'attaque basique d'un pion
 * @author Alexandre Jaquier
 * @author Lazar Pavicevic
 */
public class PawnTakeRule {
    private PawnTakeRule() {}
    
    /**
     * Contrôle si un pion sur la case de départ passé en paramètre peut attaquer la case d'arrivée
     * @param gameState
     * @param fromCol
     * @param fromRow
     * @param toCol
     * @param toRow
     * @return Vrai si un pion sur la case de départ peut attaquer la case d'arrivée
     */
    public static boolean canTake(GameState gameState, int fromCol, int fromRow, int toCol, int toRow) {
        final int direction = gameState.getPiece(fromRow, fromCol).getColor() == PlayerColor.WHITE ? 1 : -1;
        return gameState.getPiece(toRow, toCol) != null && (toRow == fromRow + direction && (toCol == fromCol + direction || toCol == fromCol  - direction));
    }
}
