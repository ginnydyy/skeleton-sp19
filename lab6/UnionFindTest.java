import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;

public class UnionFindTest {

    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();

    @Test
    public void constructorThrowsException() {
        int n = -2;
        exceptionRule.expect(IllegalArgumentException.class);
        exceptionRule.expectMessage(String.format("invalid vertices size: %s", n));
        UnionFind uf = new UnionFind(n);
    }

    @Test
    public void sizeOf() {
        int n = 2;
        UnionFind uf = new UnionFind(n);
        assertTrue(uf.sizeOf(0) == 1);
        assertTrue(uf.sizeOf(1) == 1);

        uf.union(0, 1);
        assertTrue(uf.sizeOf(0) == 2);
        assertTrue(uf.sizeOf(1) == 2);
    }

    @Test
    public void parent() {
        int n = 3;
        UnionFind uf = new UnionFind(n);
        assertTrue(uf.parent(0) == -1);
        assertTrue(uf.parent(1) == -1);
        uf.union(0, 1);
        assertTrue(uf.parent(0) == 1);
        assertTrue(uf.parent(1) == -2);
        uf.union(0, 2);
        assertTrue(uf.parent(0) == 1);
        assertTrue(uf.parent(1) == -3);
        assertTrue(uf.parent(2) == 1);
    }

    @Test
    public void connected() {
        int n = 2;
        UnionFind uf = new UnionFind(n);
        assertFalse(uf.connected(0, 1));
        uf.union(0, 1);
        assertTrue(uf.connected(0, 1));
    }

    @Test
    public void union() {
        int n = 4;
        UnionFind uf = new UnionFind(n);
        uf.union(0, 1);
        uf.union(2, 3);
        uf.union(2, 0);
        assertTrue(uf.parent(0) == 1);
        assertTrue(uf.parent(1) == -4);
        assertTrue(uf.parent(2) == 3);
        assertTrue(uf.parent(3) == 1);
        uf.union(0, 2);
        assertTrue(uf.parent(0) == 1);
        assertTrue(uf.parent(1) == -4);
        assertTrue(uf.parent(2) == 1);
        assertTrue(uf.parent(3) == 1);
    }

    @Test
    public void find() {
        int n = 4;
        UnionFind uf = new UnionFind(n);
        uf.union(0, 1);
        uf.union(2, 3);
        uf.union(2, 0);
        assertTrue(uf.parent(0) == 1);
        assertTrue(uf.parent(1) == -4);
        assertTrue(uf.parent(2) == 3);
        assertTrue(uf.parent(3) == 1);
        assertTrue(uf.find(0) == 1);
        assertTrue(uf.find(1) == 1);
        assertTrue(uf.find(2) == 1);
        assertTrue(uf.find(3) == 1);
        assertTrue(uf.parent(0) == 1);
        assertTrue(uf.parent(1) == -4);
        assertTrue(uf.parent(2) == 1);
        assertTrue(uf.parent(3) == 1);
    }
}
