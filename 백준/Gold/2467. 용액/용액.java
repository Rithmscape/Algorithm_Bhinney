import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	public static void main(String[] args) throws IOException {
		input();
		solution();
	}
	private static int N;
	private static long[] liquid;
	private static void solution() {
		int left = 0;
		int right = N - 1;
		int mid = (left + right) / 2;
		long m = Integer.MAX_VALUE;
		long[] res = {m, m};

		while (left < right) {
			long sum = liquid[left] + liquid[right];
			if (sum == 0) {
				res[0] = liquid[left];
				res[1] = liquid[right];
				break;
			}

			if (m > Math.abs(sum)) {
				res[0] = liquid[left];
				res[1] = liquid[right];
				m = Math.abs(sum);
			}

			if (sum > 0) right--;
			else left++;
		}

		System.out.println(res[0] + " " + res[1]);
	}
	private static void input() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		liquid = Arrays.stream(br.readLine().split(" ")).mapToLong(Long::parseLong).toArray();
		br.close();
	}
}