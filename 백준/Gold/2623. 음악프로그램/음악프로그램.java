import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.stream.Collectors;

public class Main {
	public static void main(String[] args) throws IOException {
		input();
		solution();
	}

	private static int N, M;
	private static int[] indegree;
	private static List<List<Integer>> order;
	private static void solution() {
		Queue<Integer> q = new LinkedList<>();
		List<Integer> res = new ArrayList<>();

		for (int i = 1; i <= N; i++)
			if (indegree[i] == 0) q.offer(i);

		while (!q.isEmpty()) {
			int cur = q.poll();
			res.add(cur);

			for (int n : order.get(cur)) {
				indegree[n]--;
				if (indegree[n] == 0)
					q.offer(n);
			}
		}

		if (res.size() != N)
			System.out.println("0");
		else {
			String s = res.stream().map(String::valueOf).collect(Collectors.joining("\n"));
			System.out.println(s);
		}
	}

	private static void input() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int[] nm = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		N = nm[0]; M = nm[1];
		indegree = new int[N + 1];

		order = new ArrayList<>();
		for (int i = 0; i <= N; i++)
			order.add(new ArrayList<>());

		for (int i = 0; i < M; i++) {
			int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			for (int j = 1; j < arr[0]; j++) {
				order.get(arr[j]).add(arr[j + 1]);
				indegree[arr[j + 1]]++;
			}
		}

		br.close();
	}
}