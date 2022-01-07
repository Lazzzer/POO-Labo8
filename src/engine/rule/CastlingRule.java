package engine.rule;

import chess.PlayerColor;
import engine.GameState;
import engine.piece.Rook;


public class CastlingRule {

    private static final int KING_SIDE_CELL = 6;
    private static final int QUEEN_SIDE_CELL = 2;

    private CastlingRule() {}

    public static boolean canCastle(GameState gameState, int fromX, int fromY, int toX, int toY) {
        final int boardLimit = gameState.getBoard().length - 1;
        int validToY = gameState.getPiece(fromY, fromX).getColor() == PlayerColor.WHITE ? 0 : boardLimit;

        if (toY == validToY && (toX == QUEEN_SIDE_CELL || toX == KING_SIDE_CELL)) {
            if (toX == QUEEN_SIDE_CELL) {
                if (gameState.getPiece(validToY, 0) instanceof Rook && !((Rook) gameState.getPiece(validToY, 0)).hasMoved()
                        && gameState.getPiece(validToY, 0).move(gameState, 0, validToY, QUEEN_SIDE_CELL + 1, validToY)) {
                    gameState.setPiece(gameState.getPiece(validToY, 0), validToY, QUEEN_SIDE_CELL + 1);
                    gameState.setPiece(null, validToY, 0);
                    return true;
                }
            } else {
                if (gameState.getPiece(validToY, boardLimit) instanceof Rook && !((Rook) gameState.getPiece(validToY, boardLimit)).hasMoved()
                        && gameState.getPiece(validToY, boardLimit).move(gameState, boardLimit, validToY, KING_SIDE_CELL - 1,
                        validToY)) {
                    gameState.setPiece(gameState.getPiece(validToY, boardLimit), validToY, KING_SIDE_CELL - 1);
                    gameState.setPiece(null, validToY, boardLimit);
                    return true;
                }
            }
        }
        return false;
    }
}
