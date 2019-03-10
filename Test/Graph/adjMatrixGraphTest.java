package Graph;

import org.junit.Test;

import static org.junit.Assert.*;

public class adjMatrixGraphTest {

    private char[] ver = {'a', 'b', 'c'};
    private int numOfPoint = 3, noEdgeFlag = 100;
    private adjMatrixGraph graph = new adjMatrixGraph(numOfPoint, ver, noEdgeFlag);

    @Test
    public void insert() {
        char x = 'a', y = 'b';
        int weight = 3;
        graph.insert(x, y, weight);
        assertEquals(graph.edge[graph.find(x)][graph.find(y)], weight);
        assertEquals(graph.edge[graph.find(y)][graph.find(x)], weight);
    }

    @Test
    public void remove() {
        char x = 'a', y = 'b';
        int weight = 3;
        graph.insert(x, y, weight);
        assertEquals(graph.edge[graph.find(x)][graph.find(y)], weight);
        assertEquals(graph.edge[graph.find(y)][graph.find(x)], weight);
        graph.remove(x, y);
        assertEquals(graph.edge[graph.find(x)][graph.find(y)], noEdgeFlag);
        assertEquals(graph.edge[graph.find(y)][graph.find(x)], noEdgeFlag);
    }

    @Test
    public void exist() {
        char x = 'a', y = 'b';
        int weight = 3;
        assertFalse(graph.exist(x, y));
        graph.insert(x, y, weight);
        assertTrue(graph.exist(x, y));
    }
}