package binary_search;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Boj1539 {
    private static List<Integer> list = new ArrayList<>();
    private static long[] height;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        long sum = 0;

        height = new long[N];
        for(int i = 0; i < N; i++){
            int node = Integer.parseInt(br.readLine());
            sum += getHeight(node);
        }

        System.out.println(sum);
    }

    private static int binarySearch(int start, int end, int target){
        while(start < end){
            int mid = (start + end) / 2;

            if(list.get(mid) < target) start = mid + 1;
            else end = mid;
        }

        return end;
    }

    private static long getHeight(int node){
        int size = list.size();
        int index = binarySearch(0, size, node);

        long left = 0, right = 0;

        if(index > 0) left = height[list.get(index - 1)];
        if(index < size) right = height[list.get(index)];

        height[node] = Math.max(left, right) + 1;
        list.add(index, node);

        return height[node];
    }
}
