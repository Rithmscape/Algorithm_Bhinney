class Solution {
    public int solution(int[] cookie) {
		/*
		 * 과자를 바구니 단위로 판매
		 * 첫째 -> l ~ m
		 * 둘째 -> (m + 1) ~ r
		 * 첫째 과자수 == 둘째 과자수
		 */
		int ans = 0;

		for (int m = 0; m < cookie.length - 1; m++) { // 인덱스로 누적합
			int l = m;
			int r = m + 1;

			int sum1 = cookie[l];
			int sum2 = cookie[r];

			while (true) {
				if (sum1 == sum2) ans = Math.max(ans, sum1);

				if (sum1 <= sum2 && l > 0) { // m을 기준으로 왼쪽 확장
					l--;
					sum1 += cookie[l];
				}
				else if (sum1 > sum2 && r < cookie.length - 1) { // m을 기준으로 오른쪽 확장
					r++;
					sum2 += cookie[r];
				} else break;
			}
 		}

		return ans;
	}
}