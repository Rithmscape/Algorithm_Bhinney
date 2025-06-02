import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {
	public static void main(String[] args) throws IOException {
		solution();
	}

	private static int N, R, Q; // N : 정점 수, R : 루트 번호, Q : 쿼리 수
	private static ArrayList<ArrayList<Integer>> adj; // 간선 정보
	private static ArrayList<ArrayList<Integer>> child; // 자식 노드 정보
	private static int[] res;
	private static void solution() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int[] nrq = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		N = nrq[0]; R = nrq[1]; Q = nrq[2];
		res = new int[N + 1];
		adj = new ArrayList<>();
		child = new ArrayList<>();
		for (int i = 0; i < N + 1; i++) {
			adj.add(new ArrayList<>());
			child.add(new ArrayList<>());
		}

		for (int i = 0; i < N - 1; i++) {
			int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

			// 간선 info 양방향 저장
			adj.get(arr[0]).add(arr[1]);
			adj.get(arr[1]).add(arr[0]);
		}

		// 부모 - 자식 노드 정보 저장
		makeTree(R, -1);

		count(R);

		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < Q; i++) {
			int node = Integer.parseInt(br.readLine());
			sb.append(res[node]).append("\n");
		}

		System.out.println(sb.toString());
	}

	private static void count(int cur) {
		res[cur] = 1; // 자기 자신도 포함

		for (int child : child.get(cur)) {
			count(child);
			res[cur] += res[child];
		}
	}

	private static void makeTree(int cur, int parent) {
		for (int node : adj.get(cur)) {
			if (node != parent) {
				child.get(cur).add(node);
				makeTree(node, cur);
			}
		}
	}
}