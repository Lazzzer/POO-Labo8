package engine.piece;

import chess.PieceType;
import chess.PlayerColor;
import engine.move.DiagonalMove;
import engine.move.StraightMove;

public class Queen extends Piece{

    public Queen(PlayerColor color) {
        super(PieceType.QUEEN, color);
    }

    @Override
    public boolean move(Piece[][] gameState, int fromX, int fromY, int toX, int toY) {
        return (new StraightMove()).move(gameState, fromX, fromY, toX, toY)
                || (new DiagonalMove()).move(gameState, fromX, fromY, toX, toY);
    }
}
