package implementation;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 
 * 	@author exponential-e
 * 	백준 17294번: 귀여운 수
 * 
 * 	@see https://www.acmicpc.net/problem/17294/
 *
 */
public class Boj17294 {
	private static final String CUTE = "◝(⑅•ᴗ•⑅)◜..°♡ 뀌요미!!";
	private static final String NOT_CUTE = "흥칫뿡!! <(￣ ﹌ ￣)>";
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));		
		System.out.println(isCute(br.readLine().toCharArray()));
	}
	
	private static String isCute(char[] arr) {
		if(arr.length == 1) return CUTE;
		int diff = arr[1] - arr[0];
		
		for(int i = 1; i < arr.length - 1; i++) {
			if(arr[i + 1] - arr[i] == diff) continue;
			return NOT_CUTE;
		}
		
		return CUTE;
	}
}
