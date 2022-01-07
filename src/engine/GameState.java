package engine;

import chess.PlayerColor;
import engine.piece.*;

class GameState {
    private int size;

    private Piece[][] board;
    private Piece[][] previousBoard;
    private PlayerColor turn;
    private PlayerColor nextTurn;
    private int nbTurns;

    public GameState(Piece[][] board, int size, PlayerColor turn) {
        this.size = size;
        this.board = board;
        previousBoard = deepCopyBoard(board);
        this.turn = turn;
        nextTurn = turn == PlayerColor.WHITE ? PlayerColor.BLACK : PlayerColor.WHITE;
    }

    public void switchTurn() {
        turn = turn == PlayerColor.WHITE ? PlayerColor.BLACK : PlayerColor.WHITE;
        nextTurn = turn == PlayerColor.WHITE ? PlayerColor.BLACK : PlayerColor.WHITE;

        nbTurns++;
    }

    public Piece[][] deepCopyBoard(Piece[][] oldBoard) {
        Piece[][] newBoard = new Piece[size][size];
        for (int i = 0; i < size; ++i) {
            for (int j = 0; j < size; ++j) {
                if (oldBoard[i][j] != null)
                    newBoard[i][j] = oldBoard[i][j].clone();
            }
        }
        return newBoard;
    }

    public Piece getPiece(int row, int column) {
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

    public Piece[][] getPreviousBoard() {
        return previousBoard;
    }

    public void setPreviousBoard(Piece[][] previousBoard) {
        this.previousBoard = previousBoard;
    }

    public PlayerColor getTurn() {
        return turn;
    }

    public void setTurn(PlayerColor turn) {
        this.turn = turn;
    }

    public PlayerColor getNextTurn() {
        return nextTurn;
    }

    public void setNextTurn(PlayerColor nextTurn) {
        this.nextTurn = nextTurn;
    }

    public int getNbTurns() {
        return nbTurns;
    }

    public void setNbTurns(int nbTurns) {
        this.nbTurns = nbTurns;
    }
}
