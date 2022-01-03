package engine.piece;

import chess.PieceType;
import chess.PlayerColor;
import engine.move.StraightMove;

public class Rook extends SpecialPiece{

    private final StraightMove straightMove;

    public Rook(PlayerColor color) {
        super(PieceType.ROOK, color);
        straightMove = new StraightMove();
    }

    @Override
    public boolean move(Piece[][] gameState, int fromX, int fromY, int toX, int toY) {
        boolean isValid = straightMove.move(gameState, fromX, fromY, toX, toY);
        if (isValid && !hasMoved)
            hasMoved = true;
        return isValid;
    }
}
