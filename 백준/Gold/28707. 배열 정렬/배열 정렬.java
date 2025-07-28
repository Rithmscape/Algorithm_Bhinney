import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {
	public static void main(String[] args) throws IOException {
		ArraySorting sorting = new ArraySorting();
		sorting.solve();
	}

	private static class ArraySorting {
		private int n;
		private int m;
		private int[] arr;
		private int[][] operations;

		private int answer;

		public void solve() throws IOException {
			input();
			sort();
			output();
		}

		private void sort() {
			int[] targetArr = arr.clone();
			Arrays.sort(targetArr);

			if (Arrays.equals(arr, targetArr)) {
				answer = 0;
				return;
			}

			PriorityQueue<State> pq = new PriorityQueue<>((o1, o2) -> o1.cost - o2.cost);
			Map<String, Integer> visited = new HashMap<>();

			String init = arrToString(arr);
			pq.offer(new State(arr.clone(), 0));
			visited.put(init, 0);

			String target = arrToString(targetArr);

			while (!pq.isEmpty()) {
				State cur = pq.poll();
				String current = arrToString(cur.arr);

				if (current.equals(target)) {
					answer = cur.cost;
					return;
				}

				if (visited.containsKey(current) && visited.get(current) < cur.cost) continue;

				for (int i = 0; i < m; i++) {
					int l = operations[i][0] - 1; // index
					int r = operations[i][1] - 1; // index
					int c = operations[i][2]; // cost

					int[] newArr = cur.arr.clone();
					int temp = newArr[l];
					newArr[l] = newArr[r];
					newArr[r] = temp;

					String key = arrToString(newArr);
					int cost = cur.cost + c;

					if (visited.containsKey(key) && visited.get(key) <= cost) continue;

					visited.put(key, cost);
					pq.offer(new State(newArr, cost));
				}
			}

			answer = -1;
		}

		private String arrToString(int[] arr) {

			return IntStream.of(arr)
				.mapToObj(String::valueOf)
				.collect(Collectors.joining(", "));
		}
		private void input() throws IOException {
			try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
				n = Integer.parseInt(br.readLine());
				arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
				m = Integer.parseInt(br.readLine());
				operations = new int[m][3];

				for (int i = 0; i < m; i++)
					operations[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			}
		}

		private void output() throws IOException {
			try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {
				bw.write(String.valueOf(answer));
				bw.flush();
			}
		}
	}

	private static class State {
		int[] arr;
		int cost;

		public State(int[] arr, int cost) {
			this.arr = arr;
			this.cost = cost;
		}
	}
}
