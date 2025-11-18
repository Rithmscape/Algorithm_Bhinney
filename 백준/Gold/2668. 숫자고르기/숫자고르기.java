import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
	public static void main(String[] args) throws IOException {
		new Solution().solution();
	}

	private static class Solution {
		private int[] arr;
		private List<Integer> list;

		void solution() throws IOException {
			input();
			solve();
			output();
		}

		private void solve() {
			boolean[] visited = new boolean[arr.length];
			list = new ArrayList<>();
			visited[0] = true;

			for (int i = 1; i < arr.length; i++) {
				visited[i] = true;
				dfs(i, i, visited);
				visited[i] = false;
			}

			Collections.sort(list);
		}

		private void dfs(int start, int target, boolean[] visited) {
			if (!visited[arr[start]]) {
				visited[arr[start]] = true;
				dfs(arr[start], target, visited);
				visited[arr[start]] = false;
			}

			if (arr[start] == target)
				list.add(target);
		}

		private void input() throws IOException {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

			int n = Integer.parseInt(br.readLine());
			arr = new int[n + 1];

			for (int i = 1; i <= n; i++)
				arr[i] = Integer.parseInt(br.readLine());

			br.close();
		}

		private void output() {
			System.out.println(list.size());
			System.out.println(list.stream().map(String::valueOf).collect(Collectors.joining("\n")));
		}
	}
}