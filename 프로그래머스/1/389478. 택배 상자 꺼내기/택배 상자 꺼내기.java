class Solution {
    public int solution(int n, int w, int num) {
        int ans = 0;

		while (num <= n) {
			num += 2 * (w - ((num - 1) % w) - 1) + 1;
			ans++;
		}

		return ans;
    }
}