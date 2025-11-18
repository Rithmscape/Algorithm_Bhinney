import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Main {
	public static void main(String[] args) throws IOException {
		 BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		 int K = Integer.parseInt(br.readLine());

		 for (int tc = 0; tc < K; tc++) {
			 String[] arr = br.readLine().split(" ");
			 int V = Integer.parseInt(arr[0]);
			 int E = Integer.parseInt(arr[1]);

			 List<List<Integer>> graph = new ArrayList<>();
			 for (int i = 0; i <= V; i++)
				 graph.add(new ArrayList<>());

			 for (int i = 0; i < E; i++) {
				 arr = br.readLine().split(" ");
				 int from = Integer.parseInt(arr[0]);
				 int to = Integer.parseInt(arr[1]);

				 graph.get(from).add(to);
				 graph.get(to).add(from);
			 }

			 check(graph);
		 }
	}

	private static void check(List<List<Integer>> graph) {
		Queue<Integer> q = new LinkedList<>();
		int[] color = new int[graph.size()];

		for (int i = 1; i < graph.size(); i++) {
			if (color[i] == 0) {
				q.offer(i);
				color[i] = 1;
			}

			while (!q.isEmpty()) {
				int cur = q.poll();

				for (int j = 0; j < graph.get(cur).size(); j++) {
					if (color[graph.get(cur).get(j)] == 0)
						q.offer(graph.get(cur).get(j));

					if (color[graph.get(cur).get(j)] == color[cur]) {
						System.out.println("NO");
						return;
					}

					if (color[cur] == 1 && color[graph.get(cur).get(j)] == 0)
						color[graph.get(cur).get(j)] = 2;
					else if (color[cur] == 2 && color[graph.get(cur).get(j)] == 0)
						color[graph.get(cur).get(j)] = 1;
				}
			}
		}

		System.out.println("YES");
	}
}