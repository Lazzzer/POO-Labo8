package engine;

import chess.ChessView;
import chess.PieceType;
import chess.PlayerColor;
import engine.piece.Piece;

import java.util.ArrayList;
import java.util.Objects;

/**
 * Classe représentant l'état d'une partie d'échecs
 * @author Alexandre Jaquier
 * @author Lazar Pavicevic
 */
public class GameState {

    /**
     * Classe interne permettant de créer un mouvement effectué sur l'échiquier
     */
    private class BoardMovement {
        private final Piece piece;
        private final int fromY, fromX, toY, toX;

        /**
         * Constructeur de base
         * @param piece Pièce effectuant un mouvement
         * @param fromY Ligne de départ
         * @param fromX Colonne de départ
         * @param toY   Ligne d'arrivée
         * @param toX   Colonne d'arrivée
         */
        private BoardMovement(Piece piece, int fromY, int fromX, int toY, int toX) {
            this.piece = piece;
            this.fromY = fromY;
            this.fromX = fromX;
            this.toY = toY;
            this.toX = toX;
        }

        /**
         * Rollback le mouvement actuel
         */
        private void revert() {
            removePiece(toY, toX);
            setPiece(piece, fromY, fromX);
        }
    }

    private final ChessView view;
    private final ArrayList<BoardMovement> moveHistory;
    private final int boardLength;
    private final Piece[][] board;
    private final int[][] kingCoords;
    private PlayerColor turn;
    private PlayerColor nextTurn;
    private boolean isChecked;
    private boolean endGame;
    private int nbTurns;

    /**
     * Constructeur de base
     * @param board Le tableau contenant les coordonnées des pièces
     * @param turn La couleur du joueur initiant le premier tour
     * @param view L'interface affichant l'échiquier
     */
    public GameState(Piece[][] board, PlayerColor turn, ChessView view) {
        this.board = board;
        this.boardLength = board.length;
        this.view = view;
        this.turn = turn;
        isChecked = false;
        endGame = false;
        nextTurn = turn == PlayerColor.WHITE ? PlayerColor.BLACK : PlayerColor.WHITE;
        moveHistory = new ArrayList<>();
        kingCoords = new int[2][];
        kingCoords[0] = findKingCoords(turn);
        kingCoords[1] = findKingCoords(nextTurn);
    }

    /**
     * Récupère et renvoie une pièce de l'échiquier
     * @param row Ligne de la pièce
     * @param column Colonne de la pièce
     * @throws IndexOutOfBoundsException est levée si les paramètres ne sont pas valides
     * @return La pièce voulue
     */
    public Piece getPiece(int row, int column) {
        Objects.checkIndex(row, boardLength);
        Objects.checkIndex(column, boardLength);
        return board[row][column];
    }

    /**
     * Pose une pièce à un endroit sur l'échiquier et sur l'interface
     * @param piece Pièce à placer
     * @param row Ligne de la pièce
     * @param column Colonne de la pièce
     * @throws IndexOutOfBoundsException est levée si les paramètres ne sont pas valides
     */
    public void setPiece(Piece piece, int row, int column) {
        Objects.checkIndex(row, boardLength);
        Objects.checkIndex(column, boardLength);
        board[row][column] = piece;
        if (piece != null) {
            if (piece.getPieceType() == PieceType.KING) {
                setKingCoords(piece.getColor(), row, column);
            }
            view.putPiece(piece.getPieceType(), piece.getColor(), column, row);
        } else {
            view.removePiece(column, row);
        }
    }

    /**
     * Enlève une pièce du plateau interne
     * @param row Ligne de la pièce
     * @param column Colonne de la pièce
     */
    public void removePiece(int row, int column) {
        setPiece(null, row, column);
    }

    /**
     * Déplace une pièce dans le plateau interne
     * @param fromRow Ligne de départ
     * @param fromCol Colonne de départ
     * @param toRow Ligne d'arrivée
     * @param toCol Colonne d'arrivée
     */
    public void movePiece(int fromRow, int fromCol, int toRow, int toCol) {
        if (getPiece(toRow, toCol) != null) {
            createBoardMovement(toRow, toCol, toRow, toCol);
        }
        setPiece(getPiece(fromRow, fromCol), toRow, toCol);
        removePiece(fromRow, fromCol);
    }

    /**
     * Nettoie l'historique des mouvements
     */
    public void removeMovedPieces() {
        moveHistory.clear();
    }

    /**
     * Stock un mouvement effectué sur le plateau
     * @param fromRow Ligne de départ
     * @param fromCol Colonne de départ
     * @param toRow Ligne d'arrivée
     * @param toCol Colonne d'arrivée
     */
    public void createBoardMovement(int fromRow, int fromCol, int toRow, int toCol) {
        moveHistory.add(new BoardMovement(getPiece(fromRow, fromCol).clone(), fromRow, fromCol, toRow, toCol));
    }

    /**
     * Getter sur boardLength
     * @return La longueur de l'échiquier
     */
    public int getBoardLength() {
        return boardLength;
    }

    /**
     * Getter sur turn
     * @return La couleur du joueur actuel
     */
    public PlayerColor getTurn() {
        return turn;
    }

    /**
     * Remet le plateau à son état avant tous les mouvements efféctués
     */
    public void revertMoves() {
        for (BoardMovement m : moveHistory) {
            m.revert();
        }
    }

    /**
     * Retourne les coordonnées d'un roi
     * @param color Couleur du roi
     * @return Un tableau d'entier contenant les coordonnées d'un roi
     */
    public int[] getKingCoords(PlayerColor color) {
        return color == PlayerColor.WHITE ? kingCoords[0] : kingCoords[1];
    }

    /**
     * Getter sur nextTurn
     * @return La couleur du joueur du prochain coup
     */
    PlayerColor getNextTurn() {
        return nextTurn;
    }

    /**
     * Getter sur isChecked
     * @return Un booléen spécifiant s'il y a un échec
     */
    public boolean isChecked() {
        return isChecked;
    }

    /**
     * Getter sur nbTurns
     * @return Le nombre de tours joués
     */
    public int getNbTurns() {
        return nbTurns;
    }

    /**
     * Change la couleur des pièces pouvant être jouées et incrémente le nombre de tours
     */
    void switchTurn() {
        nextTurn = turn;
        turn = turn == PlayerColor.WHITE ? PlayerColor.BLACK : PlayerColor.WHITE;
        nbTurns++;
    }

    /**
     * Setter sur isChecked
     * @param checked Valeur spécifiant s'il y a un échec
     */
    void setChecked(boolean checked) {
        isChecked = checked;
    }

    /**
     * Getter sur endGame
     * @return Un booléen spécifiant si la partie est finie
     */
    boolean isEndGame() {
        return endGame;
    }

    /**
     * Setter sur endGame
     * @param endGame Valeur spécifiant si la partie est finie
     */
    void setEndGame(boolean endGame) {
        this.endGame = endGame;
    }

    /**
     * Modifie les coordonnées d'un des rois
     * @param color  Couleur du roi
     * @param row    Nouvelle ligne
     * @param column Nouvelle colonne
     */
    private void setKingCoords(PlayerColor color, int row, int column) {
        if (color == PlayerColor.WHITE) {
            this.kingCoords[0] = new int[]{row, column};
        } else {
            this.kingCoords[1] = new int[]{row, column};
        }
    }

    /**
     * Recherche les coordonnées d'un roi d'une certaine couleur et les retourne
     * @param color Couleur du roi
     * @throws RuntimeException Est levée s'il n'y a pas de roi sur l'échiquier
     * @return Un tableau d'entier contenant les coordonnées d'un roi
     */
    private int[] findKingCoords(PlayerColor color) {
        for (int i = 0; i < boardLength; ++i) {
            for (int j = 0; j < boardLength; ++j) {
                if (getPiece(i, j) != null && getPiece(i, j).getPieceType() == PieceType.KING && getPiece(i, j).getColor() == color)
                    return new int[]{i, j};
            }
        }
        throw new RuntimeException("Error: There must be kings on the chessboard.");
    }
}
