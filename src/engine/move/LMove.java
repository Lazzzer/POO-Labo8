package engine.move;

import engine.GameState;

/**
 * Classe représentant un mouvement en L d'une pièce
 * @author Alexandre Jaquier
 * @author Lazar Pavicevic
 */
public class LMove extends Move {

    /**
     * Contrôle si le déplacement d'une pièce vers une case données est légal ou non
     * @param gameState État du jeu
     * @param fromX Colonne de départ
     * @param fromY Ligne de départ
     * @param toX Colonne d'arrivée
     * @param toY Ligne d'arrivée
     * @return Vrai si le mouvement n'est pas bloqué
     */
    @Override
    public boolean move(GameState gameState, int fromX, int fromY, int toX, int toY) {
        return super.move(gameState, fromX, fromY, toX, toY) && (toX - fromX) * (toX - fromX) + (toY - fromY) * (toY - fromY) == 5;
    }
}
