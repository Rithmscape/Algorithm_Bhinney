import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class Main {
	public static void main(String[] args) throws IOException {
		new MinAndMax().solve();
	}

	private static class MinAndMax {
		private int n, m;
		private int[] arr;
		private int[][] queries;
		private Node[] segmentTree;
		private StringBuilder answer;

		public void solve() throws IOException {
			input();
			solution();
			output();
		}

		private void solution() {
			segmentTree = new Node[n * 4];
			answer = new StringBuilder();

			build(1, 0, n - 1);

			for (int[] query : queries) {
				Node result = find(1, 0, n - 1, query[0] - 1, query[1] - 1);
				answer.append(result.min)
					.append(" ")
					.append(result.max)
					.append("\n");
			}
		}

		private void build(int node, int start, int end) {
			if (start == end) { // 리프 노드
				segmentTree[node] = new Node(arr[start], arr[end]);
				return;
			}

			int mid = (start + end) / 2;
			build(2 * node, start, mid); // 좌측
			build(2 * node + 1, mid + 1, end); // 우측

			int min = Math.min(segmentTree[2 * node].min, segmentTree[2 * node + 1].min);
			int max = Math.max(segmentTree[2 * node].max, segmentTree[2 * node + 1].max);

			segmentTree[node] = new Node(min, max);
		}

		private Node find(int node, int start, int end, int left, int right) {
			if (right < start || left > end)
				return new Node(Integer.MAX_VALUE, Integer.MIN_VALUE);

			if (left <= start && end <= right)
				return segmentTree[node];

			int mid = (start + end) / 2;
			Node leftResult = find(2 * node, start, mid, left, right);
			Node rightResult = find(2 * node + 1, mid + 1, end, left, right);

			return new Node(Math.min(leftResult.min, rightResult.min), Math.max(leftResult.max, rightResult.max));
		}

		private void input() throws IOException {
			try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
				String[] s = br.readLine().split(" ");
				n = Integer.parseInt(s[0]);
				m = Integer.parseInt(s[1]);

				arr = new int[n];
				for (int i = 0; i < n; i++)
					arr[i] = Integer.parseInt(br.readLine());

				queries = new int[m][2];
				for (int i = 0; i < m; i++)
					queries[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			}
		}

		private void output() throws IOException {
			try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))){
				bw.write(answer.toString());
				bw.flush();
			}
		}
	}

	private static class Node {
		int min;
		int max;

		public Node(int min, int max) {
			this.min = min;
			this.max = max;
		}
	}
}