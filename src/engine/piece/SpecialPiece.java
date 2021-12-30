package engine.piece;

import chess.PieceType;
import chess.PlayerColor;

public abstract class SpecialPiece extends Piece{
    protected boolean hasMoved;

    protected SpecialPiece(PieceType type, PlayerColor color) {
        super(type, color);
        hasMoved = false;
    }

    public abstract boolean move(Piece[][] gameState, int fromX, int fromY, int toX, int toY);
}
