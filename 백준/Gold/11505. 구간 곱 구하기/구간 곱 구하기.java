import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class Main {
	private static final long MOD = 1_000_000_007;
	public static void main(String[] args) throws IOException {
		new Multiple().solve();
	}

	private static class Multiple {
		private int n, k, m;
		private long[] arr;
		private long[][] queries;
		private long[] tree;
		private StringBuilder answer;

		public void solve() throws IOException {
			input();
			solution();
			output();
		}

		private void solution() {
			tree = new long[n * 4];
			build(1, 0, n - 1);

			answer = new StringBuilder();
			for (long[] query : queries) {
				if (query[0] == 1)
					update(1, 0, n - 1, (int) query[1] - 1, query[2]);
				else
					answer
						.append(multiple(1, 0, n - 1, (int) query[1] - 1, (int) query[2] - 1))
						.append("\n");
			}
		}

		private void build(int node, int start, int end) {
			if (start == end) { // 리프 노드
				tree[node] = arr[start];
				return;
			}

			int mid = (start + end) / 2;
			build(2 * node, start, mid); // 좌측
			build(2 * node + 1, mid + 1, end); // 우측
			tree[node] = (tree[node * 2] * tree[node * 2 + 1]) % MOD;
		}

		private void update(int node, int start, int end, int idx, long value) {
			if (start == end) {
				tree[node] = value;
				return;
			}

			int mid = (start + end) / 2;
			if (idx <= mid)
				update(2 * node, start, mid, idx, value);
			else
				update(2 * node + 1, mid + 1, end, idx, value);

			tree[node] = (tree[2 * node] * tree[2 * node + 1]) % MOD;
		}

		private long multiple(int node, int start, int end, int l, int r) {
			if (r < start || end < l)
				return 1;

			if (l <= start && end <= r)
				return tree[node];

			int mid = (start + end) / 2;

			return (multiple(2 * node, start, mid, l, r) * multiple(2 * node + 1, mid + 1, end, l, r)) % MOD;
		}

		private void input() throws IOException {
			try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
				int[] nkm = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
				n = nkm[0];
				k = nkm[1];
				m = nkm[2];

				arr = new long[n];
				for (int i = 0; i < n; i++)
					arr[i] = Long.parseLong(br.readLine());

				queries = new long[m + k][3];
				for (int i = 0; i < queries.length; i++)
					queries[i] = Arrays.stream(br.readLine().split(" ")).mapToLong(Long::parseLong).toArray();
			}
		}

		private void output() throws IOException {
			try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {
				bw.write(answer.toString());
				bw.flush();
			}
		}
	}
}