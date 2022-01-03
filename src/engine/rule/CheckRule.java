package engine.rule;

import chess.PlayerColor;
import engine.piece.King;
import engine.piece.Piece;

public class CheckRule {
    public static boolean isChecked(PlayerColor color, Piece[][] gameState) {
        int[] coordsKing = findKing(color, gameState);
        for (int i = 0; i < gameState.length; ++i) {
            for (int j = 0; j < gameState[0].length; ++j) {
                if (gameState[i][j] != null && gameState[i][j].getColor() != color
                        && gameState[i][j].move(gameState, j, i, coordsKing[1], coordsKing[0]))
                    return true;
            }
        }
        return false;
    }

    private static int[] findKing(PlayerColor color, Piece[][] gameState) {
        for (int i = 0; i < gameState.length; ++i) {
            for (int j = 0; j < gameState[0].length; ++j) {
                if (gameState[i][j] != null && gameState[i][j] instanceof King && gameState[i][j].getColor() == color)
                    return new int[]{i, j};
            }
        }
        throw new RuntimeException();
    }
}
