import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
import java.util.stream.IntStream;

class Solution {
    static List<Mineral> list = new ArrayList<>();
	public static int solution(int[] picks, String[] minerals) {
		int ans = 0;
		int total = IntStream.of(picks).sum();
		addAndSort(minerals, total);

		for (Mineral m : list) {
			if (picks[0] > 0) {
				ans += m.dia;
				picks[0]--;
			} else if (picks[1] > 0) {
				ans += m.iron;
				picks[1]--;
			} else if (picks[2] > 0) {
				ans += m.stone;
				picks[2]--;
			}
		}

		return ans;
	}

	private static void addAndSort(String[] minerals, int total) {

		for (int i = 0; i < minerals.length; i += 5) {
			if (total == 0) break; // 곡괭이가 없으면 끝내기

			int dia = 0, iron = 0, stone = 0;
			for (int j = i; j < i + 5; j++) {
				String m = minerals[j];
				switch (m) {
					case "diamond" -> {
						dia += 1;
						iron += 5;
						stone += 25;
					}
					case "iron" -> {
						dia += 1;
						iron += 1;
						stone += 5;
					}
					case "stone" -> {
						dia += 1;
						iron += 1;
						stone += 1;
					}
					default -> {}
				}

				if (j == minerals.length - 1) break;
			}

            total--;
			list.add(new Mineral(dia, iron, stone));
		}

		Collections.sort(list, (o1, o2) -> o2.stone - o1.stone);
	}

	private static class Mineral{
		int dia;
		int iron;
		int stone;

		public Mineral(int dia, int iron, int stone) {
			this.dia = dia;
			this.iron = iron;
			this.stone = stone;
		}
	}
}