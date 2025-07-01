import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Main {
	public static void main(String[] args) throws IOException {
		InputData data = input();
		String answer = solution(data.n, data.m, data.graph, data.edges);
		output(answer);
	}

	private static String solution(int n, int m, List<List<Integer>> graph, int[] edges) {
		Queue<Integer> queue = new LinkedList<>();

		for (int i = 1; i < n + 1; i++)
			if (edges[i] == 0) queue.offer(i);

		StringBuilder sb = new StringBuilder();
		while (!queue.isEmpty()) {
			int student = queue.poll();
			sb.append(student).append(" ");

			List<Integer> list = graph.get(student);
			for (int next : list) {
				edges[next]--;

				if (edges[next] == 0)
					queue.offer(next);
			}
		}

		return sb.toString().trim();
	}

	private static InputData input() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int[] nm = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

		int[] edges = new int[nm[0] + 1];
		List<List<Integer>> graph = new ArrayList<>();
		for (int i = 0; i <= nm[0]; i++)
			graph.add(new ArrayList<>());

		for (int i = 0; i < nm[1]; i++) {
			int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			graph.get(arr[0]).add(arr[1]);
			edges[arr[1]]++;
		}
		br.close();

		return new InputData(nm[0], nm[1], graph, edges);
	}

	private static void output(String answer) throws IOException {
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		bw.write(answer);
		bw.flush();
		bw.close();
	}

	private static class InputData {
		int n;
		int m;
		List<List<Integer>> graph;
		int[] edges;

		public InputData(int n, int m, List<List<Integer>> graph, int[] edges) {
			this.n = n;
			this.m = m;
			this.graph = graph;
			this.edges = edges;
		}
	}
}
