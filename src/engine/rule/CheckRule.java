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

    /**
     * Contrôle si un des joueurs est en échec et mat ou en PAT
     * @param color Couleur du joueur à contrôler
     * @return Vrai si le joueur passé en paramètre est en échec et mat ou en PAT
     */
    public static boolean isEndGame(PlayerColor color, GameState gameState) {
        boolean endGame = true;
        for (int i = 0; i < gameState.getBoardLength(); i++) {
            for (int j = 0; j < gameState.getBoardLength(); j++) {
                if (gameState.getPiece(i, j) != null && gameState.getPiece(i, j).getColor() == color) {
                    for (int k = 0; k < gameState.getBoardLength(); k++) {
                        for (int l = 0; l < gameState.getBoardLength(); l++) {
                            gameState.createBoardMovement(i, j, k, l);
                            if (gameState.getPiece(i, j).move(gameState, j, i, l, k)) {
                                gameState.movePiece(i, j, k, l);
                                endGame = CheckRule.isChecked(color, gameState, gameState.getKingCoords(color));
                                gameState.revertMoves();
                            }
                            gameState.removeMovedPieces();
                            if (!endGame) {
                                return false;
                            }
                        }
                    }
                }
            }
        }
        return true;
    }
}
