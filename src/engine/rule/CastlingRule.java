package engine.rule;

import chess.PieceType;
import chess.PlayerColor;
import engine.GameState;
import engine.piece.King;
import engine.piece.Rook;

public class CastlingRule {

    private static final int KING_SIDE_CELL = 6;
    private static final int QUEEN_SIDE_CELL = 2;

    private CastlingRule() {}

    public static boolean canCastle(GameState gameState, int fromX, int fromY, int toX, int toY) {
        final int boardLimit = gameState.getBoardLength() - 1;
        int validToY = gameState.getPiece(fromY, fromX).getColor() == PlayerColor.WHITE ? 0 : boardLimit;

        if (toY == validToY && (toX == QUEEN_SIDE_CELL || toX == KING_SIDE_CELL)) {
            int rookX = toX == QUEEN_SIDE_CELL ? 0 : 7;
            int beginLoop = toX == QUEEN_SIDE_CELL ? toX : fromX;
            int loopLength = toX == QUEEN_SIDE_CELL ? fromX : rookX;
            int nextKing = toX == QUEEN_SIDE_CELL ? fromX - 1 : fromX + 1;

            if (gameState.getPiece(validToY, rookX) != null && gameState.getPiece(validToY, rookX).getPieceType() == PieceType.ROOK) {
                Rook rook = (Rook) gameState.getPiece(validToY, rookX);

                if (!rook.hasMoved() && rook.move(gameState, rookX, validToY, nextKing, validToY)) {
                    King tmpKing = new King(gameState.getTurn());
    
                    for (int i = beginLoop; i < loopLength; ++i) {
                        gameState.setPiece(tmpKing,validToY,i);
                        if (CheckRule.isChecked(gameState.getTurn(), gameState, new int[]{toY, i})){
                            gameState.revertBoard();
                            return false;
                        }
                    }
                    gameState.revertBoard();
                    gameState.setPiece(rook, validToY, nextKing);
                    gameState.setPiece(null, validToY, rookX);
                    return true;
                }
            }
        }
        return false;
    }
}
