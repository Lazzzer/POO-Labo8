package engine.piece;

import chess.PieceType;
import chess.PlayerColor;
import engine.move.DiagonalMove;

public class Bishop extends Piece {

    private final DiagonalMove diagonalMove;

    public Bishop(PlayerColor color) {
        super(PieceType.BISHOP, color);
        diagonalMove = new DiagonalMove();
    }

    @Override
    public boolean move(Piece[][] gameState, int fromX, int fromY, int toX, int toY) {
        return diagonalMove.move(gameState, fromX, fromY, toX, toY);
    }
}
