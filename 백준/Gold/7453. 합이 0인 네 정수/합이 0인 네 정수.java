import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	public static void main(String[] args) throws IOException {
		input();
		solution();
	}
	private static int n;
	private static int[][] arr;
	private static void solution() {
		int[] sum1 = new int[n * n];
		int[] sum2 = new int[n * n];

		int idx = 0;
		for (int i = 0; i < n; i++)
			for (int j = 0; j < n; j++) {
				sum1[idx] = arr[i][0] + arr[j][1];
				sum2[idx] = arr[i][2] + arr[j][3];
				idx++;
			}

		Arrays.sort(sum1);
		Arrays.sort(sum2);

		long ans = searchZero(sum1, sum2);
		System.out.println(ans);
	}

	private static long searchZero(int[] sum1, int[] sum2) {
		long ret = 0;
		int p1 = 0;
		int p2 = n * n - 1;

		while (p1 < n * n && p2 >= 0) {
			int sum = sum1[p1] + sum2[p2];

			if (sum == 0) {
				long c1 = 1;
				long c2 = 1;

				while (p1 < n * n - 1 && sum1[p1] == sum1[p1 + 1]) {
					c1++;
					p1++;
				}

				while (p2 > 0 && sum2[p2] == sum2[p2 - 1]) {
					c2++;
					p2--;
				}

				ret += c1 * c2;
				p1++;
				p2--;
			}
			else if (sum < 0) p1++;
			else p2--;
		}

		return ret;
	}
	private static void input() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		arr = new int[n][4];
		for (int i = 0; i < n; i++)
			arr[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		br.close();
	}
}