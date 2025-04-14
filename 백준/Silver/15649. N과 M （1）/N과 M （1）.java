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
		System.out.println(sb.toString());
	}
	private static int N,M;
	private static int[] arr;
	private static int[] res;
	private static boolean[] visited;
	private static StringBuilder sb;

	private static void solution() {
		arr = IntStream.rangeClosed(1, N).toArray();
		res = new int[M];
		visited = new boolean[N];
		sb = new StringBuilder();
		perm(0);
	}

	private static void perm(int depth) {
		if (depth == M) {
			sb.append(
				Arrays.stream(res).mapToObj(String::valueOf).collect(Collectors.joining(" "))
			).append("\n");
			return;
		}

		for (int i = 0; i < N; i++) {
			if (visited[i]) continue;

			visited[i] = true;
			res[depth] = arr[i];
			perm( depth + 1);
			visited[i] = false;
		}
	}

	private static void input() throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int[] nm = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		N = nm[0];
		M = nm[1];
	}
}