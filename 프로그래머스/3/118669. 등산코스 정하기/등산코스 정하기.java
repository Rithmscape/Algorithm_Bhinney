import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;

class Solution {
    public int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
		/*
		 * n : 지점의 개수
		 * paths : 각 등산로의 정보
		 * gates : 출입구 번호
		 * summits : 산봉우리 번호

* 		 * answer = {산봉우리 번호, 휴식 없이 이동하는 가장 긴 시간};
		 */

		List<List<Node>> graph = graph(n, paths); // 양방향 연결 정보 정하기
		Set<Integer> gate = addSet(gates); // 출입구 정보 set 담기
		Set<Integer> summit = addSet(summits); // 봉우리 정보 set 담기

		int[] intensity = new int[n + 1];
		Arrays.fill(intensity, Integer.MAX_VALUE);

		dijkstra(graph, intensity, gate, summit);

		int[] answer = {Integer.MAX_VALUE, Integer.MAX_VALUE};
		Arrays.sort(summits);

		for (int s : summits) {
			if (intensity[s] >= answer[1]) continue;
			answer[0] = s;
			answer[1] = intensity[s];
		}

		return answer;
	}

	private void dijkstra(List<List<Node>> graph, int[] intensity, Set<Integer> gates, Set<Integer> summits) {
		PriorityQueue<Node> pq = new PriorityQueue<>((a, b ) -> a.time - b.time);

		for (int gate : gates) {
			intensity[gate] = 0;
			pq.offer(new Node(gate, 0));
		}

		while (!pq.isEmpty()) {
			Node cur = pq.poll();

			if (cur.time > intensity[cur.to]) continue;
			if (summits.contains(cur.to)) continue; // 산봉우리에 도착하면 continue

			for (Node next : graph.get(cur.to)) {
				if (gates.contains(next.to)) continue;

				int newIntensity = Math.max(next.time, cur.time);

				if (newIntensity < intensity[next.to]) {
					intensity[next.to] = newIntensity;
					pq.offer(new Node(next.to, newIntensity));
				}
			}
		}
	}

	private List<List<Node>> graph(int n, int[][] paths) {
		List<List<Node>> list = new ArrayList<>();
		for (int i = 0; i <= n; i++)
			list.add(new ArrayList<>());

		// [지점, 지점, 거리]
		for (int[] path : paths) {
			int i = path[0];
			int j = path[1];
			int w = path[2];

			list.get(i).add(new Node(j, w));
			list.get(j).add(new Node(i, w));
		}

		return list;
	}

	private Set<Integer> addSet(int[] info) {
		Set<Integer> set = new HashSet<>();
		for (int i : info)
			set.add(i);

		return set;
	}

	private class Node {
		int to;
		int time;

		public Node(int to, int time) {
			this.to = to;
			this.time = time;
		}
	}
}