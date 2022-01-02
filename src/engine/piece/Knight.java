package engine.piece;

import chess.PieceType;
import chess.PlayerColor;
import engine.move.LMove;

public class Knight extends Piece {

    public Knight(PlayerColor color) {
        super(PieceType.KNIGHT, color);
    }

    @Override
    public boolean move(Piece[][] gameState, int fromX, int fromY, int toX, int toY) {
        return (new LMove()).move(fromX, fromY, toX, toY);
    }
}
