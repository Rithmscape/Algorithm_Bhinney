class Solution {
    public static int[] solution(long begin, long end) {
		int[] answer = new int[(int) (end - begin) + 1];

		for (long i = begin; i <= end; i++) 
			answer[(int) (i - begin)] = function(i);


		return answer;
	}

	private static int function(long n) {
		int res = 1;

		if (n == 1) return 0;

		for (int i = 2; i <= Math.sqrt(n); i++) {
			if (n % i == 0) {
				if (n / i <= 10_000_000) {
					res = Math.max(res, (int) n / i);
					break;
				} else {
					res = Math.max(res, i);
				}
			}
		}

		return res;
	}
}