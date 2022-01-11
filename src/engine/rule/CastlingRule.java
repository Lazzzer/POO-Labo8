package engine.rule;

import chess.PieceType;
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

                for (int i = fromX; i >= toX; --i) {
                    if (CheckRule.isChecked(gameState.getTurn(), gameState, new int[]{toY, i}))
                        return false;
                }

                if (gameState.getPiece(validToY, 0) != null && gameState.getPiece(validToY, 0).getPieceType() == PieceType.ROOK) {
                    Rook rook = (Rook) gameState.getPiece(validToY, 0);

                    if (!rook.hasMoved() && rook.move(gameState, 0, validToY, QUEEN_SIDE_CELL + 1, validToY)) {
                        gameState.setPiece(rook, validToY, QUEEN_SIDE_CELL + 1);
                        gameState.setPiece(null, validToY, 0);
                        return true;
                    }
                }
            } else {

                for (int i = fromX; i <= toX; ++i) {
                    if (CheckRule.isChecked(gameState.getTurn(), gameState, new int[]{toY, i}))
                        return false;
                }

                if (gameState.getPiece(validToY, boardLimit) != null && gameState.getPiece(validToY, boardLimit).getPieceType() == PieceType.ROOK) {
                    Rook rook = (Rook) gameState.getPiece(validToY, boardLimit);

                    if (!rook.hasMoved() && rook.move(gameState, boardLimit, validToY, KING_SIDE_CELL - 1, validToY)) {
                        gameState.setPiece(rook, validToY, KING_SIDE_CELL - 1);
                        gameState.setPiece(null, validToY, boardLimit);
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
