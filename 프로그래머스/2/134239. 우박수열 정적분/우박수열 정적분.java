import java.util.ArrayList;
import java.util.List;

class Solution {
    public double[] solution(int k, int[][] ranges) {
		/*
		 * k % 2 == 0 -> k /= 2
		 * k % 2 != 0 -> k * 3 + 1
		 * while (k > 1)
		 */

		// 콜라츠 수열 입력. 인덱스로 x값, value는 y값
		List<Integer> collatz = new ArrayList<>();
		collatz(collatz, k);
		int cnt = collatz.size() - 1; // 콜라츠 횟수

		// 해당 인덱스 위치에 넓이 계산
		List<Double> area = new ArrayList<>();
		area(collatz, area);

		double[] answer = new double[ranges.length];
		for (int i = 0; i < ranges.length; i++) {
			int end = cnt + ranges[i][1];


			if (ranges[i][0] > end) // 시작점이 끝점보다 큰 경우
				answer[i] = -1.0;
			else // 그렇지 않은 경우
				for (int start = ranges[i][0]; start < end; start++)
					answer[i] += area.get(start);
		}

		return answer;
	}

	private void collatz(List<Integer> collatz, int k) {
		collatz.add(k);
		while (k > 1) {
			k = (k % 2 == 0) ? k / 2 : k * 3 + 1;
			collatz.add(k);
		}
	}

	private void area(List<Integer> collatz, List<Double> area) {
		for (int i = 1; i < collatz.size(); i++) {
			double a = (double) Math.min(collatz.get(i - 1), collatz.get(i));
			double b = Math.abs(collatz.get(i - 1) - collatz.get(i)) / 2.0;
			area.add(a + b);
		}
	}
}