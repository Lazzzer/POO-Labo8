package engine.piece;

import chess.PieceType;
import chess.PlayerColor;
import engine.move.LMove;

public class Knight extends Piece {

    private final LMove lMove;

    public Knight(PlayerColor color) {
        super(PieceType.KNIGHT, color);
        lMove = new LMove();
    }

    @Override
    public boolean move(Piece[][] gameState, int fromX, int fromY, int toX, int toY) {
        return lMove.move(gameState, fromX, fromY, toX, toY);
    }
}
