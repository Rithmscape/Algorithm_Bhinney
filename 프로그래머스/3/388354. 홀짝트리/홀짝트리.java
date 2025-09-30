import java.util.*;

class Solution {
    public static int[] solution(int[] nodes, int[][] edges) {
		int[] answer = new int[2];

		Map<Integer, List<Integer>> graph = init(nodes, edges);
		Set<Integer> visited = new HashSet<>();

		for (int node : nodes) {
			if (visited.contains(node)) continue;

			TypeCounter counter = new TypeCounter();

			classify(graph, visited, counter, node);

			answer[1] += counter.getOEG();
			answer[0] += counter.getROEG();
		}

		return answer;
	}

	private static Map<Integer, List<Integer>> init(int[] nodes, int[][] edges) {
		Map<Integer, List<Integer>> graph = new HashMap<>();

		for (int node : nodes)
			graph.put(node, new ArrayList<>());

		for (int[] edge : edges) {
			graph.get(edge[0]).add(edge[1]);
			graph.get(edge[1]).add(edge[0]);
		}

		return graph;
	}

	private static void classify(Map<Integer, List<Integer>> graph, Set<Integer> visited, TypeCounter counter, int cur) {
		int degree = graph.get(cur).size();
		boolean currentOdd = cur % 2 == 1;
		boolean degreeOdd = degree % 2 == 1;

		if (!currentOdd && !degreeOdd)
			counter.e++;
		else if (!currentOdd)
			counter.re++;
		else if (!degreeOdd)
			counter.ro++;
		else
			counter.o++;

		visited.add(cur);

		for (int next : graph.get(cur))
			if (!visited.contains(next))
				classify(graph, visited, counter, next);
	}

	private static class TypeCounter {
		private int o; // odd
		private int e; // even
		private int ro; // reverse odd
		private int re; // reverse even

		private int getOEG() {
			if ((ro == 1 && re == 0) || (ro == 0 && re == 1)) return 1;
			else return 0;
		}

		private int getROEG() {
			if ((o == 1 && e == 0) || (o == 0 && e == 1)) return 1;
			else return 0;
		}
	}
}