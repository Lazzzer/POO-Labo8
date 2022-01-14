package engine.move;

import engine.GameState;

/**
 * Classe représentant un mouvement d'une pièce vers une case donnée
 * @author Alexandre Jaquier
 * @author Lazar Pavicevic
 */
abstract class Move {
    /**
     * Contrôle si le déplacement d'une pièce vers une case données est légal ou non
     * Le mouvement est légal si la case de destination n'est pas occupé par une pièce de la même couleur
     * @param gameState État du jeu
     * @param fromX Colonne de départ
     * @param fromY Ligne de départ
     * @param toX Colonne d'arrivée
     * @param toY Ligne d'arrivée
     * @return Vrai si le mouvement est légal
     */
    protected boolean move(GameState gameState, int fromX, int fromY, int toX, int toY) {
        return !(gameState.getPiece(toY, toX) != null && gameState.getPiece(fromY, fromX).getColor() == gameState.getPiece(toY,toX).getColor());
    }
}
