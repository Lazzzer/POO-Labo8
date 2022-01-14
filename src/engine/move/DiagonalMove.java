package engine.move;

import engine.GameState;

/**
 * Classe représentant un mouvement en diagonal et bloquable d'une pièce
 * @author Alexandre Jaquier
 * @author Lazar Pavicevic
 */
public class DiagonalMove extends BlockableMove {

    /**
     * Contrôle si le déplacement d'une pièce vers une case données est légal ou non sur toute la longueur de
     * l'échiquier. Le mouvement est légal s'il est en diagonal.
     * @param gameState État du jeu
     * @param fromX Colonne de départ
     * @param fromY Ligne de départ
     * @param toX Colonne d'arrivée
     * @param toY Ligne d'arrivée
     * @return Vrai si le mouvement est légal
     */
    public boolean move(GameState gameState, int fromX, int fromY, int toX, int toY) {
        return move(gameState, fromX, fromY, toX, toY, gameState.getBoardLength() - 1);
    }

    /**
     * Contrôle si le déplacement d'une pièce vers une case données est légal ou non sur un nombre de cases donné.
     * Le mouvement est légal s'il est en diagonal.
     * @param gameState État du jeu
     * @param fromX Colonne de départ
     * @param fromY Ligne de départ
     * @param toX Colonne d'arrivée
     * @param toY Ligne d'arrivée
     * @param nbCells Nombre de cases à traverser
     * @return Vrai si le mouvement est légal
     */
    public boolean move(GameState gameState, int fromX, int fromY, int toX, int toY, int nbCells) {
        return Math.abs(toX - fromX) == Math.abs(toY - fromY) && Math.abs(toX - fromX) <= nbCells
                && super.move(gameState, fromX, fromY, toX, toY);
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
    @Override
    protected boolean checkBlockingPieces(GameState gameState, int fromX, int fromY, int toX, int toY) {
        int distanceX = Math.abs(toX - fromX);
        int directionX = toX > fromX ? 1 : -1;
        int directionY = toY > fromY ? 1 : -1;

        for (int i = 1; i <= distanceX - 1; ++i) {
            if (gameState.getPiece(fromY + i * directionY, fromX + i * directionX) != null)
                return false;
        }
        return true;
    }
}
