import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {
	public static void main(String[] args) throws IOException {
		input();
		solution();
	}
	private static int N, M;
	private static StringBuilder sb;
	private static void solution() {
		int[] arr = IntStream.rangeClosed(1, N).toArray();
		int[] res = new int[M];
		sb = new StringBuilder();
		comb(arr, res, 0, 0);
		System.out.println(sb.toString());
	}
	private static void comb(int[] arr, int[] res, int idx, int depth) {
		if (depth == M) {
			sb.append(
				Arrays.stream(res).mapToObj(String::valueOf).collect(Collectors.joining(" "))
			).append("\n");
			return;
		}

		for (int i = idx; i < N; i++) {
			res[depth] = arr[i];
			comb(arr, res, i, depth + 1);
		}
	}
	private static void input() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int[] nm = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		N = nm[0];
		M = nm[1];
	}
}