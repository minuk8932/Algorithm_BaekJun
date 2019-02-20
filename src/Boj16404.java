import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj16404 {
	private static int[] parent;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		parent = new int[N + 1];
		init();
		
		int[] profit = new int[N + 1];
		st = new StringTokenizer(br.readLine());
		
		for(int i = 1; i < N + 1; i++) {
			int head = Integer.parseInt(st.nextToken());
			if(head == -1) continue;
			
			merge(head, i);
		}
		
		// segment + disjoint set
		
		while(M-- > 0) {
			st = new StringTokenizer(br.readLine());
			int cmd = Integer.parseInt(st.nextToken());
			
			if(cmd == 1) {
				
			}
			else {
				
			}
		}
	}
	
	private static void init() {
		for(int i = 1; i < parent.length; i++) {
			parent[i] = -1;
		}
	}
	
	private static int find(int x) {
		if(parent[x] < 0) return x;
		else return parent[x] = find(parent[x]);
	}
	
	private static void merge(int x, int y) {
		x = find(x);
		y = find(y);
		
		if(x == y) return;
		
		if(parent[x] < parent[y]) {
			parent[x] += parent[y];
			parent[y] = x;
		}
		else {
			parent[y] += parent[x];
			parent[x] = y;
		}
	}
}
