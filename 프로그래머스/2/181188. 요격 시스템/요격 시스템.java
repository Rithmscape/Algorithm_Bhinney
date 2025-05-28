import java.util.Arrays;

class Solution {
    public int solution(int[][] targets) {
		int ans = 1;
		Arrays.sort(targets, (o1, o2) -> o1[1] - o2[1]);

		int f = targets[0][1];
		for (int[] t : targets) {
			if (t[0] < f) continue;
			ans++;
			f = t[1];
		}

		return ans;
	}
}