package engine.piece;

import chess.PieceType;
import chess.PlayerColor;
import engine.GameState;
import engine.move.DiagonalMove;
import engine.move.OrthogonalMove;

/**
 * Classe représentant une reine
 * @author Alexandre Jaquier
 * @author Lazar Pavicevic
 */
public class Queen extends Piece{

    private final OrthogonalMove orthogonalMove;
    private final DiagonalMove diagonalMove;

    /**
     * Constructeur de base
     * @param color Couleur de la pièce
     */
    public Queen(PlayerColor color) {
        super(PieceType.QUEEN, color);
        orthogonalMove = new OrthogonalMove();
        diagonalMove = new DiagonalMove();
    }

    /**
     * Constructeur de copie
     * @param piece Piece qui est copiée
     */
    private Queen(Queen piece) {
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
        return orthogonalMove.move(gameState, fromX, fromY, toX, toY) || diagonalMove.move(gameState, fromX, fromY, toX, toY);
    }

    /**
     * Clône la pièce
     * @return La pièce clônée
     */
    @Override
    public Queen clone() {
        return new Queen(this);
    }
}
