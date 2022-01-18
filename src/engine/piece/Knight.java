package engine.piece;

import chess.PieceType;
import chess.PlayerColor;
import engine.GameState;
import engine.move.LMove;

/**
 * Classe représentant un cavalier
 * @author Alexandre Jaquier
 * @author Lazar Pavicevic
 */
public class Knight extends Piece {

    private final LMove lMove;

    /**
     * Constructeur de base
     * @param color Couleur de la pièce
     */
    public Knight(PlayerColor color) {
        super(PieceType.KNIGHT, color);
        lMove = new LMove();
    }

    /**
     * Constructeur de copie
     * @param piece Piece qui est copiée
     */
    private Knight(Knight piece) {
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
        return lMove.move(gameState, fromX, fromY, toX, toY);
    }

    /**
     * Copie la pièce
     * @return La pièce copiée
     */
    @Override
    public Knight copy() {
        return new Knight(this);
    }
}
