import java.util.ArrayList;
import java.util.List;

public class UnionFind {

    private int[] parent;
    private int verticesSize;

    /* Creates a UnionFind data structure holding n vertices. Initially, all
       vertices are in disjoint sets. */
    public UnionFind(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException(String.format("invalid vertices size: %s", n));
        }
        verticesSize = n;
        parent = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = -1;
        }
    }

    /* Throws an exception if v1 is not a valid index. */
    private void validate(int vertex) {
        if (vertex < 0 || vertex > verticesSize - 1) {
            throw new IllegalArgumentException(String.format("invalid vertex: %s", vertex));
        }
    }

    /* Returns the size of the set v1 belongs to. */
    public int sizeOf(int v1) {
        validate(v1);
        int p = parent[v1];
        if (p >= 0) {
            p = parent[p];
        }
        return -p;
    }

    /* Returns the parent of v1. If v1 is the root of a tree, returns the
       negative size of the tree for which v1 is the root. */
    public int parent(int v1) {
        validate(v1);
        return parent[v1];
    }

    /* Returns true if nodes v1 and v2 are connected. */
    public boolean connected(int v1, int v2) {
        validate(v1);
        validate(v2);
        if (v1 == v2) {
            return true;
        }
        int root1 = find(v1);
        int root2 = find(v2);
        return root1 == root2;
    }

    /* Connects two elements v1 and v2 together. v1 and v2 can be any valid
       elements, and a union-by-size heuristic is used. If the sizes of the sets
       are equal, tie break by connecting v1's root to v2's root. Unioning a
       vertex with itself or vertices that are already connected should not
       change the sets but may alter the internal structure of the data. */
    public void union(int v1, int v2) {
        validate(v1);
        validate(v2);
        int root1 = find(v1);
        int root2 = find(v2);
        if (!this.connected(v1, v2)) {
            int size1 = sizeOf(v1);
            int size2 = sizeOf(v2);
            if (size1 <= size2) {
                parent[root1] = root2;
                parent[root2] = -(size1 + size2);
            } else {
                parent[root2] = root1;
                parent[root1] = -(size1 + size2);
            }
        }
    }

    /* Returns the root of the set V belongs to. Path-compression is employed
       allowing for fast search-time. */
    public int find(int vertex) {
        validate(vertex);
        List<Integer> seenList = new ArrayList<>();
        int p = parent[vertex];

        // vertex is the only one in the set
        if (p < 0) {
            return vertex;
        }
        seenList.add(vertex);
        if (parent[p] >= 0) {
            seenList.add(p);
            p = parent[p];
        }

        // path compression
        for (int seen : seenList) {
            parent[seen] = p;
        }

        return p;
    }

}
