package counter_clock_wise;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 16491번: 대피소
 *
 * @see https://www.acmicpc.net/problem/16491/
 *
 */
public class Boj16491 {
	private static final String NEW_LINE = "\n";
	private static long INF;
	
	private static ArrayList<Long> seq = new ArrayList<>();
	private static boolean[] isVisited;
	
	private static class Point {
		int x;
		int y;
		
		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		INF = (long) Math.pow(10, N - 1);
		
		Point[] robot = new Point[N];
		Point[] shelter = new Point[N];
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			robot[i] = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			shelter[i] = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}
		
		System.out.println(process(N, robot, shelter));
	}
	
	private static String process(int n, Point[] robo, Point[] shel) {
		StringBuilder sb = new StringBuilder();
		
		for(int i = 0; i < n; i++) {				// make sequence
			isVisited = new boolean[n];
			backTracking(n, i, 0, i);
		}

		int[] tmp = new int[n];

		for(long s: seq) {
			int loop = n;

			if(s < INF){
				loop--;
				tmp[n - 1] = 0;
			}
			
			for(int i = 0; i < loop; i++) {
				tmp[i] = (int) (s % 10);
				s /= 10;
			}

			if(judgement(n, robo, shel, tmp)) continue;		// two lines are intersection?

			for(int idx = 0; idx < tmp.length; idx++) {
				sb.append(tmp[idx] + 1).append(NEW_LINE);
			}
			break;
		}
		
		return sb.toString();
	}

	private static boolean judgement(int n, Point[] r, Point[] s, int[] perm) {
		for(int i = 0; i < n; i++) {
			for(int j = i + 1; j < n; j++) {
				if (isIntersect(r[i], s[perm[i]], r[j], s[perm[j]])) return true;
			}
		}

		return false;
	}
	
	private static void backTracking(int n, int current, int depth, long value) {
		if(depth == n - 1) {
			seq.add(value);
			return;
		}
		
		if(isVisited[current]) return;
		isVisited[current] = true;
		
		for(int next = 0; next < n; next++) {
			if(isVisited[next]) continue;
			
			backTracking(n, next, depth + 1, value * 10 + next);
			isVisited[next] = false;
		}
	}
	
	private static boolean isIntersect(Point a, Point b, Point c, Point d) {
		int ab = ccw(a, b, c) * ccw(a, b, d);
		int cd = ccw(c, d, a) * ccw(c, d, b);

		if (ab == 0 && cd == 0) {					// judgement lines status by relative position
			Point[] p = swap(a, b);
			a = p[0];
			b = p[1];

			p = swap(c, d);
			c = p[0];
			d = p[1];

			return compare(c, b) && compare(a, d);
		}

		return ab <= 0 && cd <= 0;
	}

	private static Point[] swap(Point p1, Point p2) {
		if (p1.x > p2.x) {
			Point tmp = p1;
			p1 = p2;
			p2 = tmp;
		}
		else {
			if(p1.x == p2.x) {
				if(p1.y > p2.y) {
					Point tmp = p1;
					p1 = p2;
					p2 = tmp;
				}
			}
		}

		return new Point[]{p1, p2};
	}

	private static boolean compare(Point p1, Point p2) {
		if(p1.x < p2.x) return true;
		else if(p1.x > p2.x) return false;
		else return p1.y <= p2.y;
	}
	
	private static int ccw(Point p1, Point p2, Point p3) {
		int cost = p1.x * p2.y + p2.x * p3.y + p3.x * p1.y;
		cost -= p1.y * p2.x + p2.y * p3.x + p3.y * p1.x;

		if(cost < 0) return -1;
		else if(cost > 0) return 1;
		else return 0;
	}
}
