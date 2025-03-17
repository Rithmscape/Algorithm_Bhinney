import java.util.PriorityQueue;

class Solution {
    public int solution(int[] players, int m, int k) {
		// m 명이 늘어나면 -> 서버 한 대 증설
		// n * m <= 특정 시간 대 인원 < (n + 1) * m ==> 최소 n 대의 증설된 서버 운영
		// 한 번 증설 된 서버는 k 시간 동안 운영하고 반납

		// 우선 순위 큐를 이용해서 시간이 지난 서버들을 꺼주기
		PriorityQueue<Server> q = new PriorityQueue<>((o1,o2) -> o1.time - o2.time);

		int server = 0; // 서버 개수
		int cnt = 0; // 증설 횟수

		for (int i = 0; i < 24; i++) { // 24 시간으로 계산

			// 큐가 비어있지 않고, 서버 시간이 꺼져야 하면 꺼버리기
			while (!q.isEmpty() && q.peek().time == i) {
				server -= q.poll().count; // 활성화 된 서버에서 꺼져야 하는 서버 빼주기
			}

			int need = players[i] / m;
			int plus = need - server;

			if (plus > 0) {
				server += plus;
				cnt += plus;
				q.add(new Server(i + k, plus));
			}
		}

		return cnt;
	}

	private class Server {
		int time; // 서버가 꺼지는 시간
		int count; // 서버 개수

		public Server(int time, int count) {
			this.time = time;
			this.count = count;
		}
	}
}