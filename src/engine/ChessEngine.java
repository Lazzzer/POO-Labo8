package engine;

import chess.ChessController;
import chess.ChessView;
import chess.PlayerColor;
import engine.piece.*;
import engine.rule.PromotionRule;

public class ChessEngine implements ChessController {
    private ChessView view;

    final int boardSize = 8;
    Piece[][] board;
    PlayerColor turn;

    private void populateBoard() {
        int position = 0;

        // Tours
        board[0][position] = new Rook(PlayerColor.WHITE);
        board[0][boardSize - 1 - position] = new Rook(PlayerColor.WHITE);
        board[boardSize - 1][position] = new Rook(PlayerColor.BLACK);
        board[boardSize - 1][boardSize - 1 - position] = new Rook(PlayerColor.BLACK);

        position++;
        // Cavaliers
        board[0][position] = new Knight(PlayerColor.WHITE);
        board[0][boardSize - 1 - position] = new Knight(PlayerColor.WHITE);
        board[boardSize - 1][position] = new Knight(PlayerColor.BLACK);
        board[boardSize - 1][boardSize - 1 - position] = new Knight(PlayerColor.BLACK);

        position++;
        // Fous
        board[0][position] = new Bishop(PlayerColor.WHITE);
        board[0][boardSize - 1 - position] = new Bishop(PlayerColor.WHITE);
        board[boardSize - 1][position] = new Bishop(PlayerColor.BLACK);
        board[boardSize - 1][boardSize - 1 - position] = new Bishop(PlayerColor.BLACK);

        position++;
        // Reines et Rois
        board[0][position] = new Queen(PlayerColor.WHITE);
        board[0][boardSize - 1 - position] = new King(PlayerColor.WHITE);
        board[boardSize - 1][position] = new Queen(PlayerColor.BLACK);
        board[boardSize - 1][boardSize - 1 - position] = new King(PlayerColor.BLACK);

        // Pions
        for (int i = 0; i < boardSize; ++i) {
            board[1][i] = new Pawn(PlayerColor.WHITE);
            board[boardSize - 2][i] = new Pawn(PlayerColor.BLACK);
        }
    }

    private void drawBoard() {
        for (int i = 0; i < boardSize; ++i) {
            for (int j = 0; j < boardSize; ++j) {
                if (board[i][j] != null) {
                    view.putPiece(board[i][j].getPieceType(), board[i][j].getColor(), j, i);
                } else {
                    view.removePiece(j, i);
                }
            }
        }
    }

    private void switchTurn() {
        turn = turn == PlayerColor.WHITE ? PlayerColor.BLACK : PlayerColor.WHITE;
    }

    @Override
    public void start(ChessView view) {
        this.view = view;
        view.startView();
    }

    @Override
    public boolean move(int fromX, int fromY, int toX, int toY) {
        if (board[fromY][fromX] == null || board[fromY][fromX].getColor() != turn || (board[toY][toX] != null && board[fromY][fromX].getColor() == board[toY][toX].getColor())) {
            return false;
        } else if (board[fromY][fromX].move(board, fromX, fromY, toX, toY)) {
            board[toY][toX] = board[fromY][fromX];
            board[fromY][fromX] = null;

            if (board[toY][toX] instanceof Pawn) {
                if (PromotionRule.canPromote(turn, board))
                    board[toY][toX] = view.askUser("Pawn Promotion", "Question", new Knight(board[toY][toX].getColor()),
                            new Queen(board[toY][toX].getColor()), new Bishop(board[toY][toX].getColor()));
            }

            drawBoard();
            switchTurn();
            return true;
        }
        return false;
    }

    @Override
    public void newGame() {
        board = new Piece[boardSize][boardSize];
        // populateBoard();
        board[boardSize - 2][0] = new Pawn(PlayerColor.WHITE);
        drawBoard();
        turn = PlayerColor.WHITE;
    }
}
