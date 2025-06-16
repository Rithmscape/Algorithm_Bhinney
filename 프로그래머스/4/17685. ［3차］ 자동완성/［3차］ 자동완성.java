import java.util.Arrays;
import java.util.stream.IntStream;

class Solution {
    public static int solution(String[] words) {
		Arrays.sort(words);
		int[] counts = new int[words.length];

		for (int i = 0; i < words.length - 1; i++) {
			String cur = words[i];
			String next = words[i + 1];

			int same = countSame(cur, next);
			if (same == Math.min(cur.length(), next.length()))
				counts[i] = Math.max(counts[i], same);
			else counts[i] = Math.max(counts[i], same + 1);

			counts[i + 1] = Math.max(counts[i + 1], same + 1);
		}

		return IntStream.of(counts).sum();
	}

	private static int countSame(String str1, String str2) {
		int cnt = 0;

		for (int i = 0; i < Math.min(str1.length(), str2.length()); i++) {
			if (str1.charAt(i) != str2.charAt(i)) break;
			cnt++;
		}

		return cnt;
	}
}