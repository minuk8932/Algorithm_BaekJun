import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Boj1602 {
    private static int[][] dist;
    private static int[][] graph;
    private static int[] bothered;

    private static final String NEW_LINE = "\n";
    private static final int INF = 1_000_000_000;
    private static final int CIPHER = 1_000;

    private static class Node implements Comparable<Node>{
        int node;
        int cost;

        public Node(int node, int cost) {
            this.node = node;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node n) {
            return this.cost < n.cost ? -1: 1;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int Q = Integer.parseInt(st.nextToken());

        int[] harass = new int[N];
        graph = new int[N][N];
        dist = new int[N][N];
        bothered = new int[N];

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            harass[i] = Integer.parseInt(st.nextToken());

            bothered[i] = harass[i] * CIPHER + i;
            Arrays.fill(graph[i], INF);
            graph[i][i] = 0;

            Arrays.fill(dist[i], INF);
        }

        Arrays.sort(bothered);

        while(M-- > 0) {
            st = new StringTokenizer(br.readLine());
            int node1 = Integer.parseInt(st.nextToken()) - 1;
            int node2 = Integer.parseInt(st.nextToken()) - 1;
            int cost = Integer.parseInt(st.nextToken());

            graph[node1][node2] = Math.min(graph[node1][node2], cost);
            graph[node2][node1] = graph[node1][node2];
        }

        for(int v = 0; v < N; v++) {
            floydWashall(N, v);
        }

        StringBuilder sb = new StringBuilder();

        while(Q-- > 0) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken()) - 1;
            int end = Integer.parseInt(st.nextToken()) - 1;

            sb.append(dist[start][end] == INF ? -1: dist[start][end]).append(NEW_LINE);
        }

        System.out.println(sb.toString());
    }

    private static void floydWashall(int n, int via) {
        via = bothered[via];

        for(int s = 0; s < n; s++){
            for(int e = 0; e < n; e++){
                graph[s][e] = Math.min(graph[s][e], graph[s][via] + graph[via][e]);

                int max = Math.max(bothered[s], bothered[e]);
                dist[s][e] =  Math.min(dist[s][e], graph[s][e] + Math.max(max, bothered[via]));
            }
        }
    }
}
