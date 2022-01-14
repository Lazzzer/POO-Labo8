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
     * Contrôle si la pièce issue des coordonnées est en échec ou non
     * @param color Couleur de la pièce
     * @param gameState État du jeu
     * @param checkCoords Coordonnée de la pièce dans le gameState
     * @return Vrai si la pièce de la couleur passée en paramètre est en échec
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
