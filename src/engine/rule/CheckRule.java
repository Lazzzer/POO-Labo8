package engine.rule;

import chess.PlayerColor;
import engine.GameState;
/**
 * Classe représentant la mise en échec d'un roi
 * @author Alexandre Jaquier
 * @author Lazar Pavicevic
 */
public class CheckRule {

    private CheckRule() {}
    
    /**
     * Contrôle si un roi est en échec ou non
     * @param color
     * @param gameState
     * @param checkCoords
     * @return Vrai si le roi de la couleur passée en paramètre est en échec
     */
    public static boolean isChecked(PlayerColor color, GameState gameState, int[] checkCoords) {
        for (int i = 0; i < gameState.getBoardLength(); ++i) {
            for (int j = 0; j < gameState.getBoardLength(); ++j) {
                if (gameState.getPiece(i, j) != null
                        && gameState.getPiece(i, j).getColor() != color
                        && gameState.getPiece(i, j).move(gameState, j, i, checkCoords[1], checkCoords[0]))
                    return true;
            }
        }
        return false;
    }
}
