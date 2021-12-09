package engine.move;

import java.util.Map;

public class DiagonalMove extends BlockableMove{
    @Override
    public boolean move(Map<Integer, Integer> startPos, Map<Integer, Integer> endPos) {
        return false;
    }
}
