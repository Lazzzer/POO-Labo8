package engine.move;

import java.util.Map;

public abstract class BlockableMove extends Move {
    public abstract boolean move(Map<Integer, Integer> startPos, Map<Integer, Integer> endPos);
}
