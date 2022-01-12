package engine;

import chess.ChessView;
import chess.PieceType;
import chess.PlayerColor;
import engine.piece.Piece;

public class GameState {
    private final int boardLength;

    private Piece[][] board;
    private Piece[] movedPieces;
    private PlayerColor turn;
    private PlayerColor nextTurn;
    private ChessView view;
    
    boolean isChecked;
    
    private final int[][] kingCoords;
    private int nbTurns;

    public GameState(Piece[][] board, int boardLength, PlayerColor turn,ChessView view) {
        this.boardLength = boardLength;
        this.board = board;
        this.turn = turn;
        nextTurn = turn == PlayerColor.WHITE ? PlayerColor.BLACK : PlayerColor.WHITE;
        this.view = view;
        movedPieces = new Piece[2];
        isChecked = false;
        kingCoords = new int[2][];
        kingCoords[0] = findKingCoords(turn);
        kingCoords[1] = findKingCoords(nextTurn);
    }

    public void switchTurn() {
        turn = turn == PlayerColor.WHITE ? PlayerColor.BLACK : PlayerColor.WHITE;
        nextTurn = turn == PlayerColor.WHITE ? PlayerColor.BLACK : PlayerColor.WHITE;

        nbTurns++;
    }

    public int[] getKingCoords(PlayerColor color) {
        return color == PlayerColor.WHITE ? kingCoords[0] : kingCoords[1];
    }

    public void setKingCoords(PlayerColor color, int row, int column) {
        if (color == PlayerColor.WHITE) {
            this.kingCoords[0] = new int[] { row, column };
        } else {
            this.kingCoords[1] = new int[] { row, column};
        }
    }

    public int[] findKingCoords(PlayerColor color) {
        for (int i = 0; i < boardLength; ++i) {
            for (int j = 0; j < boardLength; ++j) {
                if (getPiece(i, j) != null && getPiece(i, j).getPieceType() == PieceType.KING && getPiece(i, j).getColor() == color)
                    return new int[]{i, j};
            }
        }
        throw new RuntimeException();
    }
    
    public Piece getPiece(int row, int column) {
        if (row >= boardLength || column >= boardLength || row < 0 || column < 0)
            throw new ArrayIndexOutOfBoundsException("index is out of bound");
        return board[row][column];
    }
    
    public void setPiece(Piece piece, int row, int column) {
        if (row >= boardLength || column >= boardLength || row < 0 || column < 0)
            throw new ArrayIndexOutOfBoundsException("index is out of bound");
        board[row][column] = piece;
        if(piece != null){
            if(piece.getPieceType() == PieceType.KING){
                setKingCoords(piece.getColor(),row,column);
            }
            view.putPiece(piece.getPieceType(),turn,column,row);
        } else{
            view.removePiece(column,row);
        }
    }
    public void removePiece(int row, int column) {
        setPiece(null,row,column);
    }
    
    public void movePiece(int rowFrom,int columnFrom,int rowTo,int columnTo){
        if (getPiece(rowTo,columnTo) != null)
            movedPieces[1] = getPiece(rowTo,columnTo).clone();
        setPiece(getPiece(rowFrom,columnFrom), rowTo,columnTo);
        removePiece(rowFrom,columnFrom);
    }
    
    public void cloneMovingPiece(int row,int column){
        movedPieces[0] = getPiece(row,column).clone();
    }
    
    public void removedMovedPieces(){
        movedPieces[0] = null;
        movedPieces[1] = null;
    }
    
    public Piece[][] getBoard() {
        return board;
    }

    public void setBoard(Piece[][] board) {
        this.board = board;
    }
    
    public void revertMove(int rowFrom, int columnFrom, int rowTo, int columnTo){
        setPiece(movedPieces[0], rowFrom,columnFrom);
        removePiece(rowTo,columnTo);
        if(movedPieces[1] != null){
            setPiece(movedPieces[1],rowTo,columnTo);
        }
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
