import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class DijkstraTest {

    private Dijkstra dijkstra;
    private final static int INF = Integer.MAX_VALUE / 2;
    private final static int[][] graph8 =
            {
                    { INF, INF, 4,   INF, 4,   INF, INF, INF },
                    { INF, INF, 4,   INF, INF, INF, 3,   INF },
                    { 4,   4,   INF, INF, INF, 4,   4,   INF },
                    { INF, INF, INF, INF, INF, 3,   INF, 3   },
                    { 4,   INF, INF, INF, INF, INF, INF, 8   },
                    { INF, INF, 4,   3,   INF, INF, INF, INF },
                    { INF, 3,   4,   INF, INF, INF, INF, 2   },
                    { INF, INF, INF, 3,   8,   INF, 2,   INF },
            };

    private final static int[][] graph18 =
            {
                    { INF, 1,   INF, INF, 5,   INF, INF, 6,   INF, INF, INF, INF, INF, INF, 9,   INF, INF, INF },
                    { 1,   INF, 7,   INF, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF },
                    { INF, 7,   INF, INF, INF, INF, 6,   INF, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF },
                    { INF, INF, INF, INF, INF, INF, 7,   INF, INF, INF, INF, INF, INF, INF, INF, INF, INF, 8   },
                    { 5,   INF, INF, INF, INF, 7,   INF, 7,   INF, INF, INF, INF, INF, INF, INF, INF, INF, INF },
                    { INF, INF, INF, INF, 7,   INF, INF, INF, 1,   INF, INF, INF, INF, INF, INF, INF, INF, INF },
                    { INF, INF, 6,   7,   INF, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF },
                    { 6,   INF, INF, INF, 7,   INF, INF, INF, 7,   INF, INF, 8  , INF, INF, INF, INF, INF, INF },
                    { INF, INF, INF, INF, INF, 1,   INF, 7,   INF, INF, INF, 6,   INF, INF, INF, INF, INF, INF },
                    { INF, INF, INF, INF, INF, INF, INF, INF, INF, INF, 4,   INF, INF, INF, INF, INF, INF, INF },
                    { INF, INF, INF, INF, INF, INF, INF, INF, INF, 4,   INF, INF, INF, 1,   INF, INF, INF, INF },
                    { INF, INF, INF, INF, INF, INF, INF, 8,   6,   INF, INF, INF, INF, INF, INF, INF, INF, INF },
                    { INF, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF, 6,   INF, 7,   7  , INF },
                    { INF, INF, INF, INF, INF, INF, INF, INF, INF, INF, 1,   INF, 6,   INF, INF, INF, INF, 5   },
                    { 9,   INF, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF, 7,   INF, INF },
                    { INF, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF, 7,   INF, 7,   INF, 3,   3   },
                    { INF, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF, 7,   INF, INF, 3,   INF, 6   },
                    { INF, INF, INF, 8,   INF, INF, INF, INF, INF, INF, INF, INF, INF, 5,   INF, 3,   6,   INF }
            };

    @Before
    public void init() {
        dijkstra = new Dijkstra();
    }

    @Test
    public void testFrom1To2() {
        List<Integer> expected = new ArrayList<>();
        expected.add(2);
        expected.add(1);
        assertEquals(expected, dijkstra.getPath(1,2, graph8, 8));
    }

    @Test
    public void testFrom5To4() {
        List<Integer> expexted = new ArrayList<>();
        expexted.add(7);
        expexted.add(3);
        expexted.add(5);
        assertEquals(expexted, dijkstra.getPath(5,7, graph8, 8));
    }

    @Test
    public void testFrom1To4() {
        List<Integer> expected = new ArrayList<>();
        expected.add(4);
        expected.add(0);
        expected.add(2);
        expected.add(1);
        assertEquals(expected, dijkstra.getPath(1,4, graph8, 8));
    }

    @Test
    public void testFrom4To3() {
        List<Integer> expected = new ArrayList<>();
        expected.add(3);
        expected.add(6);
        expected.add(2);
        expected.add(1);
        expected.add(0);
        expected.add(4);
        assertEquals(expected, dijkstra.getPath(4,3, graph18, 18));
    }

    @Test
    public void testFrom0To9() {
        List<Integer> expected = new ArrayList<>();
        expected.add(9);
        expected.add(10);
        expected.add(13);
        expected.add(17);
        expected.add(15);
        expected.add(14);
        expected.add(0);
        assertEquals(expected, dijkstra.getPath(0,9, graph18, 18));
    }
}
