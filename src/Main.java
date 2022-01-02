import chess.ChessController;
import chess.ChessView;
import chess.views.console.ConsoleView;
import chess.views.gui.GUIView;
import engine.ChessEngine;

public class Main {

    public static void main(String[] args) {
        ChessController controller = new ChessEngine();
        ChessView view = new GUIView(controller);
        // ChessView view = new ConsoleView(controller);
        controller.start(view);
    }
}
