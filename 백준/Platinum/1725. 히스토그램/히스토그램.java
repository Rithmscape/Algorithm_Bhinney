import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
	public static void main(String[] args) throws IOException {
		new Solution().solution();
	}

	private static class Solution {
		private int N;
		private int[] graph;

		void solution() throws IOException {
			input();
			output(solve());
		}

		private int solve() {
			Stack<Integer> stack = new Stack<>();
			stack.push(0);

			int max = 0;

			for (int i = 1; i < graph.length; i++) {
				while (!stack.isEmpty()) {
					int t = stack.peek();

					if (graph[t] <= graph[i]) break;

					stack.pop();
					max = Math.max(max, graph[t] * (i - stack.peek() - 1));
				}

				stack.push(i);
			}

			return max;
		}

		private void input() throws IOException {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			N = Integer.parseInt(br.readLine());
			graph = new int[N + 2];

			for (int i = 0; i < N; i++)
				graph[i + 1] = Integer.parseInt(br.readLine());

			br.close();
		}

		private void output(int answer) {
			System.out.println(answer);
		}
	}
}
