import java.util.*;

class Solution {
    public String[] solution(String[] expressions) {
		List<String> answer = new ArrayList<>();
		List<String[]> list = new ArrayList<>();

		boolean[] candidate = new boolean[10];
		Arrays.fill(candidate, true);
		candidate[0] = false;
		candidate[1] = false;

		for (String e : expressions) {
			String[] parse = e.split(" ");

			for (int k = 2; k <= 9; k++) {
				if (!candidate[k]) continue;
				candidate[k] = isValid(k, parse[0], parse[2], parse[4], parse[1]);
			}

			if (parse[4].equals("X")) 
				list.add(parse);
		}

		for (String[] l : list) {
			boolean flag = true;
			String x = "";

			for (int k = 2; k <= 9; k++) {
				if (!candidate[k]) continue;

				int result = calculate(toTen(k, l[0]), toTen(k, l[2]), l[1]);

				if (x.isEmpty()) x = toK(k, result);
				else if (!x.equals(toK(k, result))) {
						flag = false;
						break;
				}
			}

			l[4] = flag ? x : "?";

			String str = String.join(" ", l);
			answer.add(str);
		}

		return answer.toArray(String[]::new);
	}

	private boolean isValid(int k, String a, String b, String c, String op) {
		// a와 b의 숫자들은 k보다 작아야함
		for (char aa : a.toCharArray())
			if (aa - '0' >= k) return false;

		for (char bb : b.toCharArray())
			if (bb - '0' >= k) return false;

		if (c.equals("X")) return true;

		for (char cc : c.toCharArray())
			if (cc - '0' >= k) return false;

		return toTen(k, c) == calculate(toTen(k, a), toTen(k, b), op);
	}

	// k진법 -> 10진법
	private int toTen(int k, String s) {
		int d = 1;
		int sum = 0;

		for (int i = s.length() - 1; i >= 0; i--) {
			sum += (s.charAt(i) - '0') * d;
			d *= k;
		}

		return sum;
	}

	// 10진법 -> k진법
	private String toK (int k, int x) {
		if (x == 0) return "0";

		return Integer.toString(x, k);
	}

	private int calculate(int a, int b, String op) {
		return op.equals("+") ? a + b : a - b;
	}
}