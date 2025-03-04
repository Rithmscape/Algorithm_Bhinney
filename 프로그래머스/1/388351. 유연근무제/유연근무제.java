class Solution {
    public static int solution(int[] schedules, int[][] timelogs, int startday) {
		// schedules : n명의 직원의 출근 희망 시각 배열
		// timelogs : n명의 직원의 출근한 시각 2차원배열
		// startday : 시작한 요일 (토요일, 일요일은 이벤트에 영향이 없음)

		int answer = 0;
		int n = schedules.length;

		for (int i = 0; i < n; i++) {
			if (employee(change(schedules[i]), timelogs[i], startday))
				answer++;
		}

		return answer;
	}

	// 직원 별로 상품 유무 확인, 반복문
	private static boolean employee(int schedule, int[] log, int day) {

		for (int i = 0; i < 7; i++) {
			if (day == 6) {
				day++;
				continue;
			}

			if (day == 7) {
				day = 1;
				continue;
			}

			int time = change(log[i]);
			if (time > schedule + 10) return false;
			day++;
		}

		return true;
	}

	// 시간 변환
	private static int change(int time) {
		String t = String.valueOf(time);

		int hour = Integer.parseInt(t.substring(0, t.length() - 2)) * 60;
		int minute = Integer.parseInt(t.substring(t.length() - 2));

		return hour + minute;
	}
}