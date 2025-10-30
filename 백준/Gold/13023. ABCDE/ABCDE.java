import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Main {
	public static void main(String[] args) throws IOException {
		new Solution().solution();
	}

	private static class Solution {
		private int N, M;
		private List<List<Integer>> map;

		void solution() throws IOException {
			input();
			solve();
		}

		private void solve() {
			for (int i = 0; i < N; i++) {
				boolean[] visited = new boolean[N];

				if (find(i, visited, 0)) {
					System.out.println(1);
					return;
				}
			}

			System.out.println(0);
		}

		private boolean find(int idx, boolean[] visited, int depth) {
			if (depth == 4) return true;

			visited[idx] = true;
			for (int next : map.get(idx))
				if (!visited[next])
					if (find(next, visited, depth + 1)) 
						return true;

			visited[idx] = false;

			return false;
		}

		private void input() throws IOException {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			String[] arr = br.readLine().split(" ");
			N = Integer.parseInt(arr[0]);
			M = Integer.parseInt(arr[1]);

			map = new ArrayList<>();
			for (int i = 0; i < N; i++) // 0 ~ N - 1
				map.add(new ArrayList<>());

			for (int i = 0; i < M; i++) {
				arr = br.readLine().split(" ");
				map.get(Integer.parseInt(arr[0])).add(Integer.parseInt(arr[1]));
				map.get(Integer.parseInt(arr[1])).add(Integer.parseInt(arr[0]));
			}

			br.close();
		}
	}
}