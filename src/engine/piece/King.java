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

    public King(PlayerColor color) {
        super(PieceType.KING, color);
        orthogonalMove = new OrthogonalMove();
        diagonalMove = new DiagonalMove();
    }
    private King(King piece) {
        this(piece.color);
        hasMoved = piece.hasMoved;
    }

    @Override
    public boolean move(GameState gameState, int fromX, int fromY, int toX, int toY) {
        boolean isValid = orthogonalMove.move(gameState, fromX, fromY, toX, toY, 1)
                            || diagonalMove.move(gameState, fromX, fromY, toX, toY, 1);

        if (!hasMoved && !isValid && !gameState.getIsChecked())
            isValid = CastlingRule.canCastle(gameState, fromX, fromY, toX, toY);

        if (isValid && !hasMoved){
            hasMoved = true;
        }

        return isValid;
    }

    @Override
    public King clone() {
        return new King(this);
    }
}
