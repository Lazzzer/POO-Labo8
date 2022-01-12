package engine.rule;

import chess.PieceType;
import chess.PlayerColor;
import engine.GameState;

public class CheckRule {

    private CheckRule() {}

    public static boolean isChecked(PlayerColor color, GameState gameState, int[] checkCoords) {
        for (int i = 0; i < gameState.getBoardLength(); ++i) {
            for (int j = 0; j < gameState.getBoardLength(); ++j) {
                if (gameState.getPiece(i, j) != null
                        && gameState.getPiece(i, j).getColor() != color
                        && gameState.getPiece(i, j).move(gameState, j, i, checkCoords[1], checkCoords[0]))
                    return true;
            }
        }
        return false;
    }
}
