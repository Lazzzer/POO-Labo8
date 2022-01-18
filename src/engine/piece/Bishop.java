package engine.piece;

import chess.PieceType;
import chess.PlayerColor;
import engine.GameState;
import engine.move.DiagonalMove;

/**
 * Classe représentant un fou
 * @author Alexandre Jaquier
 * @author Lazar Pavicevic
 */
public class Bishop extends Piece {

    private final DiagonalMove diagonalMove;

    /**
     * Constructeur de base
     * @param color Couleur de de la pièce
     */
    public Bishop(PlayerColor color) {
        super(PieceType.BISHOP, color);
        diagonalMove = new DiagonalMove();
    }

    /**
     * Constructeur de copie
     * @param piece Piece qui est copiée
     */
    private Bishop(Bishop piece) {
        this(piece.color);
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
    @Override
    public boolean move(GameState gameState, int fromX, int fromY, int toX, int toY) {
        return diagonalMove.move(gameState, fromX, fromY, toX, toY);
    }

    /**
     * Copie la pièce
     * @return La pièce copiée
     */
    @Override
    public Bishop copy() {
        return new Bishop(this);
    }
}
