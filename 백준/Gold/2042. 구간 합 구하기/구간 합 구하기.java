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
		InputData data = input();
		String answer = solution(data.arr, data.queries);
		output(answer);
	}

	private static String solution(long[] arr, List<long[]> queries) {
		SegmentTree st = new SegmentTree(arr);
		StringBuilder answer = new StringBuilder();

		for (long[] query : queries) {
			if (query[0] == 1)
				st.update((int)(query[1] - 1), query[2]);
			else
				answer.append(st.query((int)(query[1] - 1), (int)query[2] - 1)).append("\n");

		}

		return answer.toString();
	}

	private static InputData input() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int[] nmk = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		long[] arr = new long[nmk[0]];

		for (int i = 0; i < arr.length; i++)
			arr[i] = Long.parseLong(br.readLine());

		List<long[]> queries = new ArrayList<>();
		for (int i = 0; i < nmk[1] + nmk[2]; i++)
			queries.add(Arrays.stream(br.readLine().split(" ")).mapToLong(Long::parseLong).toArray());
		br.close();

		return new InputData(arr, queries);
	}

	private static void output(String answer) throws IOException {
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		bw.write(answer);
		bw.flush();
		bw.close();
	}

	private static class SegmentTree{
		int n;
		long[] tree;

		public SegmentTree(long[] arr) {
			n = arr.length;
			tree = new long[4 * n];
			build(arr, 1, 0, n - 1);
		}

		private void build(long[] arr, int node, int start, int end) {
			if (start == end)
				tree[node] = arr[start];
			else {
				int mid = (start + end) / 2;
				build(arr, 2 * node, start, mid);
				build(arr, 2 * node + 1, mid + 1, end);
				tree[node] = tree[2 * node] + tree[2 * node + 1];
			}
		}

		public void update(int node, int start, int end, int idx, long value) {
			if (start == end)
				tree[node] = value;
			else {
				int mid = (start + end) / 2;

				if (idx <= mid)
					update(2 * node, start, mid, idx, value);
				else
					update(2 * node + 1, mid + 1, end, idx, value);

				tree[node] = tree[2 * node] + tree[2 * node + 1];
			}
		}

		public  void update(int idx, long value) {
			update(1, 0, n - 1, idx, value);
		}

		public long query(int node, int start, int end, int l, int r) {
			if (r < start || end < l)
				return 0;

			if (l <= start && end <= r)
				return tree[node];

			int mid = (start + end) / 2;

			return query(2 * node, start, mid, l, r) +
				query(2 * node + 1, mid + 1, end, l, r);
		}

		public long query(int l, int r) {
			return query(1, 0, n - 1, l, r);
		}
	}

	private static class InputData {
		long[] arr;
		List<long[]> queries;

		public InputData(long[] arr, List<long[]> queries) {
			this.arr = arr;
			this.queries = queries;
		}
	}
}