import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

class Solution {
    private static int answer;
	public static int solution(int k, int n, int[][] reqs) {
		// n : 멘토 인원
		// k : 상담 유형
		answer = Integer.MAX_VALUE;

		List<List<int[]>> requests= new ArrayList<>();
		for (int i = 0; i <= k; i++)
			requests.add(new ArrayList<>());

		for (int[] req : reqs)
			requests.get(req[2]).add(req);

		for (List<int[]> req : requests)
			req.sort(Comparator.comparingInt(a -> a[0]));

		int[] types = new int[k + 1];
		backtracking(1, k, n - k, types, requests);

		return answer;
	}

	private static void backtracking(int type, int k, int remainMentor, int[] types, List<List<int[]>> requests) {
		if (type > k) {
			types[0] = remainMentor;
			int total = simulate(types, requests, k);
			answer = Math.min(answer, total);
			return;
		}

		for (int m = 1; m <= remainMentor + 1; m++) {
			types[type] = m;
			backtracking(type + 1, k, remainMentor - (m - 1), types, requests);
		}
	}

	private static int simulate(int[] types, List<List<int[]>> requests, int k) {
		int total = 0;

		for (int type = 1; type <= k; type++) {
			List<int[]> req = requests.get(type);

			if (req.isEmpty()) continue;

			int mentor = types[type];
			PriorityQueue<Integer> pq = new PriorityQueue<>();
			for (int i = 0; i < mentor; i++)
				pq.offer(0);

			for (int[] request : req) {
				int start = request[0];
				int duration = request[1];

				int cur = pq.poll();

				int time = Math.max(start, cur);

				int wait = time - start;

				total += wait;

				pq.offer(time + duration);
			}
		}

		return total;
	}
}