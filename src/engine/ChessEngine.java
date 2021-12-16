package engine;

import chess.ChessController;
import chess.ChessView;
import chess.PieceType;
import chess.PlayerColor;
import engine.piece.*;

public class ChessEngine implements ChessController {
    private ChessView view;
    final int boardSize = 8;
    Piece[][] board = new Piece[boardSize][boardSize];
    @Override
    public void start(ChessView view) {
        this.view = view;
        view.startView();
    }

    @Override
    public boolean move(int fromX, int fromY, int toX, int toY) {
        return false;
    }
    
    @Override
    public void newGame() {
        int position = 0;
    
        // Pour les Tours
        //Blanches
        board[0][position] = new Rook(PlayerColor.WHITE);
        view.putPiece(PieceType.ROOK,PlayerColor.WHITE,position,0);
        board[0][boardSize -1-position] = new Rook(PlayerColor.WHITE);
        view.putPiece(PieceType.ROOK,PlayerColor.WHITE, boardSize -1 - position,0);
        //Noires
        board[boardSize -1][position] = new Rook(PlayerColor.BLACK);
        view.putPiece(PieceType.ROOK,PlayerColor.BLACK,position, boardSize -1);
        board[boardSize -1][boardSize -1-position] = new Rook(PlayerColor.BLACK);
        view.putPiece(PieceType.ROOK,PlayerColor.BLACK, boardSize -1-position, boardSize -1);
    
        position++;
        // Pour les Cavaliers
        //Blanches
        board[0][position] = new Knight(PlayerColor.WHITE);
        view.putPiece(PieceType.KNIGHT,PlayerColor.WHITE,position,0);
        board[0][boardSize -1-position] = new Knight(PlayerColor.WHITE);
        view.putPiece(PieceType.KNIGHT,PlayerColor.WHITE, boardSize -1 - position,0);
        //Noires
        board[boardSize -1][position] = new Knight(PlayerColor.BLACK);
        view.putPiece(PieceType.KNIGHT,PlayerColor.BLACK,position, boardSize -1);
        board[boardSize -1][boardSize -1-position] = new Knight(PlayerColor.BLACK);
        view.putPiece(PieceType.KNIGHT,PlayerColor.BLACK, boardSize -1 - position, boardSize -1);
    
        position++;
        // Pour les Fous
        //Blanches
        board[0][position] = new Bishop(PlayerColor.WHITE);
        view.putPiece(PieceType.BISHOP,PlayerColor.WHITE,position,0);
        board[0][boardSize -1-position] = new Bishop(PlayerColor.WHITE);
        view.putPiece(PieceType.BISHOP,PlayerColor.WHITE, boardSize -1 - position,0);
        //Noires
        board[0][position] = new Bishop(PlayerColor.BLACK);
        view.putPiece(PieceType.BISHOP,PlayerColor.BLACK,position, boardSize -1);
        board[0][boardSize -1-position] = new Bishop(PlayerColor.BLACK);
        view.putPiece(PieceType.BISHOP,PlayerColor.BLACK, boardSize -1 - position, boardSize -1);
    
        position++;
        // Pour les Spéciales
        //Blanches
        board[0][position] = new Queen(PlayerColor.WHITE);
        view.putPiece(PieceType.QUEEN,PlayerColor.WHITE,position,0);
        board[0][boardSize -1-position] = new King(PlayerColor.WHITE);
        view.putPiece(PieceType.KING,PlayerColor.WHITE, boardSize -1-position,0);
        //Noires
        board[0][position] = new Queen(PlayerColor.BLACK);
        view.putPiece(PieceType.QUEEN,PlayerColor.BLACK,position, boardSize -1);
        board[0][boardSize -1-position] = new King(PlayerColor.BLACK);
        view.putPiece(PieceType.KING,PlayerColor.BLACK, boardSize -1-position, boardSize -1);
        
        for (int i = 0; i < boardSize; ++i){
            // Pour les pions blancs
            board[1][i] = new Pawn(PlayerColor.WHITE);
            view.putPiece(PieceType.PAWN,PlayerColor.WHITE,i,1);
            
            // Pour les pions noirs
            board[boardSize -2][i] = new Pawn(PlayerColor.BLACK);
            view.putPiece(PieceType.PAWN,PlayerColor.BLACK,i, boardSize -2);
        }
        
    }
}
