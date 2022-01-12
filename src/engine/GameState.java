package engine;

import chess.PieceType;
import chess.PlayerColor;
import engine.piece.Piece;

public class GameState {
    private final int boardLength;

    private Piece[][] board;
    private Piece[][] previousBoard;
    private PlayerColor turn;
    private PlayerColor nextTurn;

    private final int[][] kingCoords;
    private int nbTurns;

    public GameState(Piece[][] board, int boardLength, PlayerColor turn) {
        this.boardLength = boardLength;
        this.board = board;
        previousBoard = deepCopyBoard(board);
        this.turn = turn;
        nextTurn = turn == PlayerColor.WHITE ? PlayerColor.BLACK : PlayerColor.WHITE;
        kingCoords = new int[2][];
        kingCoords[0] = findKingCoords(turn);
        kingCoords[1] = findKingCoords(nextTurn);
    }

    public void switchTurn() {
        turn = turn == PlayerColor.WHITE ? PlayerColor.BLACK : PlayerColor.WHITE;
        nextTurn = turn == PlayerColor.WHITE ? PlayerColor.BLACK : PlayerColor.WHITE;

        nbTurns++;
    }

    public Piece[][] deepCopyBoard(Piece[][] oldBoard) {
        Piece[][] newBoard = new Piece[boardLength][boardLength];
        for (int i = 0; i < boardLength; ++i) {
            for (int j = 0; j < boardLength; ++j) {
                if (oldBoard[i][j] != null)
                    newBoard[i][j] = oldBoard[i][j].clone();
            }
        }
        return newBoard;
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
        board[row][column] = piece;
    }

    public Piece[][] getBoard() {
        return board;
    }

    public void setBoard(Piece[][] board) {
        this.board = board;
    }
    
    public void revertBoard(){
        setBoard(deepCopyBoard(previousBoard));
    }
    
    public int getBoardLength() {
        return boardLength;
    }

    public Piece[][] getPreviousBoard() {
        return previousBoard;
    }

    public void setPreviousBoard(Piece[][] previousBoard) {
        this.previousBoard = previousBoard;
    }

    public PlayerColor getTurn() {
        return turn;
    }

    public PlayerColor getNextTurn() {
        return nextTurn;
    }

    public int getNbTurns() {
        return nbTurns;
    }
}
