package engine.move;

import engine.GameState;

/**
 * Classe représentant un mouvement d'une pièce vers une case donnée, ce mouvement peut être
 * bloqué par une autre pièce
 * @author Alexandre Jaquier
 * @author Lazar Pavicevic
 */
abstract class BlockableMove extends Move{
    @Override
    protected boolean move(GameState gameState, int fromX, int fromY, int toX, int toY) {
        return super.move(gameState, fromX, fromY, toX, toY) && checkBlockingPieces(gameState, fromX, fromY, toX, toY);
    }
    
    /**
     * Contrôle si le déplacement d'une pièce vers une case donnée est bloqué ou non
     * @param gameState État du jeu
     * @param fromX Colonne de départ
     * @param fromY Ligne de départ
     * @param toX Colonne d'arrivée
     * @param toY Ligne d'arrivée
     * @return Vrai si le mouvement n'est pas bloqué
     */
    protected abstract boolean checkBlockingPieces(GameState gameState, int fromX, int fromY, int toX, int toY);
}
