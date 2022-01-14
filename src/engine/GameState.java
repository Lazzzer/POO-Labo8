package engine;

import chess.ChessView;
import chess.PieceType;
import chess.PlayerColor;
import engine.piece.Piece;

import java.util.ArrayList;
import java.util.Objects;

public class GameState {
    
    /**
     * Permet de créer un mouvement effectué sur le plateau
     */
    class BoardMovement {
        Piece piece;
        int fromY,fromX,toY,toX;
        BoardMovement(Piece piece,int fromY,int fromX,int toY,int toX){
            this.piece = piece;
            this.fromY = fromY;
            this.fromX = fromX;
            this.toY = toY;
            this.toX = toX;
        };
    }
    ArrayList<BoardMovement> moveHistory;
    private final int boardLength;
    private Piece[][] board;
    private PlayerColor turn;
    private PlayerColor nextTurn;
    private ChessView view;
    
    boolean isChecked;
    boolean endGame;
    
    private final int[][] kingCoords;
    private int nbTurns;

    public GameState(Piece[][] board, int boardLength, PlayerColor turn,ChessView view) {
        this.boardLength = boardLength;
        this.board = board;
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
     * Change la couleur de la personne pouvant jouer
     */
    public void switchTurn() {
        turn = turn == PlayerColor.WHITE ? PlayerColor.BLACK : PlayerColor.WHITE;
        nextTurn = turn == PlayerColor.WHITE ? PlayerColor.BLACK : PlayerColor.WHITE;
        nbTurns++;
    }
    
    /**
     * Retourne les coordonnées d'un des deux rois
     * @param color
     * @return
     */
    public int[] getKingCoords(PlayerColor color) {
        return color == PlayerColor.WHITE ? kingCoords[0] : kingCoords[1];
    }
    
    /**
     * Modifie les coordonnées d'un des rois stockés
     * @param color
     * @param row
     * @param column
     */
    public void setKingCoords(PlayerColor color, int row, int column) {
        if (color == PlayerColor.WHITE) {
            this.kingCoords[0] = new int[] { row, column };
        } else {
            this.kingCoords[1] = new int[] { row, column};
        }
    }
    
    /**
     * Recherche les coordonnées sur le plateau d'un roi d'une certaine couleur et les stock
     * @param color
     * @return
     */
    public int[] findKingCoords(PlayerColor color) {
        for (int i = 0; i < boardLength; ++i) {
            for (int j = 0; j < boardLength; ++j) {
                if (getPiece(i, j) != null && getPiece(i, j).getPieceType() == PieceType.KING && getPiece(i, j).getColor() == color)
                    return new int[]{i, j};
            }
        }
        throw new RuntimeException();
    }
    
    /**
     * Récupère et renvoi une pièce du plateau
     * @param row
     * @param column
     * @return Pièce voulue
     */
    public Piece getPiece(int row, int column) {
        Objects.checkIndex(row, boardLength);
        Objects.checkIndex(column, boardLength);
        return board[row][column];
    }
    
    /**
     * Pose une pièce à un endroit sur le plateau et sur l'interface
     * @param piece
     * @param row
     * @param column
     */
    public void setPiece(Piece piece, int row, int column) {
        Objects.checkIndex(row, boardLength);
        Objects.checkIndex(column, boardLength);
        board[row][column] = piece;
        if(piece != null){
            if(piece.getPieceType() == PieceType.KING){
                setKingCoords(piece.getColor(),row,column);
            }
            view.putPiece(piece.getPieceType(),piece.getColor(),column,row);
        } else{
            view.removePiece(column,row);
        }
    }
    
    /**
     * Enlève une pièce du plateau interne
     * @param row
     * @param column
     */
    public void removePiece(int row, int column) {
        setPiece(null,row,column);
    }
    
    /**
     * Déplace une pièce dans le plateau interne
     * @param rowFrom
     * @param columnFrom
     * @param rowTo
     * @param columnTo
     */
    public void movePiece(int rowFrom,int columnFrom,int rowTo,int columnTo){
        if(getPiece(rowTo,columnTo) != null){
            createBoardMovement(rowTo,columnTo,rowTo,columnTo);
        }
        setPiece(getPiece(rowFrom,columnFrom), rowTo,columnTo);
        removePiece(rowFrom,columnFrom);
    }
    
    /**
     * Stock un mouvement efféctué sur le plateau
     * @param rowFrom
     * @param columnFrom
     * @param rowTo
     * @param columnTo
     */
    public void createBoardMovement(int rowFrom, int columnFrom, int rowTo, int columnTo){
        moveHistory.add(new BoardMovement(getPiece(rowFrom,columnFrom).clone(),rowFrom,
                columnFrom, rowTo, columnTo));
    }
    
    /**
     * Remet le board à son état avant tous les mouvements efféctués
     */
    public void revertMoves(){
        for(BoardMovement m : moveHistory){
            removePiece(m.toY,m.toX);
            setPiece(m.piece,m.fromY,m.fromX);
        }
    }
    
    public void removedMovedPieces(){
        moveHistory.clear();
    }
    
    public Piece[][] getBoard() {
        return board;
    }

    public void setBoard(Piece[][] board) {
        this.board = board;
    }
    
    public int getBoardLength() {
        return boardLength;
    }
    public PlayerColor getTurn() {
        return turn;
    }

    public PlayerColor getNextTurn() {
        return nextTurn;
    }
    
    public boolean getIsChecked() {
        return isChecked;
    }
    
    public int getNbTurns() {
        return nbTurns;
    }
}
