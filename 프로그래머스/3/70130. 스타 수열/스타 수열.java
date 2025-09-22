class Solution {
    public int solution(int[] arr) {
        int[] counts = new int[arr.length];
		for (int a : arr)
			counts[a]++;

		int answer = 0;
		for (int i = 0; i < counts.length; i++) {
			if (counts[i] <= answer) continue;

			int ans = 0;
			for (int j = 0; j < arr.length - 1; j++) {
				if ((arr[j] != arr[j + 1]) && (i == arr[j] || i == arr[j + 1])) {
					ans++;
					j++;
				}
			}

			answer = Math.max(answer, ans);
		}

		return answer * 2;
    }
}