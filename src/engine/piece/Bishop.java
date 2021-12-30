package engine.piece;

import chess.PieceType;
import chess.PlayerColor;
import engine.move.DiagonalMove;

public class Bishop extends Piece{

    public Bishop(PlayerColor color) {
        super(PieceType.BISHOP, color);
    }

    @Override
    public boolean move(Piece[][] gameState, int fromX, int fromY, int toX, int toY) {
        return (new DiagonalMove()).move(fromX, fromY, toX, toY);
    }
}
