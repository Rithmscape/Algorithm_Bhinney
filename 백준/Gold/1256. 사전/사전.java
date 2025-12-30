import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	private static double[][] dp;
	private static StringBuilder result;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] arr = br.readLine().split(" ");

		int a = Integer.parseInt(arr[0]); // a의 개수
		int z = Integer.parseInt(arr[1]); // z의 개수
		int k = Integer.parseInt(arr[2]); // 출력할 문자열의 순번

		result = new StringBuilder();
		dp = new double[101][101];

		if (check(a, z) < k)
			System.out.println(-1);
		else {
			make(a, z, k);
			System.out.println(result.toString());
		}
	}

	private static double check(int a, int z) {
		if (a == 0 || z == 0) return 1;
		if (dp[a][z] != 0) return dp[a][z];
		return dp[a][z] = Double.min(check(a - 1, z) + check(a, z - 1), 1_000_000_001);
	}

	private static void make(int a, int z, double k) {
		if (a == 0) {
			result.append("z".repeat(Math.max(0, z)));
			return;
		}

		if (z == 0) {
			result.append("a".repeat(Math.max(0, a)));
			return;
		}

		double check = check(a - 1, z);

		if (k > check) {
			result.append('z');
			make(a, z - 1, k - check);
		} else {
			result.append('a');
			make(a - 1, z, k);
		}
	}
}