import java.util.*;

class Solution {
    public static int solution(String[] lines) {
		int answer = 0;

		List<Traffic> list = new ArrayList<>();
		for (String line : lines) {
			String[] arr = line.split(" "); // [date, time, traffic_time]
			int end = changeTime(arr[1]);
			int treat =  changeTreat(arr[2].replaceAll("s", ""));
			int start = end - treat + 1;

			list.add(new Traffic(start, end));
		}

		int max = 1;
		for (int i = 0; i < list.size(); i++) {
			int cnt = 0;
			for (Traffic traffic : list) {
				if (traffic.start > list.get(i).end + 999 || traffic.end < list.get(i).end)
					continue;
				cnt++;
			}

			max = Math.max(cnt, max);
		}

		answer = max;

		return answer;
	}

	private static int changeTime(String s) { // 21:00:02.066
		// [hour, minute, second]
		double[] arr = Arrays.stream(s.split(":")).mapToDouble(Double::parseDouble).toArray();
		return (int) (arr[0] * 3600 * 1000 + arr[1] * 60 * 1000 + arr[2] * 1000);
	}

	private static int changeTreat(String s) {
		return (int) (Double.parseDouble(s) * 1000);
	}

	private static class Traffic {
		int start;
		int end;

		public Traffic(int start, int end) {
			this.start = start;
			this.end = end;
		}
	}
}