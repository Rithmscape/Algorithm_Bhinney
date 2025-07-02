import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
	public static void main(String[] args) throws IOException {
		InputData data = input();
		int answer = solution(data.c, data.n, data.infos);
		output(answer);
	}

	private static int solution(int c, int n, List<int[]> infos) {
		int[] dp = new int[c + 101];
		Arrays.fill(dp, 1_000_000_000);
		dp[0] = 0;

		for (int[] info : infos) {
			int cost = info[0];
			int people = info[1];

			for (int i = people; i < c + 101; i++)
				dp[i] = Math.min(dp[i], cost + dp[i - people]);
		}

		int answer = Integer.MAX_VALUE;
		for (int i = c; i < c + 101; i++)
			answer = Math.min(answer, dp[i]);

		return answer;
	}

	private static InputData input() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int[] cn = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		int c = cn[0];
		int n = cn[1];

		List<int[]> list = new ArrayList<>();
		for (int i = 0; i < n; i++)
			list.add(Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray());

		br.close();

		return new InputData(c, n, list);
	}

	private static void output(int answer) {
		System.out.println(answer);
	}

	private static class InputData {
		int c;
		int n;
		List<int[]> infos;

		public InputData(int c, int n, List<int[]> infos) {
			this.c = c;
			this.n = n;
			this.infos = infos;
		}
	}
}