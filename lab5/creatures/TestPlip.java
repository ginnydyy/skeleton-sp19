package creatures;

import huglife.*;
import org.junit.Test;

import java.awt.*;
import java.util.HashMap;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * Tests the plip class
 *
 * @authr FIXME
 */

public class TestPlip {

    @Test
    public void testBasics() {
        Plip p = new Plip(2);
        assertEquals(2, p.energy(), 0.01);
        assertEquals(new Color(99, 255, 76), p.color());
        p.move();
        assertEquals(1.85, p.energy(), 0.01);
        p.move();
        assertEquals(1.70, p.energy(), 0.01);
        p.stay();
        assertEquals(1.90, p.energy(), 0.01);
        p.stay();
        assertEquals(2.00, p.energy(), 0.01);
    }

    @Test
    public void testReplicate() {
        // TODO
        Plip origin = new Plip(2);
        Plip clone = origin.replicate();
        assertEquals(1.0, origin.energy(), 0);
        assertEquals(1.0, clone.energy(), 0);
        assertNotEquals(origin, clone);

        origin.move();
        Plip clone2 = origin.replicate();
        assertEquals(0.425, origin.energy(), 0);
        assertEquals(0.425, clone2.energy(), 0);
        assertEquals(1.0, clone.energy(), 0);
        assertNotEquals(origin, clone2);
    }

    @Test
    public void testChoose() {

        // No empty adjacent spaces; stay.
        Plip p = new Plip(1.2);
        HashMap<Direction, Occupant> surrounded = new HashMap<Direction, Occupant>();
        surrounded.put(Direction.TOP, new Impassible());
        surrounded.put(Direction.BOTTOM, new Impassible());
        surrounded.put(Direction.LEFT, new Impassible());
        surrounded.put(Direction.RIGHT, new Impassible());

        Action actual = p.chooseAction(surrounded);
        Action expected = new Action(Action.ActionType.STAY);

        assertEquals(expected, actual);


        // Energy >= 1; replicate towards an empty space.
        p = new Plip(1.2);
        HashMap<Direction, Occupant> topEmpty = new HashMap<Direction, Occupant>();
        topEmpty.put(Direction.TOP, new Empty());
        topEmpty.put(Direction.BOTTOM, new Impassible());
        topEmpty.put(Direction.LEFT, new Impassible());
        topEmpty.put(Direction.RIGHT, new Impassible());

        actual = p.chooseAction(topEmpty);
        expected = new Action(Action.ActionType.REPLICATE, Direction.TOP);

        assertEquals(expected, actual);


        // Energy >= 1; replicate towards an empty space.
        p = new Plip(1.2);
        HashMap<Direction, Occupant> allEmpty = new HashMap<Direction, Occupant>();
        allEmpty.put(Direction.TOP, new Empty());
        allEmpty.put(Direction.BOTTOM, new Empty());
        allEmpty.put(Direction.LEFT, new Empty());
        allEmpty.put(Direction.RIGHT, new Empty());

        actual = p.chooseAction(allEmpty);
        Action unexpected = new Action(Action.ActionType.STAY);

        assertNotEquals(unexpected, actual);


        // Energy < 1; stay.
        p = new Plip(.99);

        actual = p.chooseAction(allEmpty);
        expected = new Action(Action.ActionType.STAY);

        assertEquals(expected, actual);


        // Energy < 1; stay.
        p = new Plip(.99);

        actual = p.chooseAction(topEmpty);
        expected = new Action(Action.ActionType.STAY);

        assertEquals(expected, actual);


        // Energy < 1, Cloruses are seen; 50% probability to move
        p = new Plip(.99);
        HashMap<Direction, Occupant> oneClorus = new HashMap<Direction, Occupant>();
        oneClorus.put(Direction.TOP, new Clorus(8.8));
        oneClorus.put(Direction.BOTTOM, new Clorus(8.8));
        oneClorus.put(Direction.LEFT, new Clorus(8.8));
        oneClorus.put(Direction.RIGHT, new Empty());

        actual = p.chooseAction(oneClorus);
        unexpected = new Action(Action.ActionType.REPLICATE, Direction.RIGHT);

        assertNotEquals(unexpected, actual);
        assertNotEquals(Action.ActionType.ATTACK, actual.type);

        // Energy > 1, Cloruses are seen; replicate towards an empty space.
        p = new Plip(1.1);

        actual = p.chooseAction(oneClorus);
        expected = new Action(Action.ActionType.REPLICATE, Direction.RIGHT);

        assertEquals(expected, actual);
    }
}
