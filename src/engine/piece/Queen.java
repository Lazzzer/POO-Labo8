package engine.piece;

import chess.PieceType;
import chess.PlayerColor;
import engine.GameState;
import engine.move.DiagonalMove;
import engine.move.OrthogonalMove;

public class Queen extends Piece{

    private final OrthogonalMove orthogonalMove;
    private final DiagonalMove diagonalMove;

    public Queen(PlayerColor color) {
        super(PieceType.QUEEN, color);
        orthogonalMove = new OrthogonalMove();
        diagonalMove = new DiagonalMove();
    }

    private Queen(Queen piece) {
        this(piece.color);
    }

    @Override
    public boolean move(GameState gameState, int fromX, int fromY, int toX, int toY) {
        return orthogonalMove.move(gameState, fromX, fromY, toX, toY) || diagonalMove.move(gameState, fromX, fromY, toX, toY);
    }

    @Override
    public Queen clone() {
        return new Queen(this);
    }
}
