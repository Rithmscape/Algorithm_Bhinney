import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class Main {
	public static void main(String[] args) throws IOException {
		Input data = input();
		int answer = solution(data.k, data.coins);
		output(answer);
	}

	private static int solution(int k, int[] coins) {
		int[] dp = new int[k + 1];
		dp[0] = 1;

		for (int i = 0; i < coins.length; i++)
			for (int j = 1; j < k + 1; j++)
				if (j >= coins[i])
					dp[j] += dp[j - coins[i]];

		return dp[k];
	}

	private static Input input() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int[] nk = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		int[] coins = new int[nk[0]];
		for (int i = 0; i < coins.length; i++)
			coins[i] = Integer.parseInt(br.readLine());

		br.close();

		return new Input(nk[1], coins);
	}

	private static void output(int answer) throws IOException {
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		bw.write(String.valueOf(answer));
		bw.flush();
		bw.close();
	}
	private static class Input {
		int k;
		int[] coins;

		public Input(int k, int[] coins) {
			this.k = k;
			this.coins = coins;
		}
	}
}