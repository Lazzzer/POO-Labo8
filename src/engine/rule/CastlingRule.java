package engine.rule;

import chess.PieceType;
import chess.PlayerColor;
import engine.GameState;
import engine.piece.King;
import engine.piece.Rook;

/**
 * Classe représentant le castle effectué par un roi
 * @author Alexandre Jaquier
 * @author Lazar Pavicevic
 */
public class CastlingRule {
    
    private static final int KING_SIDE_CELL = 6;
    private static final int QUEEN_SIDE_CELL = 2;
    
    private CastlingRule() {}
    
    /**
     * Contrôle si un mouvement donné peut effectuer un castle
     * @param gameState
     * @param fromCol
     * @param fromRow
     * @param toCol
     * @param toRow
     * @return Vrai si le castle peut se faire
     */
    public static boolean canCastle(GameState gameState, int fromCol, int fromRow, int toCol, int toRow) {
        final int boardLimit = gameState.getBoardLength() - 1;
        int validToY = gameState.getPiece(fromRow, fromCol).getColor() == PlayerColor.WHITE ? 0 : boardLimit;
        if (toRow == validToY && (toCol == QUEEN_SIDE_CELL || toCol == KING_SIDE_CELL)) {
            // crée toutes les variables permettant de différencier quel type de castle est effectué
            int rookX = toCol == QUEEN_SIDE_CELL ? 0 : 7;
            int beginLoop = toCol == QUEEN_SIDE_CELL ? toCol+1 : fromCol+1;
            int loopLength = toCol == QUEEN_SIDE_CELL ? fromCol : rookX;
            int nextKing = toCol == QUEEN_SIDE_CELL ? fromCol - 1 : fromCol + 1;
            
            // Contrôle si la pièce à la position de la tour est bien une tour
            if (gameState.getPiece(validToY, rookX) != null && gameState.getPiece(validToY, rookX).getPieceType() == PieceType.ROOK) {
                Rook rook = (Rook) gameState.getPiece(validToY, rookX);
                boolean notCheck = true;
                // Contrôle si la tour est éligible au castle
                if (!rook.hasMoved() && rook.move(gameState, rookX, validToY, nextKing, validToY)) {
                    // Crée un roi temporaire afin de vérifier si les cases par lesquelles le roi
                    // passera ne sont pas contrôlées par l'autre joueur
                    King tmpKing = new King(gameState.getTurn());
                    for (int i = beginLoop; i < loopLength; ++i) {
                        gameState.setPiece(tmpKing,validToY,i);
                        if (CheckRule.isChecked(gameState.getTurn(), gameState, new int[]{toRow, i})){
                            gameState.removePiece(validToY,i);
                            notCheck = false;
                            break;
                        }
                        gameState.removePiece(validToY,i);
                    }
                    if(notCheck){
                        gameState.createBoardMovement(toRow,rookX, toRow, nextKing);
                        gameState.movePiece(toRow,rookX, toRow, nextKing);
                    }
                    return notCheck;
                }
            }
        }
        return false;
    }
}
