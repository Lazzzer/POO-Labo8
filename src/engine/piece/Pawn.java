package engine.piece;

import chess.PieceType;
import chess.PlayerColor;
import engine.GameState;
import engine.move.ForwardMove;
import engine.rule.EnPassantRule;
import engine.rule.PawnTakeRule;

public class Pawn extends SpecialPiece{

    private final ForwardMove forwardMove;
    private int turnEnPassant;
    public Pawn(PlayerColor color) {
        super(PieceType.PAWN, color);
        turnEnPassant = 0;
        forwardMove = new ForwardMove();
    }

    private Pawn(Pawn piece) {
        this(piece.color);
        turnEnPassant = piece.turnEnPassant;
        hasMoved = piece.hasMoved;
    }

    @Override
    public boolean move(GameState gameState, int fromX, int fromY, int toX, int toY) {
        int nbCells = hasMoved ? 1 : 2;
        boolean isValid = EnPassantRule.canTakeEnPassant(gameState, fromX, fromY, toX, toY)
                            || PawnTakeRule.canTake(gameState, fromX, fromY, toX, toY)
                            || forwardMove.move(gameState, fromX, fromY, toX, toY, nbCells);

        if (isValid && !hasMoved) {
            hasMoved = true;
            if(Math.abs(toY - fromY) == 2)
                turnEnPassant = gameState.getNbTurns();
        }
        return isValid;
    }
    
    public int getTurnEnPassant() {
        return turnEnPassant;
    }

    @Override
    public Pawn clone() {
        return new Pawn(this);
    }
}
