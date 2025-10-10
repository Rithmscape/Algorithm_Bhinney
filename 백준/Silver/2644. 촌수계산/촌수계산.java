import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
	public static void main(String[] args) throws IOException {
		new Solution().solve();
	}

	private static class Solution {
		private int N, t1, t2, answer;
		private boolean[] visited;
		private List<List<Integer>> graph;
		public void solve() throws IOException {
			input();
			solution();
			output();
		}

		private void solution() {
			visited = new boolean[N + 1];
			answer = -1;
			dfs(t1, t2, 0);
		}

		private void dfs(int s, int e, int depth) {
			if (s == e) {
				answer = depth;
				return;
			}

			visited[s] = true;

			for (int next : graph.get(s))
				if (!visited[next])
					dfs(next, e, depth + 1);
		}

		private void input() throws IOException {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			N = Integer.parseInt(br.readLine());

			graph = new ArrayList<>();
			for (int i = 0; i <= N; i++)
				graph.add(new ArrayList<>());

			String[] target = br.readLine().split(" ");
			t1 = Integer.parseInt(target[0]);
			t2 = Integer.parseInt(target[1]);

			int m = Integer.parseInt(br.readLine());
			for (int i = 0; i < m; i++) {
				int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
				graph.get(arr[0]).add(arr[1]);
				graph.get(arr[1]).add(arr[0]);
			}

			br.close();
		}

		private void output() throws IOException {
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
			bw.write(String.valueOf(answer));
			bw.flush();
		}
	}
}