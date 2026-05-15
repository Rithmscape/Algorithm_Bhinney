import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class Solution {
    public int solution(int n, int infection, int[][] edges, int k) {
		/*
		 1 ~ n 개의 배양체(1개 감염 -> 파이프로 인접 배양체 감염)
		 n - 1개의 파이프 (A/ B/ C)
		 k번 파이프 오픈

 		 * n : 배양체의 개수
		 * infection : 감염 배양체 번호
		 * edges : 파이프 info. [x, y, type] = [x번 노드, y번 노드, 파이프 타입]
		 * k : 파이프 여는 횟수
		 */

		@SuppressWarnings("unchecked") // 밑줄 알림 방지
		List<int[]>[] graph = new List[n + 1];

		// List 초기화
		for (int i = 0; i < graph.length; i++)
			graph[i] = new ArrayList<>();

		// 노드 파이프 연결 저장
		for (int[] edge : edges) {
			graph[edge[0]].add(new int[] {edge[1], edge[2]});
			graph[edge[1]].add(new int[] {edge[0], edge[2]});
		}

		boolean[] infected = new boolean[n + 1];
		infected[infection] = true;

		return dfs(graph, infected, k, n, 1);
	}

	private int dfs(List<int[]>[] graph, boolean[] infected, int k, int n, int count) {
		int answer = count;

		if (k == 0 || count == n)
			return answer;

		for (int type = 1; type <= 3; type++) {
			boolean[] next = spread(graph, infected.clone(), type, n);

			int gained = count(next) - count;

			if (gained > 0)
				answer = Math.max(answer, dfs(graph, next, k - 1, n, count + gained));
		}

		return answer;
	}

	private boolean[] spread(List<int[]>[] graph, boolean[] infected, int type, int n) {
		Queue<Integer> queue = new LinkedList<>();

		for (int i = 1; i <= n; i++)
			if (infected[i]) queue.add(i);

		while (!queue.isEmpty()) {
			int cur = queue.poll();
			for (int[] next : graph[cur]) {
				if (next[1] == type && !infected[next[0]]) {
					infected[next[0]] = true;
					queue.add(next[0]);
				}
			}
		}

		return infected;
	}

	private int count(boolean[] arr) {
		int cnt = 0;
		for (boolean a : arr) if (a) cnt++;
		return cnt;
	}
}