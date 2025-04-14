import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {
	public static void main(String[] args) throws IOException {
		input();
		visited = new boolean[N];
		solution();
	}

	private static int N, M;
	private static int[] arr;
	private static boolean[] visited;
	private static List<String> list;
	private static void solution() {
		list = new ArrayList<>();
		int[] result = new int[M];
		dfs(0, 0, result);

		String res = list.stream().distinct().collect(Collectors.joining("\n"));
		System.out.println(res);
	}

	private static void dfs(int idx, int depth, int[] result) {
		if (depth == M) {
			list.add(IntStream.of(result)
				.mapToObj(String::valueOf)
				.collect(Collectors.joining(" ")));

			return;
		}

		for (int i = idx; i < N; i++) {
			if (visited[i]) continue;

			visited[i] = true;
			result[depth] = arr[i];
			dfs(i + 1, depth + 1, result);
			visited[i] = false;
		}

	}

	private static void input() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int[] nm = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).sorted().toArray();
		N = nm[0];
		M = nm[1];
		br.close();
	}
}