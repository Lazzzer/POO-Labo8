package engine.rule;

import chess.PieceType;
import chess.PlayerColor;
import engine.GameState;
import engine.piece.Pawn;

/**
 * Classe représentant la prise en passant d'un pion
 * @author Alexandre Jaquier
 * @author Lazar Pavicevic
 */
public class EnPassantRule {

    private static final int WHITE_EN_PASSANT_LINE = 4;
    private static final int BLACK_EN_PASSANT_LINE = 3;

    private EnPassantRule() {}
    
    /**
     * Contrôle si une pièce sur la case de départ peut prendre en passant sur la case d'arrivée
     * @param gameState État du jeu
     * @param fromCol Colonne de départ
     * @param fromRow Ligne de départ
     * @param toCol Colonne d'arrivée
     * @param toRow Ligne d'arrivée
     * @return Vrai si une pièce sur la case de départ passée en paramètre peut prendre en passant
     * sur la case d'arrivée
     */
    public static boolean canTakeEnPassant(GameState gameState, int fromCol, int fromRow, int toCol, int toRow) {
        int validYCell = gameState.getPiece(fromRow, fromCol).getColor() == PlayerColor.WHITE ? WHITE_EN_PASSANT_LINE : BLACK_EN_PASSANT_LINE;
        int directionY = gameState.getPiece(fromRow, fromCol).getColor() == PlayerColor.WHITE ? 1 : -1;

        if (fromRow != validYCell || toCol == fromCol || gameState.getPiece(toRow, toCol) != null)
            return false;

        if (gameState.getPiece(toRow - directionY, toCol) != null && gameState.getPiece(toRow - directionY, toCol).getPieceType() == PieceType.PAWN
                && gameState.getNbTurns() - ((Pawn) gameState.getPiece(toRow - directionY, toCol)).getTurnEnPassant() == 1) {
            gameState.createBoardMovement(fromRow, toCol,toRow - directionY,toCol);
            gameState.movePiece(fromRow, toCol,toRow - directionY,toCol);
            return true;
        }
        return false;
    }
}
