package engine.piece;

import chess.PieceType;
import chess.PlayerColor;
import engine.GameState;
import engine.move.DiagonalMove;

public class Bishop extends Piece {

    private final DiagonalMove diagonalMove;

    public Bishop(PlayerColor color) {
        super(PieceType.BISHOP, color);
        diagonalMove = new DiagonalMove();
    }

    private Bishop(Bishop piece) {
        this(piece.color);
    }

    @Override
    public boolean move(GameState gameState, int fromX, int fromY, int toX, int toY) {
        return diagonalMove.move(gameState.getBoard(), fromX, fromY, toX, toY);
    }

    @Override
    public Bishop clone() {
        return new Bishop(this);
    }
}
