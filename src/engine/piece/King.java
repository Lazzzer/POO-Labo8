package engine.piece;

import chess.PieceType;
import chess.PlayerColor;
import engine.GameState;
import engine.move.DiagonalMove;
import engine.move.OrthogonalMove;
import engine.rule.CastlingRule;

/**
 * Classe représentant un roi
 * @author Alexandre Jaquier
 * @author Lazar Pavicevic
 */
public class King extends SpecialPiece {

    private final OrthogonalMove orthogonalMove;
    private final DiagonalMove diagonalMove;

    /**
     * Constructeur de base
     * @param color Couleur de la pièce
     */
    public King(PlayerColor color) {
        super(PieceType.KING, color);
        orthogonalMove = new OrthogonalMove();
        diagonalMove = new DiagonalMove();
    }

    /**
     * Constructeur de copie
     * @param piece Piece qui est copiée
     */
    private King(King piece) {
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
        boolean isValid = orthogonalMove.move(gameState, fromX, fromY, toX, toY, 1)
                            || diagonalMove.move(gameState, fromX, fromY, toX, toY, 1);

        if (!hasMoved && !isValid && !gameState.isChecked())
            isValid = CastlingRule.canCastle(gameState, fromX, fromY, toX, toY);

        if (isValid && !hasMoved){
            hasMoved = true;
        }

        return isValid;
    }

    /**
     * Copie la pièce
     * @return La pièce copiée
     */
    @Override
    public King copy() {
        return new King(this);
    }
}
