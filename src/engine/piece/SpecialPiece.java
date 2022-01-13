package engine.piece;

import chess.PieceType;
import chess.PlayerColor;
import engine.GameState;

/**
 * Classe représentant une pièce avec un comportement spécial
 * @author Alexandre Jaquier
 * @author Lazar Pavicevic
 */
abstract class SpecialPiece extends Piece{
    protected boolean hasMoved;

    /**
     * Constructeur de base
     * @param type Type de pièce
     * @param color Couleur de la pièce
     */
    protected SpecialPiece(PieceType type, PlayerColor color) {
        super(type, color);
        hasMoved = false;
    }

    /**
     * Retourne si la pièce a effectué un mouvement
     * @return Vrai si la pièce a bougé
     */
    public boolean hasMoved() {
        return hasMoved;
    }

    /**
     * Contrôle si le déplacement de la pièce vers une case donnée est légal ou non
     * @param gameState État du jeu
     * @param fromX Colonne de départ
     * @param fromY Ligne de départ
     * @param toX Colonne d'arrivée
     * @param toY Ligne d'arrivée
     * @return Vrai si le mouvement est légal
     */
    public abstract boolean move(GameState gameState, int fromX, int fromY, int toX, int toY);
}
