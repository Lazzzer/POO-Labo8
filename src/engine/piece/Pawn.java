package engine.piece;

import chess.PieceType;
import chess.PlayerColor;
import engine.GameState;
import engine.move.DiagonalMove;
import engine.move.OrthogonalMove;
import engine.rule.EnPassantRule;

/**
 * Classe représentant un pion
 * @author Alexandre Jaquier
 * @author Lazar Pavicevic
 */
public class Pawn extends SpecialPiece{

    private final OrthogonalMove orthogonalMove;
    private final DiagonalMove diagonalMove;
    private int turnEnPassant;

    /**
     * Constructeur de base
     * @param color Couleur de la pièce
     */
    public Pawn(PlayerColor color) {
        super(PieceType.PAWN, color);
        turnEnPassant = 0;
        orthogonalMove = new OrthogonalMove();
        diagonalMove = new DiagonalMove();
    }

    /**
     * Constructeur de copie
     * @param piece Piece qui est copiée
     */
    private Pawn(Pawn piece) {
        this(piece.color);
        turnEnPassant = piece.turnEnPassant;
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
        int directionY = toY >= fromY ? 1 : -1;
        if (getColor() == PlayerColor.WHITE) {
            if (directionY != 1)
                return false;
        } else if (directionY != -1){
            return false;
        }

        boolean isValid = EnPassantRule.canTakeEnPassant(gameState, fromX, fromY, toX, toY)
                || gameState.getPiece(toY, toX) != null && diagonalMove.move(gameState, fromX, fromY, toX, toY, 1)
                || toX == fromX && gameState.getPiece(toY, toX) == null
                                && orthogonalMove.move(gameState, fromX, fromY, toX, toY, hasMoved ? 1 : 2);

        if (isValid && !hasMoved) {
            hasMoved = true;
            if(Math.abs(toY - fromY) == 2)
                turnEnPassant = gameState.getNbTurns();
        }
        return isValid;
    }

    /**
     * Retourne le tour quand une prise en passant de cette pièce est possible
     * @return Le tour quand une prise en passant de cette pièce est possible
     */
    public int getTurnEnPassant() {
        return turnEnPassant;
    }

    /**
     * Clône la pièce
     * @return La pièce clônée
     */
    @Override
    public Pawn clone() {
        return new Pawn(this);
    }
}
