package engine.piece;

import chess.PieceType;
import chess.PlayerColor;
import engine.GameState;
import engine.move.OrthogonalMove;

/**
 * Classe représentant une tour
 * @author Alexandre Jaquier
 * @author Lazar Pavicevic
 */
public class Rook extends SpecialPiece{

    private final OrthogonalMove orthogonalMove;

    /**
     * Constructeur de base
     * @param color Couleur de la pièce
     */
    public Rook(PlayerColor color) {
        super(PieceType.ROOK, color);
        orthogonalMove = new OrthogonalMove();
    }

    /**
     * Constructeur de copie
     * @param piece Piece qui est copiée
     */
    private Rook(Rook piece) {
        this(piece.color);
        hasMoved = piece.hasMoved;
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
        boolean isValid = orthogonalMove.move(gameState, fromX, fromY, toX, toY);
        if (isValid && !hasMoved)
            hasMoved = true;
        return isValid;
    }

    /**
     * Copie la pièce
     * @return La pièce copiée
     */
    @Override
    public Rook copy() {
        return new Rook(this);
    }
}
