package engine.piece;

import chess.PieceType;
import chess.PlayerColor;

public abstract class Piece {
    protected PieceType type;
    protected PlayerColor color;

    protected Piece(PieceType type, PlayerColor color) {
        this.type = type;
        this.color = color;
    }
    
    public PlayerColor getColor(){
        return color;
    }
    public PieceType getPieceType(){
        return type;
    }
    public abstract boolean move(Piece[][] gameState, int fromX, int fromY, int toX, int toY);
}