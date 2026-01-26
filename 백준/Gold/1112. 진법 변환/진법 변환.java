import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		long num = sc.nextLong();
		int base = sc.nextInt();
		sc.close();

		System.out.println(find(num, base));
	}

	private static String find(long num, int base) {
		if (num == 0) return "0";

		// 양수인 경우
		if (base > 0)
			return Long.toString(num, base);

		// 음수인 경우
		StringBuilder result = new StringBuilder();

		while (num != 0) {
			long r = num % base;
			num /= base;

			if (r < 0) {
				r -= base;
				num += 1;
			}

			result.append(r);
		}

		return result.reverse().toString();
	}
}