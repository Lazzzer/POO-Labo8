package engine.piece;

import chess.PieceType;
import chess.PlayerColor;

public class Pawn extends SpecialPiece{

    private boolean takeableEnPassant;
    public Pawn(PlayerColor color) {
        super(PieceType.PAWN, color);
        takeableEnPassant = false;
    }

    // Gros gros refactor
    @Override
    public boolean move(Piece[][] gameState, int fromX, int fromY, int toX, int toY) {
        boolean isValid;

        // Pion bloqué s'il y a une pièce devant
        if (fromX == toX && gameState[toY][fromX] != null) {
            return false;
        }

        int nbCases = hasMoved ? 1 : 2;

        if (color == PlayerColor.WHITE) {
            // Avec nbCases, on sait si le pion peut avancer de 1 ou 2.
            isValid = toX == fromX && (toY - fromY <= nbCases && toY - fromY >= 1);

            // On s'assure que le pion souhaite aller que d'une case ici pour check le déplacement diagonal
            if (toY == fromY + 1) {
                // Si une des deux cases en diagonales sont occupées, le pion peut s'y placer
                if (gameState[toY][toX] != null)
                    isValid = true;
            }

        } else {
            isValid = toX == fromX && (toY - fromY >= -nbCases && toY - fromY <= -1);
            if (toY == fromY - 1) {
                if (gameState[toY][toX] != null)
                    isValid = true;
            }
        }

        if (isValid) {
            // A refactor pour pas faire l'affectation à chaque fois
            hasMoved = true;
            takeableEnPassant = nbCases == 2;
        }

        return isValid;
    }

    public Piece promote(Piece choice) {
        return null;
    }
}
