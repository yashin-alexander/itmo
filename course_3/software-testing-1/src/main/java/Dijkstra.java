import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.min;
import static java.util.Arrays.fill;

public class Dijkstra {
    private final static int INF = Integer.MAX_VALUE / 2;

    public List getPath(int start, int end, int[][] graph, int vNum) {
        boolean[] used = new boolean [vNum];
        int[] dist = new int [vNum];
        int[] previous = new int [vNum];
        fill(dist, INF);
        fill(previous, -1);
        dist[start] = 0;

        while (true) {
            int v = -1;
            for (int nv = 0; nv < vNum; nv++)
                if (!used[nv] && dist[nv] < INF && (v == -1 || dist[v] > dist[nv]))
                    v = nv;
            if (v == -1) break;
            used[v] = true;
            for (int nv = 0; nv < vNum; nv++)
                if (!used[nv] && graph[v][nv] < INF)
                    if (dist[nv] != (dist[nv] = min(dist[nv], dist[v] + graph[v][nv])))
                        previous[nv] = v;
        }

        List<Integer> result = new ArrayList<>();
        int v = end;
        result.add(end);
        while ((v = previous[v]) != -1)
            result.add(v);
        return result;
    }
}
