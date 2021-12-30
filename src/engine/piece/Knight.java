package engine.piece;

import chess.PieceType;
import chess.PlayerColor;

public class Knight extends Piece {

    public Knight(PlayerColor color) {
        super(PieceType.KNIGHT, color);
    }

    @Override
    public boolean move(Piece[][] gameState, int fromX, int fromY, int toX, int toY) {
        return (Math.abs(toX - fromX) == 1 && Math.abs(toY - fromY) == 2) || (Math.abs(toX - fromX) == 2 && Math.abs(toY - fromY) == 1);
    }
}
