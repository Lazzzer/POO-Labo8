package engine.rule;

import chess.PlayerColor;
import engine.GameState;
import engine.piece.King;

public class CheckRule {

    private CheckRule() {}

    public static boolean isChecked(PlayerColor color, GameState gameState) {
        int[] coordsKing = findKing(color, gameState);
        for (int i = 0; i < gameState.getBoard().length; ++i) {
            for (int j = 0; j < gameState.getBoard()[0].length; ++j) {
                if (gameState.getPiece(i, j) != null && gameState.getPiece(i, j).getColor() != color
                        && gameState.getPiece(i, j).move(gameState, j, i, coordsKing[1], coordsKing[0]))
                    return true;
            }
        }
        return false;
    }

    private static int[] findKing(PlayerColor color, GameState gameState) {
        for (int i = 0; i < gameState.getBoard().length; ++i) {
            for (int j = 0; j < gameState.getBoard()[0].length; ++j) {
                if (gameState.getPiece(i, j) != null && gameState.getPiece(i, j) instanceof King && gameState.getPiece(i, j).getColor() == color)
                    return new int[]{i, j};
            }
        }
        throw new RuntimeException();
    }
}
