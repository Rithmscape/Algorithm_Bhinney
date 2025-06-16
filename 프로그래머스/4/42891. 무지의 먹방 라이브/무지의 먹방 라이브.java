import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.stream.IntStream;

class Solution {
	public int solution(int[] food_times, long k) {
		/*
		 * food_times : 각 위치 별 음식을 먹는 데 소요되는 시간
		 * k : 방송이 중단된 시간
		 * [3, 1, 2] => 1초 [2, 1, 2] -> 2초 [2, 0, 2] -> 3초 [2, 0, 1] -> 4초 [1, 0, 1] -> 5초 [1, 0, 0] => 1번부터
		 */
		long times = 0;
		PriorityQueue<Food> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o1.time, o2.time));
		for (int i = 0; i < food_times.length; i++) {
			times += food_times[i];
			pq.offer(new Food(i + 1, food_times[i]));
		}

		if (times <= k || food_times.length == 0) return -1;

		long total = 0;
		long prev = 0;
		long len = food_times.length;

		while (!pq.isEmpty() && total + ((pq.peek().time - prev) * len) <= k) {
			Food cur = pq.poll();
			total += (cur.time - prev) * len;
			len -= 1;
			prev = cur.time;
		}

		List<Food> list = new ArrayList<>();
		while (!pq.isEmpty())
			list.add(pq.poll());

		list.sort((o1, o2) -> Integer.compare(o1.idx, o2.idx));

		return list.get((int)((k - total) % len)).idx;
	}

	private class Food {
		int idx;
		int time;

		public Food(int idx, int time) {
			this.idx = idx;
			this.time = time;
		}
	}
}