import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
	public static void main(String[] args) throws IOException {
		new Solution().solve();
	}

	private static class Solution {
		private SegmentTree st;
		private int[] series;
		private Query[] queries;
		private StringBuilder answer;

		public void solve() throws IOException {
			input();
			solution();
			output();
		}

		private void solution() {
			st = new SegmentTree(series);
			answer = new StringBuilder();

			for (Query query : queries) {
				if (query.command == 1)
					st.update(query.i - 1, query.v);
				else
					answer
						.append(st.query(query.i - 1, query.v - 1) + 1)
						.append("\n");
			}
		}

		private void input() throws IOException {
			try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
				int n = Integer.parseInt(br.readLine());

				series = new int[n];
				String[] arr = br.readLine().split(" ");
				for (int i = 0; i < n; i++)
					series[i] = Integer.parseInt(arr[i]);

				int m = Integer.parseInt(br.readLine());
				queries = new Query[m];
				for (int i = 0; i < m; i++) {
					String[] array = br.readLine().split(" ");
					queries[i] = new Query(
						Integer.parseInt(array[0]),
						Integer.parseInt(array[1]),
						Integer.parseInt(array[2])
					);
				}
			}
		}

		private void output() throws IOException {
			try(BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {
				bw.write(answer.toString());
				bw.flush();
			}
		}
	}

	private static class SegmentTree {
		int n;
		int[] tree; // 구간의 최솟값의 인덱스 저장
		int[] values; // 실제 값

		public SegmentTree(int[] series) {
			this.n = series.length;
			tree = new int[n * 4];
			values = series.clone();
			init(1, 0, n - 1);
		}

		private void init(int node, int start, int end) {
			if (start == end)
				tree[node] = start; // 리프 노드는 자기 자신
			else {
				int mid = (start + end) / 2;
				init(2 * node, start, mid); // 좌
				init(2 * node + 1, mid + 1, end); // 우

				int left = tree[2 * node];
				int right = tree[2 * node + 1];
				tree[node] = (values[left] <= values[right]) ? left : right;
			}
		}

		public void update(int idx, int value) {
			values[idx] = value;
			update(1, 0, n - 1, idx);
		}

		private void update(int node, int start, int end, int idx) {
			if (start == end)
				tree[node] = idx;
			else {
				int mid = (start + end) / 2;
				if (idx <= mid)
					update(2 * node, start, mid, idx);
				else
					update(2 * node + 1, mid + 1, end, idx);

				int left = tree[2 * node];
				int right = tree[2 * node + 1];
				tree[node] = (values[left] <= values[right]) ? left : right;
			}
		}

		public int query(int left, int right) {
			return query(1, 0, n - 1, left, right);
		}

		private int query(int node, int start, int end, int left, int right) {
			if (right < start || end < left)
				return -1; // 범위를 벗어남.

			if (left <= start && end <= right)
				return tree[node]; // 범위 안.

			int mid = (start + end) / 2;
			int lr = query(2 * node, start, mid, left, right);
			int rr = query(2 * node + 1, mid + 1, end, left, right);

			if (lr == -1) return rr;
			if (rr == -1) return lr;

			return (values[lr] <= values[rr]) ? lr : rr;
		}
	}

	private static class Query {
		int command;
		int i;
		int v;

		public Query(int command, int i, int v) {
			this.command = command;
			this.i = i;
			this.v = v;
		}
	}
}