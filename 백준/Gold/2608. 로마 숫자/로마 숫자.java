import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String str1 = sc.nextLine();
		String str2 = sc.nextLine();
		sc.close();

		new Solution().solve(str1, str2);
	}

	private static class Solution {

		private final int[] values = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
		private final String[] romans = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};

		void solve(String str1, String str2) {
			int a = toArabic(replace(str1));
			int b = toArabic(replace(str2));
			int sum = a + b;

			String answer = toRoman(sum);

			System.out.println(sum);
			System.out.println(answer);
		}

		private int toArabic(String roman) {
			String symbols = "MADBCaLbXcVdI";
			int sum = 0;

			for (char ch : roman.toCharArray()) {
				int idx = symbols.indexOf(ch);
				if (idx == -1) continue;
				sum += values[symbols.indexOf(ch)];
			}

			return sum;
		}

		private String toRoman(int arabic) {
			StringBuilder answer = new StringBuilder();

			for (int idx = 0; idx < values.length; idx++) {
				while (arabic >= values[idx]) {
					answer.append(romans[idx]);
					arabic -= values[idx];
				}
			}

			return answer.toString();
		}

		// 계산하기 편하게 문자열을 치환
		private String replace(String s) {
			return s.replace("CM", "A")
				.replace("CD", "B")
				.replace("XC", "a")
				.replace("XL", "b")
				.replace("IX", "c")
				.replace("IV", "d");
		}
	}
}