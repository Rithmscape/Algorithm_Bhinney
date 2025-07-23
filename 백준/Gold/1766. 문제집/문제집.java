import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class Main {
	public static void main(String[] args) throws IOException {
		InputData data = input();
		String answer = solution(data.graph, data.indegree);
		output(answer);
	}

	private static String solution(List<List<Integer>> graph, int[] indegree) {
		PriorityQueue<Integer> pq = new PriorityQueue<>();

		for (int i = 1; i < graph.size(); i++)
			if (indegree[i] == 0) pq.offer(i);

		StringBuilder answer = new StringBuilder();
		while (!pq.isEmpty()) {
			int current = pq.poll();
			answer.append(current).append(" ");

			for (int next : graph.get(current)) {
				indegree[next]--;
				if (indegree[next] == 0) pq.offer(next);
			}
		}

		return answer.toString().trim();
	}

	private static InputData input() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// [n, m]
		int[] nm = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		List<List<Integer>> graph = new ArrayList<>();
		int[] indegree = new int[nm[0] + 1];

		for (int i = 0; i <= nm[0]; i++)
			graph.add(new ArrayList<>());

		for (int i = 0; i < nm[1]; i++) {
			// [from, to]
			int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			graph.get(arr[0]).add(arr[1]);
			indegree[arr[1]]++;
		}

		br.close();

		return new InputData(graph, indegree);
	}

	private static void output(String answer) throws IOException {
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		bw.write(answer);
		bw.flush();
		bw.close();
	}

	private static class InputData {
		List<List<Integer>> graph;
		int[] indegree;

		public InputData(List<List<Integer>> graph, int[] indegree) {
			this.graph = graph;
			this.indegree = indegree;
		}
	}
}