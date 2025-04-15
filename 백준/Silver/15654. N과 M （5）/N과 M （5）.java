import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
	public static void main(String[] args) throws IOException {
		input();
		solution();
	}
	private static int N, M;
	private static int[] arr;
	private static List<String> list;
	private static void solution() {
		int[] res = new int[M];
		boolean[] visited = new boolean[N];
		list = new ArrayList<>();
		perm(res, visited, 0);
		System.out.println(String.join("\n", list));
	}

	private static void perm(int[] res, boolean[] visited, int depth) {
		if (depth == M) {
			list.add(
				Arrays.stream(res).mapToObj(String::valueOf).collect(Collectors.joining(" "))
			);

			return;
		}

		for (int i = 0; i < N; i++) {
			if (visited[i]) continue;

			visited[i] = true;
			res[depth] = arr[i];
			perm(res, visited, depth + 1);
			visited[i] = false;
		}
	}

	private static void input() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int[] nm = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).sorted().toArray();
		N = nm[0];
		M = nm[1];
	}
}