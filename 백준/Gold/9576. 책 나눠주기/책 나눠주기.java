import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {
	public static void main(String[] args) throws IOException {
		List<Node> list = input();
		int[] answer = solution(list);
		output(answer);
	}

	private static int[] solution(List<Node> list) {
		int[] answer = new int[list.size()];

		for (int idx = 0; idx < list.size(); idx++) {
			Node node = list.get(idx);
			boolean[] check = new boolean[node.n + 1];

			node.applies.sort(new Comparator<int[]>() {
				@Override
				public int compare(int[] o1, int[] o2) {
					return o1[1] - o2[1];
				}
			});

			for (int[] apply : node.applies) {
				int start = apply[0];
				int end = apply[1];

				for (int i = start; i <= end; i++) {
					if (check[i]) continue;

					check[i] = true;
					answer[idx]++;
					break;
				}
			}
		}

		return answer;
	}

	private static List<Node> input() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());

		List<Node> list = new ArrayList<>();

		while (t-- > 0) {
			int[] nm = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

			List<int[]> applies = new ArrayList<>();
			for (int i = 0; i < nm[1]; i++) {
				applies.add(Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray());
			}

			list.add(new Node(nm[0], nm[1], applies));
		}

		return list;
	}

	private static void output(int[] answer) throws IOException {
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		bw.write(IntStream.of(answer).mapToObj(String::valueOf).collect(Collectors.joining("\n")));
		bw.flush();
		bw.close();
	}

	private static class Node {
		int n;
		int m;
		List<int[]> applies;

		public Node(int n, int m, List<int[]> applies) {
			this.n = n;
			this.m = m;
			this.applies = applies;
		}
	}
}
