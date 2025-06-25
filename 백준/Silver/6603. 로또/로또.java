import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
	public static void main(String[] args) throws IOException {
		List<Node> nodes = input();
		List<List<String>>result = solution(nodes);
		output(result);
	}

	private static List<List<String>> solution(List<Node> nodes) {
		List<List<String>> result = new ArrayList<>();

		for (Node node : nodes) {
			int[] res = new int[6];
			List<String> list = new ArrayList<>();
			dfs(0, 0, node.arr, list, res);
			result.add(list);
		}

		return result;
	}

	private static void dfs(int start, int depth, int[] arr, List<String> list, int[] res) {
		if (depth == 6) {
			list.add(Arrays.stream(res).mapToObj(String::valueOf).collect(Collectors.joining(" ")));
			return;
		}

		for (int i = start; i < arr.length; i++) {
			res[depth] = arr[i];
			dfs(i + 1, depth + 1, arr, list, res);
		}
	}

	private static List<Node> input() throws IOException {
		List<Node> list = new ArrayList<>();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		while (true) {
			int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			if (arr[0] == 0) break;

			int[] nodes = new int[arr[0]];
			System.arraycopy(arr, 1, nodes, 0, arr[0]);
			list.add(new Node(arr[0], nodes));
		}

		return list;
	}

	private static void output(List<List<String>> result) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < result.size(); i++) {
			if (i == 0) {
				sb.append(String.join("\n", result.get(0))).append("\n");
				continue;
			}

			sb.append("\n").append(String.join("\n", result.get(i))).append("\n");
		}

		System.out.print(sb.toString());
	}

	private static class Node {
		int k;
		int[] arr;

		public Node(int k, int[] arr) {
			this.k = k;
			this.arr = arr;
		}
	}
}
