package engine.piece;

import chess.PieceType;
import chess.PlayerColor;
import engine.move.StraightMove;

public class Rook extends SpecialPiece{

    public Rook(PlayerColor color) {
        super(PieceType.ROOK, color);
    }

    @Override
    public boolean move(Piece[][] gameState, int fromX, int fromY, int toX, int toY) {
        return (new StraightMove()).move(fromX, fromY, toX, toY);
    }
}
