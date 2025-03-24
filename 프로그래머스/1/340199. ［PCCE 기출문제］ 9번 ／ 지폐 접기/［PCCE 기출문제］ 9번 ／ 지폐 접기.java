import java.util.Arrays;

class Solution {
    public int solution(int[] wallet, int[] bill) {
        Arrays.sort(wallet);
		Arrays.sort(bill);

		int ans = 0;
		while (bill[0] > wallet[0] || bill[1] > wallet[1]) {
			ans++;
			bill[1] /= 2;
			Arrays.sort(bill);
		}

		return ans;
    }
}