package creatures;

import huglife.*;

import java.awt.*;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Map;

public class Clorus extends Creature {

    final private static double ENERGY_MOVE = -0.03;
    final private static double ENERGY_STAY = -0.01;

    /**
     * Creates a Clorus with the energy e.
     *
     * @param e initial energy
     */
    public Clorus(double e) {
        super("clorus");
        energy = e;
    }

    @Override
    public void move() {
        energy += ENERGY_MOVE;
    }

    @Override
    public void attack(Creature c) {
        energy += c.energy();
    }

    @Override
    public Clorus replicate() {
        energy /= 2;
        return new Clorus(energy);
    }

    @Override
    public void stay() {
        energy += ENERGY_STAY;
    }

    /**
     * If there are no empty squares, the Clorus will STAY
     * (even if there are Plips nearby they could attack since plip squares do not count as empty squares).
     * Otherwise, if any Plips are seen, the Clorus will ATTACK one of them randomly.
     * Otherwise, if the Clorus has energy greater than or equal to one, it will REPLICATE to a random empty square.
     * Otherwise, the Clorus will MOVE to a random empty square.
     *
     * @param neighbors neighbors
     * @return the chosen Action
     */
    @Override
    public Action chooseAction(Map<Direction, Occupant> neighbors) {
        Deque<Direction> emptyNeighbors = new ArrayDeque<>();
        boolean anyPlip = false;
        Deque<Direction> plips = new ArrayDeque<>();

        for (Map.Entry<Direction, Occupant> entry : neighbors.entrySet()) {
            if ("empty".equals(entry.getValue().name())) {
                emptyNeighbors.addFirst(entry.getKey());
            } else if ("plip".equals(entry.getValue().name())) {
                anyPlip = true;
                plips.addFirst(entry.getKey());
            }
        }

        // Rule 1
        if (emptyNeighbors.size() == 0) {
            return new Action(Action.ActionType.STAY);
        }

        // Rule 2
        if (anyPlip) {
            Direction direction = HugLifeUtils.randomEntry(plips);
            return new Action(Action.ActionType.ATTACK, direction);
        }

        // Rule 3
        if (energy >= 1d) {
            Direction direction = HugLifeUtils.randomEntry(emptyNeighbors);
            return new Action(Action.ActionType.REPLICATE, direction);
        }

        // Rule 4
        return new Action(Action.ActionType.STAY);
    }

    @Override
    public Color color() {
        return color(34, 0, 231);
    }
}
