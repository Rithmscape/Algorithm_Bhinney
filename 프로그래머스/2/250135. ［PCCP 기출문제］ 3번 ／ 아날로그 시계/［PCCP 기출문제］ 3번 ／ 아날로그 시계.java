class Solution {
    public static int solution(int h1, int m1, int s1, int h2, int m2, int s2) {
		int start = parse(h1, m1, s1);
		int end = parse(h2, m2, s2);

		int answer = count(end) - count(start);
		answer += now(start) ? 1 : 0;

		return answer;
	}

	private static int parse(int h, int m, int s) {
		return h * 3600 + m * 60 + s;
	}

	private static int count(int s) {
		int min = s * 59 / 3600;
		int hour = s * 719 / 43200;
		int duplicated = 43200 <= s ? 2 : 1;

		return min + hour - duplicated;
	}

	private static boolean now(int s) {
		return s * 59 / 3600 == 0 || s * 719 % 43200 == 0;
	}
}