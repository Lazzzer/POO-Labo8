package engine.rule;

import chess.PlayerColor;
import engine.piece.Piece;
import engine.piece.Rook;


public class CastlingRule {

    static final int KING_SIDE_CELL = 6;
    static final int QUEEN_SIDE_CELL = 2;

    public boolean canCastle(Piece[][] gameState, int fromX, int fromY, int toX, int toY) {
        final int boardLimit = gameState.length - 1;
        int validToY = gameState[fromY][fromX].getColor() == PlayerColor.WHITE ? 0 : boardLimit;

        if (toY == validToY && (toX == QUEEN_SIDE_CELL || toX == KING_SIDE_CELL)) {
            if (toX == QUEEN_SIDE_CELL) {
                if (gameState[validToY][0] instanceof Rook
                        && gameState[validToY][0].move(gameState, 0, validToY, QUEEN_SIDE_CELL + 1, validToY)) {
                    gameState[validToY][QUEEN_SIDE_CELL + 1] = gameState[validToY][0];
                    gameState[validToY][0] = null;
                    return true;
                }
            } else {
                if (gameState[validToY][boardLimit] instanceof Rook
                        && gameState[validToY][boardLimit].move(gameState, boardLimit, validToY, KING_SIDE_CELL - 1,
                        validToY)) {
                    gameState[validToY][KING_SIDE_CELL - 1] = gameState[validToY][boardLimit];
                    gameState[validToY][boardLimit] = null;
                    return true;
                }
            }
        }
        return false;
    }
}
