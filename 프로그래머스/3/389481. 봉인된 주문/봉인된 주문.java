import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

class Solution {
    public String solution(long n, String[] bans) {
        // a ~ z => 97 ~ 122

		Queue<String> queue = new ArrayDeque<>();
		Arrays.sort(bans, (o1, o2) -> {
			if (o1.length() == o2.length()) return o1.compareTo(o2);
			return o1.length() - o2.length();
		});

		for (String ban : bans)
			queue.offer(ban);

		while (!queue.isEmpty()) {
			String cur = queue.peek();
			String target = getString(n);

			if (cur.length() >= target.length() && (cur.length() != target.length() || cur.compareTo(target) > 0))
				break;

			queue.poll();
			n++;

		}

		return getString(n);
    }

    private String getString(long n) {
		StringBuilder sb = new StringBuilder();

		while (n > 0) {
			long remained = n % 26;
			n /= 26;
			if (remained == 0) {
				n--;
				sb.append('z');
			} else {
				sb.append((char) (97 + remained - 1));
			}
		}

		return sb.reverse().toString();
	}
}