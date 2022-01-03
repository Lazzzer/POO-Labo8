package engine.piece;

import chess.PieceType;
import chess.PlayerColor;
import engine.move.OneCellMove;
import engine.rule.CastlingRule;

public class King extends SpecialPiece {

    public King(PlayerColor color) {
        super(PieceType.KING, color);
    }

    @Override
    public boolean move(Piece[][] gameState, int fromX, int fromY, int toX, int toY) {
        boolean isValid = false;
        if (!hasMoved)
            isValid = (new CastlingRule()).canCastle(gameState, fromX, fromY, toX, toY);

        if (!isValid)
            isValid = (new OneCellMove()).move(gameState, fromX, fromY, toX, toY);

        if (isValid && !hasMoved)
            hasMoved = true;

        return isValid;
    }
}
