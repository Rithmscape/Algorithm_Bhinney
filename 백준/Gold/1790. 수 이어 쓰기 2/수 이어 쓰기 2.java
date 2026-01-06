import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int k = sc.nextInt();

		int answer = solve(n, k);
		System.out.println(answer);
	}

	/*
	 * 일의 자리(1 ~ 9) : 9글자
	 * 십의 자리 (10 ~ 99) : 180글자
	 * 백의 자리 (100 ~ 999) : 2700글자
	 */

	private static int solve(int n, int k) {
		if (k <= 9) return k;

		long len = 0; // 문자 개수
		int digit = 1; // 숫자의 자리 수
		int start = 1;

		while (len + (long) digit * (Math.min(n, start * 10 - 1) - start + 1) < k) {
			len += (long) digit * (Math.min(n, start * 10 - 1) - start + 1);
			digit++;
			start *= 10;
		}

		if (start > n) return -1;

		long remain = k - len;
		int target = start + (int) ((remain - 1) / digit);
		int position =  (int) ((remain - 1) % digit);

		String str = String.valueOf(target);

		return Character.getNumericValue(str.charAt(position));
	}
}