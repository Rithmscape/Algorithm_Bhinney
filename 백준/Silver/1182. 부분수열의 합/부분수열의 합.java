import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	public static void main(String[] args) throws IOException {
		solution();
	}

	private static int N, S;
	private static int[] arr;
	private static int ans = 0;

	private static void solution() throws IOException {
		input();
		dfs(0, 0);
		if (S == 0)
			System.out.println(ans - 1);
		else
			System.out.println(ans);
	}
	private static void dfs(int depth, int sum) {
		if (depth == N) {
			if (sum == S) ans++;
			return;
		}

		dfs(depth + 1, sum + arr[depth]);
		dfs(depth + 1, sum);
	}
	private static void input() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int[] ns = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		N = ns[0]; S = ns[1];
		arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		br.close();
	}
}