package engine.piece;

import chess.PieceType;
import chess.PlayerColor;
import engine.GameState;
import engine.move.OneCellMove;
import engine.rule.CastlingRule;

public class King extends SpecialPiece {

    private final OneCellMove oneCellMove;

    public King(PlayerColor color) {
        super(PieceType.KING, color);
        oneCellMove = new OneCellMove();
    }
    private King(King piece) {
        this(piece.color);
        hasMoved = piece.hasMoved;
    }

    @Override
    public boolean move(GameState gameState, int fromX, int fromY, int toX, int toY) {
        boolean isValid = false;
        if (!hasMoved)
            isValid = CastlingRule.canCastle(gameState.getBoard(), fromX, fromY, toX, toY);

        if (!isValid)
            isValid = oneCellMove.move(gameState.getBoard(), fromX, fromY, toX, toY);

        if (isValid && !hasMoved)
            hasMoved = true;

        return isValid;
    }

    @Override
    public King clone() {
        return new King(this);
    }
}
