package engine.piece;

import chess.PieceType;
import chess.PlayerColor;
import engine.GameState;
import engine.move.StraightMove;

public class Rook extends SpecialPiece{

    private final StraightMove straightMove;

    public Rook(PlayerColor color) {
        super(PieceType.ROOK, color);
        straightMove = new StraightMove();
    }

    private Rook(Rook piece) {
        this(piece.color);
        hasMoved = piece.hasMoved;
    }

    @Override
    public boolean move(GameState gameState, int fromX, int fromY, int toX, int toY) {
        boolean isValid = straightMove.move(gameState, fromX, fromY, toX, toY);
        if (isValid && !hasMoved)
            hasMoved = true;
        return isValid;
    }

    @Override
    public Rook clone() {
        return new Rook(this);
    }
}
