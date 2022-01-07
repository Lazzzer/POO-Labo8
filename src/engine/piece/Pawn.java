package engine.piece;

import chess.PieceType;
import chess.PlayerColor;
import engine.GameState;
import engine.move.ForwardMove;
import engine.rule.EnPassantRule;
import engine.rule.PawnTakeRule;

public class Pawn extends SpecialPiece{

    private boolean takeableEnPassant;
    private final ForwardMove forwardMove;
    private int turnEnPassant;
    public Pawn(PlayerColor color) {
        super(PieceType.PAWN, color);
        takeableEnPassant = false;
        forwardMove = new ForwardMove();
    }

    private Pawn(Pawn piece) {
        this(piece.color);
        takeableEnPassant = piece.takeableEnPassant;
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
            takeableEnPassant = Math.abs(toY - fromY) == 2;
        }

        return isValid;
    }

    public boolean isTakeableEnPassant() {
        return takeableEnPassant;
    }

    @Override
    public Pawn clone() {
        return new Pawn(this);
    }
}
