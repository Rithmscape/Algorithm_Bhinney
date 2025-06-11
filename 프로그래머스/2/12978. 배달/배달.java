import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

class Solution {
    public long solution(int N, int[][] road, int K) {
		/*
		 * 1 ~ N까지 번호가 부여 된 N개의 마을로 구성된 나라
		 * 각 마을은 양방향으로 통행 가능
		 * 1번의 마을에서 각 마을로 배달
		 * K시간 이하로 배달이 가능한 지역에서만 주문을 받을 예정 -> 마을 개수 리턴
		 */

		int[][] costs = new int[N + 1][N + 1]; // 각 마을의 번호를 인덱스로 처리하기 위해  + 1
		Arrays.stream(costs).forEach(it -> Arrays.fill(it, Integer.MAX_VALUE));
		costRegistration(road, costs);

		int[] dist = new int[N + 1]; // 1번에서 각 지역으로 배달하는 최소 금액 저장 배열
		Arrays.fill(dist, Integer.MAX_VALUE);
		dist[1] = 0;

		Queue<Integer> q = new LinkedList<>();
		q.offer(1);

		while (!q.isEmpty()) {
			int cur = q.poll();

			for (int i = 1; i <= N; i++) {
				if (costs[cur][i] == Integer.MAX_VALUE || dist[i] <= dist[cur] + costs[cur][i]) continue;

				dist[i] = dist[cur] + costs[cur][i];
				q.offer(i);
			}
		}

		return Arrays.stream(dist).filter(it -> it <= K).count();
	}

	private void costRegistration(int[][] road, int[][] costs) {
		for (int[] info : road) { // 양방향으로 비용 등록
			costs[info[0]][info[1]] = Math.min(costs[info[0]][info[1]], info[2]);
			costs[info[1]][info[0]] = Math.min(costs[info[1]][info[0]], info[2]);
		}
	}
}