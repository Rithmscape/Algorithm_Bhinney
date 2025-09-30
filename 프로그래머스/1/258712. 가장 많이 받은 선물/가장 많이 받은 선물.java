import java.util.HashMap;
import java.util.Map;

class Solution {
    public int solution(String[] friends, String[] gifts) {
        Map<String, Integer> key = new HashMap<>();
		for (int i = 0; i < friends.length; i++)
			key.put(friends[i], i);

		int[] score = new int[friends.length];
		int[][] info = new int[friends.length][friends.length];

		for (String g : gifts) {
			String from = g.split(" ")[0];
			String to = g.split(" ")[1];

			score[key.get(from)]++;
			score[key.get(to)]--;

			info[key.get(from)][key.get(to)]++;
		}

		int answer = 0;

		for (int i = 0; i < friends.length; i++) {
			int cnt = 0;
			for (int j = 0; j < friends.length; j++) {
				if (i == j) continue;

				if (info[i][j] > info[j][i] || (info[i][j] == info[j][i] && score[i] > score[j]))
					cnt++;
			}

			answer = Math.max(answer, cnt);
		}

		return answer;
    }
}