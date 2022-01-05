import chess.ChessController;
import chess.ChessView;
import chess.views.console.ConsoleView;
import chess.views.gui.GUIView;
import engine.ChessEngine;

public class Main {

    public static void main(String[] args) {
        if (args.length == 1) {
            ChessController controller = new ChessEngine();
            ChessView view;
            if (args[0].equals("gui")) {
                view = new GUIView(controller);
            } else if (args[0].equals("console")) {
                view = new ConsoleView(controller);
            } else {
                throw new RuntimeException("Argument is invalid");
            }
            controller.start(view);
        } else {
            throw new RuntimeException("The number of arguments is invalid");
        }
    }
}
