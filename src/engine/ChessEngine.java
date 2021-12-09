package engine;

import chess.ChessController;
import chess.ChessView;
import chess.PieceType;
import chess.PlayerColor;

public class ChessEngine implements ChessController {
    private ChessView view;

    @Override
    public void start(ChessView view) {
        this.view = view;
        view.putPiece(PieceType.KING, PlayerColor.BLACK, 4,7);
        view.startView();
    }

    @Override
    public boolean move(int fromX, int fromY, int toX, int toY) {
        return false;
    }

    @Override
    public void newGame() {
    }
}
